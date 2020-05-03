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
    LayoutInflater mInflater;

    public ItemAdapter(Context c, String[] s, String[] d){
        sports = s;
        descriptions = d;
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

        String sports_s = sports[position];
        String description_s = descriptions[position];

        nameTextView.setText(sports_s);
        descTextView.setText(description_s);

        return v;
    }
}
