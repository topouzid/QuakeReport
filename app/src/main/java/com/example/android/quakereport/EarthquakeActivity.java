/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Browser;
import android.content.Loader;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<Earthquake>>, DownloadCallback{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String ONLINE_JSON_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minlatitude=34.7&maxlatitude=41.8&minlongitude=19.5&maxlongitude=28.3&minmag=3&limit=20";
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private TextView mEmptyTextView;
    private  ProgressBar mProgressBar;

    @Override
    public void finishDownloading() {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void updateFromDownload(Object result) {

    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {

    }

    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, Bundle bundle) {
        // Create a new loader for the given URL
        Log.v("onCreateLoader", "Create new loader for " + ONLINE_JSON_URL);
//        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
//        getActiveNetworkInfo();
//
        mProgressBar.setVisibility(View.VISIBLE);
        mEmptyTextView.setText("Communicating with the scientists. Please wait...");
        return new EarthquakeLoader(this, ONLINE_JSON_URL);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        /** TODO: complete this LoaderReset **/
        // Loader reset, so we can clear out our existing data.
//        mAdapter(clear);
//        updateUi(null);
        Log.v("onLoaderReset", "Loader reset: Cleaning UI...");
        cleanUi();
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> earthquakes) {
        // clear out our existing data
//        updateUi(null);
        if (earthquakes == null) {
            Log.v("onLoadFinished", "No earthquakes on array, exit without doing anything");
            mEmptyTextView.setText("Communicating with the scientists. Please wait...");
            return;
        }
        Log.v("onLoadFinished", "Cleaning UI, showing new earthquakes data to UI");
        mProgressBar.setVisibility(View.GONE);
        cleanUi();
//        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mEmptyTextView.setText("No significant earthquakes in your region");
        updateUi(earthquakes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.loading_progress);

        // Create a fake list of earthquake locations.
//        final ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
//
//        earthquakes.add(new Earthquake("San Francisco", "Feb 2, 2017", 4.5));
//        earthquakes.add(new Earthquake("London", "Mar 28, 2018", 3.9));
//        earthquakes.add(new Earthquake("Tokyo", "Mar 29, 2018", 4.1));
//        earthquakes.add(new Earthquake("Mexico City", "Apr 4, 2018", 5.2));
//        earthquakes.add(new Earthquake("Moscow", "Apr 9, 2018", 5.0));
//        earthquakes.add(new Earthquake("Rio de Janeiro", "Apr 16, 2018", 6.3));
//        earthquakes.add(new Earthquake("Paris", "May 11, 2018", 2.1));

//        EarthquakeJsonAsyncTask task = new EarthquakeJsonAsyncTask();
//        task.execute(ONLINE_JSON_URL);

        if (getActiveNetworkInfo()==null || !getActiveNetworkInfo().isConnected()) {
            mProgressBar.setVisibility(View.GONE);
            mEmptyTextView.setText("No network connection");
            return;
        }
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        Log.v("initLoader", "Initializing loader...");

//        try {
//
////            final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(ONLINE_JSON_URL);
//            // Create a new custom {@link EarthquakeAdapter} of earthquakes
//            EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
//            // Find a reference to the {@link ListView} in the layout
//            ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//            // Set the adapter on the {@link ListView}
//            // so the list can be populated in the user interface
//            earthquakeListView.setAdapter(adapter);
//
//
//
//            earthquakeListView.setOnItemClickListener(
//                    new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            /** get the {@link Earthquake} object at the current position where the user clicked on */
//                            Earthquake currentEarthquake = earthquakes.get(position);
//                            /** get the url from the current earthquake */
//                            String url = currentEarthquake.getUrl();
//                            Log.v("EarthquakeActivity", "URL: " + url);
//                            /** create a browser intent */
//                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//                            /** set the URL and send it to the browser */
//                            browserIntent.setData(Uri.parse(url));
//                            /** start browser */
//                            startActivity(browserIntent);
//                        }
//                    }
//            );
//        } catch (IOException e) {
//            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
//        }
    }

//    /**
//     * {@link AsyncTask} to perform the network request on a background thread, and then
//     * update the UI with the earthquakes in the response.
//     */
//    private abstract class EarthquakeJsonAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>>{
//
//        @Override
//        protected ArrayList<Earthquake> doInBackground(String... urls) {
//            if (urls.length > 0) {
//                try {
//                    ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(urls[0]);
//                    return earthquakes;
//                } catch (IOException e) {
//                    Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
//                }
//
//            }
//            return null;
//        }
//
//        @Override
//        public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> earthquakes) {
//            if (earthquakes == null) {
//                return;
//            }
//            updateUi(earthquakes);
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
//            if (earthquakes == null) {
//                return;
//            }
//            updateUi(earthquakes);
//        }
//    }

    private void updateUi(final ArrayList<Earthquake> earthquakes) {
        // Create a new custom {@link EarthquakeAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        int eqCount = earthquakes.size();
        Log.v("updateUi", "EARTHQUAKE COUNT: " + eqCount);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setEmptyView(mEmptyTextView);
                    earthquakeListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /** get the {@link Earthquake} object at the current position where the user clicked on */
                            Earthquake currentEarthquake = earthquakes.get(position);
                            /** get the url from the current earthquake */
                            String url = currentEarthquake.getUrl();
                            Log.v("EarthquakeActivity", "URL: " + url);
                            /** create a browser intent */
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                            /** set the URL and send it to the browser */
                            browserIntent.setData(Uri.parse(url));
                            /** start browser */
                            startActivity(browserIntent);
                        }
                    }
            );

    }

    private void cleanUi() {
//        EarthquakeAdapter adapter;
//        adapter.clear();
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(null);
    }
}