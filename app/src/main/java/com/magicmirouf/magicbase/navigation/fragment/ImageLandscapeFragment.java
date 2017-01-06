package com.magicmirouf.magicbase.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.magicmirouf.magicbase.R;

/**
 * Created by MagicMirouf on 12/12/2016.
 */

public class ImageLandscapeFragment extends BaseFragment {

    private String URL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_layout = R.layout.fragment_image_landscape;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImage(URL,((ImageView)view));
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
