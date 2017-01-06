package com.magicmirouf.magicbase.navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aviadmini.quickimagepick.PickCallback;
import com.aviadmini.quickimagepick.PickSource;
import com.aviadmini.quickimagepick.PickTriggerResult;
import com.aviadmini.quickimagepick.QiPick;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.navigation.drawer.DrawerListAdapter;
import com.magicmirouf.magicbase.navigation.fragment.BaseFragment;
import com.magicmirouf.magicbase.navigation.fragment.ProgressFragment;
import com.magicmirouf.magicbase.utils.gps.GpsService;
import com.magicmirouf.magicbase.utils.pickers.ContactPicker;
import com.magicmirouf.magicbase.webservices.google.RetroFitGoogle;

import static com.magicmirouf.magicbase.utils.gps.GpsService.REQUEST_CHECK_SETTINGS;

/**
 * Created by MagicMirouf on 26/11/2016.
 */

public class NavigationActivity extends BaseActivity {

    public static final int MY_PERMISSIONS_READ_CONTACTS = 222;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 214;

    public static final int  REQUEST_PLACE_PICKER = 158;
    public static final int  REQUEST_PLACE_AUTOCOMPLETE = 160;

    // ToolBar
    public TextView title, tv_avatar, tv_footer, tv_layer;
    public ImageView toolbarAction,title_background,blur_avatar, avatar;
    private FloatingActionButton fab;
    public RatingBar rating_bar;
    public ActionBarDrawerToggle drawerToggle;
    public ProgressFragment progressFragment;

    // Drawer
    public DrawerLayout drawerLayout;
    public AdapterView.OnItemClickListener drawerItemClickistener;
    private ListView listView;
    public DrawerListAdapter drawerListAdapter;

    // Picker
    protected PickCallback mCallback;

    //GPS
    public GpsService gpsService;
    protected boolean activeGPS = false;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        // Progress
        initProgress();
        tv_avatar = (TextView) findViewById(R.id.avatar_text);
        tv_layer = (TextView) findViewById(R.id.drawer_layer);
        tv_footer = (TextView) findViewById(R.id.footer);
        blur_avatar = (ImageView) findViewById(R.id.blur_avatar);
        avatar = (ImageView) findViewById(R.id.avatar);
        rating_bar = (RatingBar) findViewById(R.id.myRatingBar);
        // Navigation : Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        title_background = (ImageView) toolbar.findViewById(R.id.toolbar_background_title);
        toolbarAction = (ImageView) toolbar.findViewById(R.id.toolbar_action);
        fab = (FloatingActionButton) findViewById(R.id.fab_main);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(4);
        }

        // Navigation : Drawer Layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(drawerToggle);
            listView = (ListView) drawerLayout.findViewById(R.id.drawer_list);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        // Navigation
        setDrawerItems();

        if (listView != null) {
            listView.setAdapter(drawerListAdapter);
            listView.setOnItemClickListener(drawerItemClickistener);
        }

        // start GPS Location
        if (activeGPS) {
            mGoogleApiClient = new GoogleApiClient
                    .Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(GpsService.connectionCallback)
                    .addOnConnectionFailedListener(GpsService.connectionFailed)
                    .build();
            gpsService = new GpsService(mGoogleApiClient);
        }

        RetroFitGoogle.init();
    }

    public Location getLocation() {
        return gpsService.myLocation;
    }

    protected void setDrawerItems() {
    }


    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void setToolbarTitle(String _title) {
        title_background.setVisibility(View.GONE);
        title.setOnClickListener(null);
        title.setText(_title);
    }

    public TextView getToolbarTitle() {
        return title;
    }

    public void setToolbarAction(int icone, View.OnClickListener action) {
        if (toolbarAction != null) {
            toolbarAction.setImageResource(icone);
            toolbarAction.setOnClickListener(action);
            toolbarAction.setVisibility(View.VISIBLE);
        }
    }

    public void removeToolbarAction() {
        if (toolbarAction != null) {
            toolbarAction.setVisibility(View.INVISIBLE);
            toolbarAction.setOnClickListener(null);
        }
    }


    public void setFAB(int icone, View.OnClickListener action) {
        if (fab != null) {
            fab.setImageResource(icone);
            fab.setOnClickListener(action);
            fab.setVisibility(View.VISIBLE);
        }
    }

    public void removeFAB() {
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(null);
    }

    public void backToHome() {
        if (!savedInstance) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryAt(0).getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return;
        }

        if (!progressFragment.isHidden())
            return;

        if (getActiveFragment() != null) {
            if (((BaseFragment) getActiveFragment()).backChild) {
                ((BaseFragment) getActiveFragment()).backPressed();
            } else {
                if (getActiveFragment() instanceof ProgressFragment) {
                    return;
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    public Fragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public Fragment getPreviousFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() < 2) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 2).getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }


    public void setProgress(boolean show) {
        if (getApplicationContext() != null) {
            if (!savedInstance) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                if (progressFragment.isHidden() && show) {
                    transaction.show(progressFragment);
                } else if (!progressFragment.isHidden() && !show) {
                    transaction.hide(progressFragment);
                }
                transaction.commit();
            }
        }
    }

    public void initProgress() {
        progressFragment = new ProgressFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.add(R.id.content_frame_progress, progressFragment, ProgressFragment.TAG);
        transaction.hide(progressFragment);
        transaction.commit();
    }

    public void backClick(View view) {
        getSupportFragmentManager().popBackStack();
    }

    public void searchContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            ContactPicker.searchContacts(this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_READ_CONTACTS);
        }
    }

    public void setProgressColor(int color) {
        Intent intent = new Intent("ProgressColor");
        intent.putExtra("color", getColor2(color));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void onPlaceSearch(View view) {
        // Construct an intent for the place picker
        if (gpsService.myLocation !=null) {
            try {
                PlacePicker.IntentBuilder intentBuilder =
                        new PlacePicker.IntentBuilder();
                Intent intent = intentBuilder.build(this);
                // Start the intent by requesting a result,
                // identified by a request code.
                startActivityForResult(intent, REQUEST_PLACE_PICKER);

            } catch (GooglePlayServicesRepairableException e) {
                // ...
            } catch (GooglePlayServicesNotAvailableException e) {
                // ...
            }
        } else {
            try {
                AutocompleteFilter.Builder builder = new AutocompleteFilter.Builder();
                builder.setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES);
                builder.setCountry("FR");
                Intent intent =
                        new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                .setFilter(builder.build())
                                .build(this);
                startActivityForResult(intent, REQUEST_PLACE_AUTOCOMPLETE);
            } catch (GooglePlayServicesRepairableException e) {
                // TODO: Handle the error.
            } catch (GooglePlayServicesNotAvailableException e) {
                // TODO: Handle the error.
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        QiPick.handleActivityResult(getApplicationContext(), requestCode, resultCode, data, this.mCallback);
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    @PickTriggerResult final int triggerResult;
                    triggerResult = QiPick.in(NavigationActivity.this)
                            .fromMultipleSources("All sources", PickSource.CAMERA, PickSource.DOCUMENTS);
                }
                break;
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        gpsService.startLocationUpdates(this);
                        break;
                }
                break;

            case REQUEST_PLACE_PICKER:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        NavigationData.place = PlacePicker.getPlace(this,data);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(String.valueOf(REQUEST_PLACE_PICKER)));
                        break;
                }
                break;
            case REQUEST_PLACE_AUTOCOMPLETE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        NavigationData.place = PlaceAutocomplete.getPlace(this,data);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(String.valueOf(REQUEST_PLACE_PICKER)));
                        break;
                }
                break;
        }

    }


}
