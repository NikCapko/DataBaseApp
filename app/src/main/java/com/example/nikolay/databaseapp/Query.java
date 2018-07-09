package com.example.nikolay.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class Query {
    public static String[] selectById(SQLiteDatabase db, long userId) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE + " WHERE " +
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        String[] result = new String[]{cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getLong(3))};
        cursor.close();
        return result;
    }

    public static void deleteById(SQLiteDatabase db, long userId) {
        db.delete(DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public static SimpleCursorAdapter selectAll(SQLiteDatabase db, DatabaseHelper databaseHelper, Context ctx) {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                ctx,
                R.layout.list_item,
                cursor,
                new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_LASTNAME, DatabaseHelper.COLUMN_YEAR},
                new int[]{R.id.txt1, R.id.txt2, R.id.txt3},
                0);
        return adapter;
    }

    public static void updateById(String[] params, SQLiteDatabase db, long userId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, params[0]);
        cv.put(DatabaseHelper.COLUMN_LASTNAME, params[1]);
        cv.put(DatabaseHelper.COLUMN_YEAR, params[2]);

        db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
    }

    public static void insertById(String[] params, SQLiteDatabase db, long userId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, params[0]);
        cv.put(DatabaseHelper.COLUMN_LASTNAME, params[1]);
        cv.put(DatabaseHelper.COLUMN_YEAR, params[2]);

        db.insert(DatabaseHelper.TABLE, null, cv);
    }
}
