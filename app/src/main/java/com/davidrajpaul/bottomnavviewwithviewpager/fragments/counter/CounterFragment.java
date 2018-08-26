package com.davidrajpaul.bottomnavviewwithviewpager.fragments.counter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davidrajpaul.bottomnavviewwithviewpager.MainActivity;
import com.davidrajpaul.bottomnavviewwithviewpager.R;

/**
 * A fragment with a Google +1 button.
 */
public class CounterFragment extends Fragment {

    private Button counterButton;


    public CounterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        counterButton = (Button) view.findViewById(R.id.counter_btn);
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).pushFragments(MainActivity.TAB_COUNTER, new CounterFragChild1()
                        , R.id.counter_container, true);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
