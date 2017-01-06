package com.magicmirouf.magicbase.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicmirouf.magicbase.R;

/**
 * Created by MagicMirouf on 16/11/2016.
 */

public class MainFragment extends BaseFragment {

    public final static String TAG = "Home";


    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout definition
        id_layout = R.layout.fragment_main;
        // Clicks events
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id){

                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        //getNavigationActivity().setToolbarTitle(TAG);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClicksEvent();
        //fillDatas(view);

    }




    @Override
    public void backPressed() {
        super.backPressed();
    }

}
