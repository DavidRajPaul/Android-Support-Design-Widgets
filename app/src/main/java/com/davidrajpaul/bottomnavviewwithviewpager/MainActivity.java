package com.davidrajpaul.bottomnavviewwithviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.davidrajpaul.bottomnavviewwithviewpager.fragments.counter.CounterFragment;
import com.davidrajpaul.bottomnavviewwithviewpager.fragments.dashboard.DashBoardFrag;
import com.davidrajpaul.bottomnavviewwithviewpager.fragments.recent.RecentFrag;

import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private ViewPager viewPager;

    MenuItem prevMenuItem;
    private DashBoardFrag dashboardFrag;
    private RecentFrag recentFrag;
    private CounterFragment counterFrag;

    private HashMap<String, Stack<Fragment>> mStacks;
    public static final String TAB_RECENT = "tab_recent";
    public static final String TAB_DASHBOARD = "tab_dashboard";
    public static final String TAB_COUNTER = "tab_counter";

    private String mCurrentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavView.getMenu().getItem(position);

                if(position==0){
                    mCurrentTab = TAB_DASHBOARD;
                }else if(position==1){
                    mCurrentTab = TAB_COUNTER;
                }else{
                    mCurrentTab = TAB_RECENT;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_dashboard:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_counter:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_recent:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(TAB_COUNTER, new Stack<Fragment>());
        mStacks.put(TAB_DASHBOARD, new Stack<Fragment>());
        mStacks.put(TAB_RECENT, new Stack<Fragment>());

        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        dashboardFrag = new DashBoardFrag();
        counterFrag = new CounterFragment();
        recentFrag = new RecentFrag();
        adapter.addFragment(dashboardFrag);
        adapter.addFragment(counterFrag);
        adapter.addFragment(recentFrag);
        viewPager.setAdapter(adapter);
    }

    public void pushFragments(String tag, Fragment fragment,int containerID, boolean shouldAdd){
        mCurrentTab = tag;
        if(shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(containerID, fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

    public void popFragments(){
        /*
         *    Select the second last fragment in current tab's stack..
         *    which will be shown after the fragment transaction given below
         */
        Fragment fragment = mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 1);

        /*pop current fragment from stack.. */
        mStacks.get(mCurrentTab).pop();
        getSupportFragmentManager().popBackStack(fragment.getClass().getName(),FragmentManager.POP_BACK_STACK_INCLUSIVE);


        /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction ft = manager.beginTransaction();
//        ft.replace(R.id.dashboard_frag_stack_container, fragment);
//        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(mStacks.get(mCurrentTab).size() == 0){
            // We are already showing first fragment of current tab, so when back pressed, we will finish this activity..
            finish();
            return;
        }

        /* Goto previous fragment in navigation stack of this tab */
        popFragments();
    }
}
