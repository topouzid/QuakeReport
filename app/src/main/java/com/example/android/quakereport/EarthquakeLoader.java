package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    /** Query URL */
    private String mUrl;
//    private static final String ONLINE_JSON_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=10";


    @Override
    protected void onStartLoading() {
        Log.v("onStartLoading", "forceLoad");
        forceLoad();
    }

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (mUrl == null) {
            Log.v("loadInBackground", "No URL given (or taken). Exit without trying to read earthquakes...");
            return null;
        }
        try {
            Log.v("EQLoader Background", "extractEarthquakes (LOAD IN BACKGROUND)");
            // Perform the network request, parse the response, and extract a list of earthquakes.
            ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);
            return earthquakes;
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }
}