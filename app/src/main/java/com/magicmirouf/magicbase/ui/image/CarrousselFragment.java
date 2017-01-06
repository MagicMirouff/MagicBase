package com.magicmirouf.magicbase.ui.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.navigation.fragment.BaseFragment;
import com.magicmirouf.magicbase.ui.adapter.ImagePagerAdapter;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by MagicMirouf on 12/12/2016.
 */

public class CarrousselFragment extends BaseFragment {

    public final static int HORIZONTAL = 0;
    public final static int VERTICAL = 1;
    private List<String> urls;
    private int orientation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_layout = R.layout.fragment_view_pager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager pager = $(R.id.pager);
        pager.setOffscreenPageLimit(urls.size());
        CircleIndicator circleIndicator = $(R.id.circle_indicator);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getChildFragmentManager(),urls);
        imagePagerAdapter.setOrientation(orientation);
        pager.setAdapter(imagePagerAdapter);
        circleIndicator.setViewPager(pager);

    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
