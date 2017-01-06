package com.magicmirouf.magicbase.navigation;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.magicmirouf.magicbase.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MagicMirouf on 26/11/2016.
 */

public class BaseActivity extends AppCompatActivity {

    public boolean savedInstance = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        savedInstance = false;
    }

    // layout
    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    // Classics transitions
    public void addFirstFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment, tag);
        transaction.commit();
    }

    public void addSlideHorizontalFragment(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.add(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void addStackedFragment(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);

        transaction.add(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }





    public void addSlideVerticalFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit);

        transaction.add(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);

        transaction.commit();
    }



    public void replaceStackedFragment(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void replaceStackedFragmentNoBackstack(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.content_frame, fragment, tag);

        transaction.commit();
    }


    public void replaceSideVertical(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit);

        transaction.replace(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void addSideVertical(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit);

        transaction.add(R.id.content_frame, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public int getColor2(int color){
        return ContextCompat.getColor(this,color);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        savedInstance = true;
    }

    public void showKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.magicmirouf.barnaby",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
