
package com.stadiumplayers.stadium.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;
import com.stadiumplayers.stadium.models.SportGame;

public class AdapterSearchResult extends BaseAdapter {

    private Context mContext;
    private List<SportGame> mSportGames;

    public AdapterSearchResult(Context context, List<SportGame> sportGames) {
        mContext = context;
        mSportGames = new ArrayList<SportGame>();
        mSportGames.addAll(sportGames);
    }

    @Override
    public int getCount() {
        return mSportGames.size();
    }

    @Override
    public SportGame getItem(int position) {
        return mSportGames.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Not used by framework
        return 0;
    }

    private static class ViewHolder {
        TextView textGameTitle;
        TextView textTime;
        TextView textParticipants;
        TextView textLocation;
        TextView textDistance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_result, null,
                    false);

            holder = new ViewHolder();
            holder.textGameTitle = (TextView) convertView
                    .findViewById(R.id.item_search_result_text_title);
            holder.textTime = (TextView) convertView
                    .findViewById(R.id.item_search_result_text_time);
            holder.textParticipants = (TextView) convertView
                    .findViewById(R.id.item_search_result_text_participants);
            holder.textLocation = (TextView) convertView
                    .findViewById(R.id.item_search_result_text_location);
            holder.textDistance = (TextView) convertView
                    .findViewById(R.id.item_search_result_text_distance);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SportGame sportGame = getItem(position);
        holder.textGameTitle.setText(sportGame.getTitle());
        holder.textTime.setText(Utils.getFriendlyDateFor(sportGame.getTimeInMillis()));
        holder.textParticipants.setText(String.format("Participants: %d", 
                sportGame.getParticipants()));
        holder.textLocation.setText(sportGame.getLocationText());
        holder.textDistance.setText(String.format("%.01f km away", sportGame.getDistance()));

        return convertView;
    }

}
