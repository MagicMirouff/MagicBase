package com.magicmirouf.magicbase.navigation;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.navigation.drawer.DrawerItem;
import com.magicmirouf.magicbase.navigation.drawer.DrawerListAdapter;
import com.magicmirouf.magicbase.navigation.fragment.MainFragment;

import java.util.ArrayList;

/**
 * Created by MagicMirouf on 26/11/2016.
 */

public class StartActivity extends NavigationActivity {

    @Override
    protected void setDrawerItems() {
        super.setDrawerItems();
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(getString(R.string.app_name), null, R.mipmap.ic_launcher, null, getResources().getColor(R.color.black), false));
        drawerItems.add(new DrawerItem(getString(R.string.app_name), null, R.mipmap.ic_launcher, null, getResources().getColor(R.color.black), false));
        drawerItems.add(new DrawerItem(getString(R.string.app_name), null, R.mipmap.ic_launcher, null, getResources().getColor(R.color.black), false));
        drawerItems.add(new DrawerItem(getString(R.string.app_name), null, R.mipmap.ic_launcher, null, getResources().getColor(R.color.black), false));
        drawerListAdapter = new DrawerListAdapter(drawerItems, this);

        drawerItemClickistener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment;
                switch (position) {
                    case 0:
                        backToHome();
                        drawerLayout.closeDrawers();
                        return;
                    case 1:

                        return;
                    case 2:

                        return;
                    case 3:

                        return;

                    case 4:

                        return;
                    case 5:

                        return;


                    case 6:

                        break;
                }
            }
        };
        // FirstFragment
        addFirstFragment(new MainFragment(), MainFragment.TAG);
    }


}
