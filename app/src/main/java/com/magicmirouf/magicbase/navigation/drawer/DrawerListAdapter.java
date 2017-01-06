package com.magicmirouf.magicbase.navigation.drawer;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magicmirouf.magicbase.R;

import java.util.List;


/**
 * Created by sylva on 03/06/2016.
 */
public class DrawerListAdapter extends BaseAdapter {

    private List<DrawerItem> datas;
    private Context context;

    public DrawerListAdapter(List<DrawerItem> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = View.inflate(context, R.layout.drawer_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.drawer_item_image);
            viewHolder.chevron = (ImageView) convertView.findViewById(R.id.arrow);
            viewHolder.background = (ImageView) convertView.findViewById(R.id.background);
            viewHolder.title = (TextView) convertView.findViewById(R.id.drawer_item_title);
            viewHolder.sub = (TextView) convertView.findViewById(R.id.drawer_item_sub);
            viewHolder.data = (TextView) convertView.findViewById(R.id.drawer_item_data);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DrawerItem item = datas.get(position);

        if (item !=null) {
            convertView.setVisibility(View.VISIBLE);
            if (item.getImage_id() !=0)
                viewHolder.image.setImageDrawable(context.getResources().getDrawable(item.getImage_id()));
            else
                viewHolder.image.setVisibility(View.GONE);

            viewHolder.title.setText(item.getTitle());

            if (item.getSubtitle() != null) {
                viewHolder.sub.setText(item.getSubtitle());
            }else {
                viewHolder.sub.setVisibility(View.GONE);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                viewHolder.title.setLayoutParams(lp);
                viewHolder.title.setGravity(Gravity.CENTER_VERTICAL);
            }

            viewHolder.data.setText(item.getData_number());
            viewHolder.background.setBackgroundColor(item.getData_color());

            if (item.isChevron()) {
                viewHolder.chevron.setVisibility(View.VISIBLE);
            } else {
                viewHolder.chevron.setVisibility(View.GONE);
            }
        } else {
            convertView.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }


    private class ViewHolder {

        private ImageView image;
        private ImageView chevron;
        private ImageView background;
        private TextView title;
        private TextView sub;
        private TextView data;

    }

}
