package com.magicmirouf.magicbase.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.ui.image.CarrousselFragment;

/**
 * Created by MagicMirouf on 12/12/2016.
 */

public class ImageFragment  extends BaseFragment {

    private String URL;
    private int orientation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (orientation){
            case CarrousselFragment.HORIZONTAL:
                id_layout = R.layout.fragment_image_landscape;
                break;
            case CarrousselFragment.VERTICAL:
                id_layout = R.layout.fragment_image;
                break;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImage(URL,((ImageView)$(R.id.image)));
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
