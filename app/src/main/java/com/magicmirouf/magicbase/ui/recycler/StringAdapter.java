package com.magicmirouf.magicbase.ui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicmirouf.magicbase.R;

import java.util.List;

/**
 * Created by MagicMirouf on 05/12/2016.
 */

public class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> fields;

    public StringAdapter(Context context, List<String> fields) {
        this.context = context;
        this.fields = fields;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderThis(LayoutInflater.from(context).inflate(R.layout.item_text,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderThis)holder).setData(fields.get(position));
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    private class ViewHolderThis extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolderThis(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }


        public void setData(String s) {
            tv.setText(s);
        }
    }
}
