package com.magicmirouf.magicbase.navigation;

import com.google.android.gms.location.places.Place;

/**
 * Created by MagicMirouf on 29/12/2016.
 */
public class NavigationData {
    private static NavigationData ourInstance = new NavigationData();


    public static NavigationData getInstance() {
        return ourInstance;
    }

    private NavigationData() {
    }

    public static Place place;

}
