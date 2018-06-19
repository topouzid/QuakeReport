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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String ONLINE_JSON_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

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

        EarthquakeJsonAsyncTask task = new EarthquakeJsonAsyncTask();
        task.execute(ONLINE_JSON_URL);

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

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the earthquakes in the response.
     */
    private class EarthquakeJsonAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length > 0) {
                try {
                    ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(urls[0]);
                    return earthquakes;
                } catch (IOException e) {
                    Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
//            super.onPostExecute(earthquakes);
            if (earthquakes == null) {
                return;
            }
            updateUi(earthquakes);
        }
    }


    private void updateUi(final ArrayList<Earthquake> earthquakes) {
        // Create a new custom {@link EarthquakeAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
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
}
