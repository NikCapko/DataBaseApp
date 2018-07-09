package com.example.nikolay.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // database name
    private static final String DATABASE_NAME = "database.db";
    // version database
    private static final int VERSION = 1;
    // table name
    public static final String TABLE = "users";
    // column name
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_YEAR = "year";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_LASTNAME + " TEXT, " + COLUMN_YEAR + " INTEGER);");
        // add start data
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Tom', 'Smit', 1981);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Jerry', 'Wolt', 1975);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Garry', 'Ford', 1970);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Ron', 'Flip', 1984);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Terry', 'Frost', 1976);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Josh', 'Rin', 1980);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Mark', 'Raspel', 1981);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Harold', 'Finch', 1979);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Morgan', 'Finegan', 1982);");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_LASTNAME + ", " + COLUMN_YEAR + ") VALUES ('Max', 'Kramer', 1973);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
