package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        /** Check if the existing view is being reused, otherwise inflate the view */
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        /** Get the {@link Earthquake} object located at this position in the list */
        Earthquake currentEarthquake = getItem(position);
        /** Find the TextView in the list_item.xml layout with the ID time_text_view and set object time to this view */
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(String.valueOf(currentEarthquake.getTime()));
        /** Find the TextView in the list_item.xml layout with the ID location_text_view and set object location to this view */
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        locationTextView.setText(currentEarthquake.getLocation());
        /** Find the TextView in the list_item.xml layout with the ID magnitude_text_view and set object magnitude to this view */
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));
        return listItemView;
    }
}
