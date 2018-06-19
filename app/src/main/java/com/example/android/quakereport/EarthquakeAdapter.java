package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;

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
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(currentEarthquake.getSimpleDate());
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(currentEarthquake.getSimpleTime());
//        timeTextView.setText(String.valueOf(currentEarthquake.getTime()));
        /** Find the TextView in the list_item.xml layout with the ID location_text_view and set object location to this view */
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        TextView locationHeadingTextView = (TextView) listItemView.findViewById(R.id.location_heading_text_view);
        String fullLocation = currentEarthquake.getLocation();
        String locationPart1;
        String locationPart2;
        /** Split the view if there is an "of", otherwise add "Near the" on the heading */
        if (fullLocation.contains(" of ")) {
            locationPart1 = fullLocation.substring(0, fullLocation.indexOf(" of ", 0)+4);
            locationPart2 = fullLocation.substring(fullLocation.indexOf(" of ", 0)+4, fullLocation.length());
        } else {
            locationPart1 = "Near the";
            locationPart2 = fullLocation;
        }
        locationTextView.setText(locationPart2);
        locationHeadingTextView.setText(locationPart1);
        /** Find the TextView in the list_item.xml layout with the ID magnitude_text_view and set object magnitude to this view */
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        /** Format the numbers to be "0.0" type, and not 0 or 0.00 */
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitudeDecimal = formatter.format(currentEarthquake.getMagnitude());
//        magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));
        magnitudeTextView.setText(magnitudeDecimal);
//        View quakeIconView = (View) listItemView.findViewById(R.id.earthquake_icon_color);
//        quakeIconView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorStrongQuake));
//
//        if (currentEarthquake.getMagnitude()>7) {
//            quakeIconView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_circle_strong));
//        } else if (currentEarthquake.getMagnitude()<6.1) {
//            quakeIconView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_circle_mild));
//        } else {
//            quakeIconView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_circle_medium));
//        }

        /** Set the proper background color on the magnitude circle.
         *  Fetch the background from the TextView, which is a GradientDrawable. */
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        /** Get the appropriate background color based on the current earthquake magnitude */
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        /** Set the color on the magnitude circle */
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }

    /** Method that gets magnitudeColor the right value according to the given number */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.colorMagnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.colorMagnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.colorMagnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.colorMagnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.colorMagnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.colorMagnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.colorMagnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.colorMagnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.colorMagnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.colorMagnitude10plus;
                break;
        }
        /** return the color id */
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
