package com.example.android.inventoryappstage1.data;

import android.provider.BaseColumns;


public final class ClothesContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ClothesContract() {
    }

    /**
     * Inner class that defines constant values for the clothing database table.
     * Each entry in the table represents a single clothes.
     */
    public static final class ClothesEntry implements BaseColumns {

        /**
         * Name of database table for cloths
         */
        public final static String TABLE_NAME = "clothes";

        /**
         * Unique ID number for the clothes (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the clothes.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_CLOTHES_NAME = "name";

        /**
         * Price of the clothes.
         * <p>
         * Type: FLOAT
         */
        public final static String COLUMN_CLOTHES_PRICE = "price";

        /**
         * Quantity of the clothes.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_CLOTHES_QUANTITY = "quantity";

        /**
         * Supplier name of the clothes.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_CLOTHES_SUPPLIER = "supplier";

        /**
         * Supplier phone number of the clothes.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_CLOTHES_SUPPLIER_PHONE = "supplier phone";
    }
}


