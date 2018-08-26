package com.davidrajpaul.bottomnavviewwithviewpager.fragments.dashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davidrajpaul.bottomnavviewwithviewpager.MainActivity;
import com.davidrajpaul.bottomnavviewwithviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFrag extends Fragment {


    private Button addNewFragBtn;

    public DashBoardFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        addNewFragBtn = (Button)v.findViewById(R.id.add_new_frag_btn);
        addNewFragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).pushFragments(MainActivity.TAB_DASHBOARD, new DashBoardChild1(),
                        R.id.dashboard_frag_stack_container,true);

            }
        });
        return v;
    }


}
