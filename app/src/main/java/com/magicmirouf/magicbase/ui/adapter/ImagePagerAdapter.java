package com.magicmirouf.magicbase.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.magicmirouf.magicbase.navigation.fragment.ImageFragment;

import java.util.List;

/**
 * Created by MagicMirouf on 12/12/2016.
 */

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> urls;
    private int orientation;

    public ImagePagerAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;
    }

    public void setOrientation(int _orientation) {
        orientation = _orientation;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setOrientation(orientation);
        imageFragment.setURL(urls.get(position));
        return imageFragment;
    }

    @Override
    public int getCount() {
        return urls.size();
    }
}
