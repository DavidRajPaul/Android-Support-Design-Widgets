package com.davidrajpaul.bottomnavviewwithviewpager.fragments.dashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidrajpaul.bottomnavviewwithviewpager.MainActivity;
import com.davidrajpaul.bottomnavviewwithviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardChild1 extends Fragment {


    public DashBoardChild1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dash_board_child1, container, false);

        v.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).pushFragments(MainActivity.TAB_DASHBOARD, new DashboardChild2(),
                        R.id.dashboard_frag_stack_container,true);
            }
        });

        return v;
    }

}
