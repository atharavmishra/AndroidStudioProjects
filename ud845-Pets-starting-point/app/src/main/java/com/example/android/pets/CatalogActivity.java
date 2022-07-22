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
package com.example.android.pets;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.provider.UserDictionary;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private PetDbHelper mDbHelper;
    private static final int PRIVATE_LOADER = 0;
    PetCursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.recreate();
        setContentView(R.layout.activity_catalog);
        LoaderManager.getInstance(this).initLoader(PRIVATE_LOADER,null,this);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new PetDbHelper(this);
        ListView list= (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        list.setEmptyView(emptyView);

        mCursorAdapter = new PetCursorAdapter(this, null);
        list.setAdapter(mCursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri currentpeturi = ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, id);
                intent.setData(currentpeturi);
                startActivity(intent);
            }
        });




    }
    




    //private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        // Create and/or open a database to read from it

        //
//
//        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        Cursor cursor = db.query(PetContract.PetEntry.TABLE_NAME,null,null,null,null,null,null);
//       try {
//        // Display the number of rows in the Cursor (which reflects the number of rows in the
//        // pets table in the database).
//        displayView.setText("Number of rows in pets database table: " + cursor.getCount());
//        displayView.append("\n" + PetContract.PetEntry._ID + " ");
//        displayView.append(PetContract.PetEntry.COLUMN_PET_NAME + " ");
//        displayView.append(PetContract.PetEntry.COLUMN_PET_BREED + " ");
//        displayView.append(PetContract.PetEntry.COLUMN_PET_WEIGHT + " ");
//        displayView.append(PetContract.PetEntry.COLUMN_PET_GENDER + "\n");
//        int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
//        int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
//        int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
//        int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);
//        int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
//        while (cursor.moveToNext()) {
//            int currentID = cursor.getInt(idColumnIndex);
//            String currentName = cursor.getString(nameColumnIndex);
//            String currentbreed = cursor.getString(breedColumnIndex);
//            int currentweight = cursor.getInt(weightColumnIndex);
//            int currentgender = cursor.getInt(genderColumnIndex);
//            displayView.append(("\n" + currentID + "  -  " + currentName + "  -  " + currentbreed + "  -  " + currentweight + "  -             " + currentgender));
//        }
//
//
//
////
//    } finally
//
//    {
//        // Always close the cursor when you're done reading from it. This releases all its
//        // resources and makes it invalid.
//        cursor.close();
//    }



//}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                //insertPet();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String [] mprojection={PetContract.PetEntry._ID,PetContract.PetEntry.COLUMN_PET_NAME,PetContract.PetEntry.COLUMN_PET_BREED };

        return new CursorLoader(this,PetContract.PetEntry.CONTENT_URI,mprojection,null,null,null);
//
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
