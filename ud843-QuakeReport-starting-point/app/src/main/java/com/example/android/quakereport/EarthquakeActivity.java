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
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;

import android.app.LoaderManager;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Word>> {

//    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=3&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private WordAdapter adapter;
    private TextView mEmptyStateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        TextView txt = findViewById(R.id.empty_view);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else
        txt.setText("No Internet available");
    }

    private void updateUi(ArrayList<Word> earthquakes) {
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new WordAdapter(this, earthquakes);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Word currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<Word>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(EarthquakeActivity.this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Word>> loader, List<Word> data) {
        if (data==null){
            mEmptyStateTextView.setText(R.string.no_earthquakes);
        }
        if (data != null && !data.isEmpty()) {

            updateUi((ArrayList<Word>) data);

            ProgressBar progressbar = (ProgressBar)findViewById(R.id.progress);
            progressbar.setVisibility(View.GONE);


        }

    }

    @Override
    public void onLoaderReset(Loader<List<Word>> loader) {
        adapter.clear();
    }
}



//    private class EarthquakeAsyncTask extends AsyncTask<String , Void, ArrayList<Word>> {
//
//        @Override
//        protected ArrayList<Word> doInBackground(String... urls) {
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//            ArrayList<Word> earthquake = QueryUtils.extractEarthquakes(urls[0]);
//            return earthquake;
//        }
//        protected void onPostExecute(ArrayList<Word> result){
//
//            if (result == null) {
//                return;
//            }
//
//            updateUi(result);
//        }
//    }






