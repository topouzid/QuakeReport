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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        final ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

        earthquakes.add(new Earthquake("San Francisco", "Feb 2, 2017", 4.5f));
        earthquakes.add(new Earthquake("London", "Mar 28, 2018", 3.9f));
        earthquakes.add(new Earthquake("Tokyo", "Mar 29, 2018", 4.1f));
        earthquakes.add(new Earthquake("Mexico City", "Apr 4, 2018", 5.2f));
        earthquakes.add(new Earthquake("Moscow", "Apr 9, 2018", 5.0f));
        earthquakes.add(new Earthquake("Rio de Janeiro", "Apr 16, 2018", 6.3f));
        earthquakes.add(new Earthquake("Paris", "May 11, 2018", 2.1f));

        // Create a new custom {@link EarthquakeAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
