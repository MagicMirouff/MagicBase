package com.magicmirouf.magicbase.navigation.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.navigation.NavigationActivity;
import com.magicmirouf.magicbase.ui.dialog.AlertDialogUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sylva on 26/07/2016.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    protected List<Object> datas = new ArrayList<>();
    public boolean backChild = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Broadcast

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(id_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view !=null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setClickable(true);
        }
    }

    public void setData(Object data,View view){
        if (data instanceof String){
            if (view instanceof  TextView)
                ((TextView)view).setText((String)data);
            if (view instanceof EditText)
                ((EditText)view).setText((String)data);
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageResource((Integer)data);
        }
    }

    public void fillData(Object data,int id){
        View view = getView().findViewById(id);
        setData(data,view);
    }

    public void fillDatas(View main_view){
        fillDatas(main_view,this);
    }

    public void fillDatas(View main_view,Object o){
        if (main_view !=null) {
            if (main_view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) getView();
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View view = viewGroup.getChildAt(i);
                    if (view instanceof ViewGroup) {
                        //fillDatas(view);
                    } else {
                        for (int j = 0; j < o.getClass().getFields().length; j++) {
                            if (view.getId() != -1) {
                                if (getResources().getResourceName(view.getId()).replace(getActivity().getPackageName()+"id/", "").equals(o.getClass().getFields()[j].getName())) {
                                    try {
                                        setData(o.getClass().getFields()[j].get(o), view);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void addStackedFragment(Fragment fragment){
        ((NavigationActivity)getActivity()).addStackedFragment(fragment);
    }

    public void addSlideHorizontalFragment(Fragment fragment){
        ((NavigationActivity)getActivity()).addSlideHorizontalFragment(fragment);
    }

    public void replaceStackedFragment(Fragment fragment){
        if (getActivity() !=null)
            ((NavigationActivity)getActivity()).replaceStackedFragment(fragment);
    }

    public void addArguments(String[] names, Object[] values){
        Bundle args = new Bundle();
        for (int i = 0; i < names.length; i++) {
            if (values[i] instanceof Integer)
                args.putInt(names[i], (Integer) values[i]);
            if (values[i] instanceof String)
                args.putString(names[i], (String) values[i]);
        }
        this.setArguments(args);
    }

    // Classics transitions
    public void addFirstFragmentChild(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.child_container, fragment, tag);
        transaction.commit();
    }

    public void addStackedFragmentChild(Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);

        transaction.replace(R.id.child_container, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }



    public void replaceStackedFragmentChild(Fragment fragment) {
        String tag = fragment.getClass().toString();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.child_container, fragment, tag);

        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void backPressed() {
        if (getChildFragmentManager().getBackStackEntryCount()>0){
            getChildFragmentManager().popBackStack();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStack();
            } else {
                AlertDialogUtils.showalertDialog(getActivity(), "Souhaitez-vous quitter l'application Barnaby ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                });
            }
        }
    }


    public int getArgumentsValues(String mode) {
        Bundle args = getArguments();
        if (args !=null)
            return args.getInt(mode);
        else
            return 0;
    }

    public NavigationActivity getNavigationActivity(){
        return ((NavigationActivity) getActivity());
    }


    // Click Events
    protected View.OnClickListener clickListener;
    @Override
    public void onClick(View view) {
        clickListener.onClick(view);
    }

    public void setClicksEvent(int... ids){
        View view = getView();
        if (view !=null) {
            for (int id : ids) {
                view.findViewById(id).setOnClickListener(clickListener);
            }
        }
    }

    public void loadImage(String url, ImageView background) {
        Picasso.with(getActivity()).load(url).fit().centerCrop().into(background);
    }


    // Layout faster
    protected int id_layout;
    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) getView().findViewById(id);
    }

    // Arbitrary value; set it to some reasonable default
    private static final int DEFAULT_CHILD_ANIMATION_DURATION = 250;

    public int getColor(int color){
        return ContextCompat.getColor(getActivity(),color);
    }

    /*
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        final Fragment parent = getParentFragment();
        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed.
        if (!enter && parent != null && parent.isRemoving()) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(getNextAnimationDuration(parent, DEFAULT_CHILD_ANIMATION_DURATION));
            return doNothingAnim;
        } else {
            return super.onCreateAnimation(transit, enter, nextAnim);
        }
    }

    private static long getNextAnimationDuration(Fragment fragment, long defValue) {
        try {
            // Attempt to get the resource ID of the next animation that
            // will be applied to the given fragment.
            Field nextAnimField = Fragment.class.getDeclaredField("mNextAnim");
            nextAnimField.setAccessible(true);
            int nextAnimResource = nextAnimField.getInt(fragment);
            Animation nextAnim = AnimationUtils.loadAnimation(fragment.getActivity(), nextAnimResource);

            // ...and if it can be loaded, return that animation's duration
            return (nextAnim == null) ? defValue : nextAnim.getDuration();
        } catch (NoSuchFieldException |IllegalAccessException |Resources.NotFoundException ex) {
            Log.w("TAG", "Unable to load next animation from parent.", ex);
            return defValue;
        }
    }

    // Broadcast
    public interface BroadcastCall {
        void onNewLocation();
    }*/

    public void showToastShort(String mess){
        Toast.makeText(getActivity(),mess,Toast.LENGTH_SHORT).show();
    }

    public void clearKeyboard(View v){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if( getActivity()!=null) {
            if (getView() != null)
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }
}
