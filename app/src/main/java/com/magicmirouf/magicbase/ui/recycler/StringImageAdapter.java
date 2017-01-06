package com.magicmirouf.magicbase.ui.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicmirouf.magicbase.R;

import java.util.List;

/**
 * Created by MagicMirouf on 05/12/2016.
 */

public class StringImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> fields;
    private List<Integer> images;
    private AdapterListener listener;

    private int colorSelection;
    private int positionSelected = -1;

    public StringImageAdapter(Context context, List<String> fields, List<Integer> images) {
        this.context = context;
        this.fields = fields;
        this.images = images;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderThis(LayoutInflater.from(context).inflate(R.layout.item_text_image,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderThis)holder).setData(fields.get(position),images.get(position),position);
    }

    public void setColorSelection(int colorSelection) {
        this.colorSelection = colorSelection;
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    private class ViewHolderThis extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public ViewHolderThis(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
            iv = (ImageView) itemView.findViewById(R.id.image);
        }


        public void setData(String s, Integer integer, final int position) {
            tv.setText(s);
            if (integer !=-1){
                iv.setImageResource(integer);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if (listener!=null){
                    listener.OnClick(position);
                    if (position == positionSelected){
                        select();
                    }
                    positionSelected = position;
                    notifyDataSetChanged();
                }
                }
            });

            if (position == positionSelected){
                select();
            } else {
                unSelect();
            }

        }

        private void select() {
            tv.setTextColor(colorSelection);
            iv.setColorFilter(colorSelection);
        }

        private void unSelect() {
            tv.setTextColor(Color.BLACK);
            iv.setColorFilter(Color.BLACK);
        }
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public interface AdapterListener {
        void OnClick(int position);
    }

    public void selectPosition(int position){
        positionSelected = position;
        notifyDataSetChanged();
    }




}
