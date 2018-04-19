package com.example.android.inventoryappstage1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappstage1.data.ClothesContract.ClothesEntry;
import com.example.android.inventoryappstage1.data.ClothesDbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ClothesDbHelper DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper = new ClothesDbHelper(this);

        Button addDummyDataButton = findViewById(R.id.add_button);
        addDummyDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long nerRow = insertItem();
                if (nerRow > 0)
                    queryDatabase();
                else
                    Toast.makeText(MainActivity.this, "Error inserting data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        queryDatabase();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void queryDatabase() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = DbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ClothesEntry._ID,
                ClothesEntry.COLUMN_CLOTHES_NAME,
                ClothesEntry.COLUMN_CLOTHES_PRICE,
                ClothesEntry.COLUMN_CLOTHES_QUANTITY,
                ClothesEntry.COLUMN_CLOTHES_SUPPLIER,
                ClothesEntry.COLUMN_CLOTHES_SUPPLIER_PHONE};


        // Perform a query on the pets table
        Cursor cursor = db.query(
                ClothesEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = findViewById(R.id.text);

        try {
            displayView.setText("The table contains " + cursor.getCount() + " clothes.\n\n");
            displayView.append(ClothesEntry._ID + " - " +
                    ClothesEntry.COLUMN_CLOTHES_NAME + " - " +
                    ClothesEntry.COLUMN_CLOTHES_PRICE + " - " +
                    ClothesEntry.COLUMN_CLOTHES_QUANTITY + " - " +
                    ClothesEntry.COLUMN_CLOTHES_SUPPLIER + " - " +
                    ClothesEntry.COLUMN_CLOTHES_SUPPLIER_PHONE + " - " + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ClothesEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ClothesEntry.COLUMN_CLOTHES_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ClothesEntry.COLUMN_CLOTHES_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ClothesEntry.COLUMN_CLOTHES_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(ClothesEntry.COLUMN_CLOTHES_SUPPLIER);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ClothesEntry.COLUMN_CLOTHES_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                Float currentPrice = cursor.getFloat(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplier + " - " +
                        currentSupplierPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded item data into the database. For debugging purposes only.
     */
    private long insertItem() {
        // Gets the database in write mode
        SQLiteDatabase db = DbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(ClothesEntry.COLUMN_CLOTHES_NAME, "Skirts");
        values.put(ClothesEntry.COLUMN_CLOTHES_PRICE, "10.5");
        values.put(ClothesEntry.COLUMN_CLOTHES_QUANTITY, "8");
        values.put(ClothesEntry.COLUMN_CLOTHES_SUPPLIER, "constantinos");
        values.put(ClothesEntry.COLUMN_CLOTHES_SUPPLIER_PHONE, "123");

        return db.insert(ClothesEntry.TABLE_NAME, null, values);
    }
}