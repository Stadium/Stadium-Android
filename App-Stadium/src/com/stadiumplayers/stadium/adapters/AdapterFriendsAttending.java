
package com.stadiumplayers.stadium.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public class AdapterFriendsAttending extends BaseAdapter {

    private Context mContext;
    private String[] mData;

    public AdapterFriendsAttending(Context context, String[] friendsAttending) {
        mContext = context;
        mData = friendsAttending;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public String getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        // Not used by framework
        return 0;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_friends_attending,
                    null, false);
            
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_friends_attending_img);
            holder.name = (TextView) convertView.findViewById(R.id.item_friends_attending_text_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        
        holder.name.setText(getItem(position));
        holder.imageView.setImageDrawable(Utils.getDrawable(R.drawable.ic_launcher));
        
        return convertView;
    }

}
