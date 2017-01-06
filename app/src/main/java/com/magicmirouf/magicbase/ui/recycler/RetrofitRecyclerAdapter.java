package com.magicmirouf.magicbase.ui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicmirouf.magicbase.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sylva on 26/07/2016.
 */
public class RetrofitRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int NOT_EMPTY = 0;
    private static final int EMPTY = 1;
    protected int type;

    private Context context;
    protected List<T> datas;
    protected int layout_item;
    protected String text_empty = "Nadal";

    public RetrofitRecyclerAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == NOT_EMPTY) {
            view = LayoutInflater.from(context).inflate(layout_item, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_text, parent, false);
        }

        return onCreateView(view);
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == NOT_EMPTY) {
            onBindView(holder, datas.get(position));
        } else {
            ((ViewHolderText)holder).setData(text_empty);
        }
    }

    @Override
    public int getItemCount() {
        if (datas.size() >0) {
            type = NOT_EMPTY;
            return datas.size();
        } else {
            type = EMPTY;
            return 1;
        }
    }

    protected void onBindView(RecyclerView.ViewHolder holder, T data){
    }

    protected RecyclerView.ViewHolder onCreateView(View view) {
        return new ViewHolderText(view);
    }

    public class ViewHolderText extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolderText(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setData(String text) {
            textView.setText(text);
        }
    }

}
