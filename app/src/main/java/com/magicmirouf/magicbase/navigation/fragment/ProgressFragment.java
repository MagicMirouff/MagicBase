package com.magicmirouf.magicbase.navigation.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.magicmirouf.magicbase.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sylva on 02/06/2016.
 */
public class ProgressFragment extends BaseFragment {

    public static final String TAG = "ProgressFragment";
    private ImageView anim;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int color = intent.getIntExtra("color", Color.BLACK);
                setColorProgress(color);
            }
        },new IntentFilter("ProgressColor"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anim = (ImageView) view.findViewById(R.id.image);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        //setDrawable(android.R.drawable.ic_menu_upload);
        /*anim.setAnimation(new RotateAnimation(0,360));
        anim.animate();*/

    }

    public void setDrawable(int ic_marker) {
        Picasso.with(getActivity()).load(ic_marker).fit().centerInside().into(anim);
    }

    public void setColorProgress(int color) {
        progressBar.getProgressDrawable().setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
    }

}
