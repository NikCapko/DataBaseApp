package com.example.nikolay.databaseapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public static List selectAll(SQLiteDatabase db, DatabaseHelper databaseHelper) {
        List<User> users = new ArrayList<>();
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE + " ;", null);

        while (cursor.moveToNext()) {
            User user = new User();
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LASTNAME));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_YEAR));
            user.setId(id);
            user.setName(name);
            user.setLastname(lastname);
            user.setYear(year);
            users.add(user);
        }

        return users;
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
