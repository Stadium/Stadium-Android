
package com.stadiumplayers.stadium.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.models.NavDrawer;

public class AdapterNavDrawer extends BaseAdapter {

    private NavDrawer[] mItems;
    private Context mContext;

    public AdapterNavDrawer(Context context) {
        mItems = NavDrawer.values();
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public NavDrawer getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        // Not used by framework
        return 0;
    }

    private static class ViewHolder {
        TextView title;
        ImageView icon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentView) {
        
        ViewHolder holder;
        if (convertView == null) {
            
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_nav_drawer, null, false);
            
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_nav_drawer_title);
            holder.icon = (ImageView) convertView.findViewById(R.id.item_nav_drawer_icon);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        NavDrawer item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageDrawable(item.getIcon());
        
        return convertView;
    }
}
