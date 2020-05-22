package com.example.finalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemAdapter extends BaseAdapter {

    String[] sports;
    String[] descriptions;
    String[] dates;
    String[] times;
    String[] venues;
    LayoutInflater mInflater;

    public ItemAdapter(Context c, String[] s, String[] d, String[] da, String[] t, String[] v){
        sports = s;
        descriptions = d;
        dates = da;
        times = t;
        venues = v;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return sports.length;
    }

    @Override
    public Object getItem(int position) {
        return sports[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= mInflater.inflate(R.layout.my_list_view, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descTextView = (TextView) v.findViewById(R.id.descTextView);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);
        TextView timeTextView = (TextView) v.findViewById(R.id.timeTextView);
        TextView venueTextView = (TextView) v.findViewById(R.id.venueTextView);

        String sports_s = sports[position];
        String descriptions_s = descriptions[position];
        String dates_s = dates[position];
        String times_s = times[position];
        String venues_s = venues[position];

        nameTextView.setText(sports_s);
        descTextView.setText(descriptions_s);
        dateTextView.setText(dates_s);
        timeTextView.setText(times_s);
        venueTextView.setText(venues_s);

        return v;
    }
}
