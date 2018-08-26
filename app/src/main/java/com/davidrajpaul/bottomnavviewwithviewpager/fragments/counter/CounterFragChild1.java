package com.davidrajpaul.bottomnavviewwithviewpager.fragments.counter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidrajpaul.bottomnavviewwithviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CounterFragChild1 extends Fragment {


    public CounterFragChild1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_counter_frag_child1, container, false);
        return v;
    }

}
