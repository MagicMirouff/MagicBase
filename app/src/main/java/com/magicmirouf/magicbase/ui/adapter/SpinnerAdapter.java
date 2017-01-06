package com.magicmirouf.magicbase.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magicmirouf.magicbase.R;

import java.util.List;

/**
 * Created by sylva on 26/08/2016.
 */
public class SpinnerAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> datas;

    public SpinnerAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view ==null){
            textView = (TextView) LayoutInflater.from(context).inflate(R.layout.spinner_item, viewGroup, false);
        } else {
            textView = (TextView) view;
        }
        textView.setText(datas.get(i).toString());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
