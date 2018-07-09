package com.example.nikolay.databaseapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;

    EditText etName;
    EditText etLastname;
    EditText etYear;

    Button btnSave;
    Button btnDel;

    Button btnAdd;
    String name;
    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        etName = (EditText) findViewById(R.id.etName);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etYear = (EditText) findViewById(R.id.etYear);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = (long) extras.getLong("id");
        }

        if (userId > 0) {
            userCursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE + " WHERE " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            etName.setText(userCursor.getString(1));
            etLastname.setText(userCursor.getString(2));
            etYear.setText(String.valueOf(userCursor.getInt(3)));
            userCursor.close();
        } else {
            btnDel.setVisibility(View.GONE);
        }
    }

    public void btnSaveClick(View view) {
        ContentValues cv = new ContentValues();
        try {
            cv.put(DatabaseHelper.COLUMN_NAME, etName.getText().toString());
            cv.put(DatabaseHelper.COLUMN_LASTNAME, etLastname.getText().toString());
            cv.put(DatabaseHelper.COLUMN_YEAR, Integer.parseInt(etYear.getText().toString()));
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (userId > 0) {
            db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
        } else {
            db.insert(DatabaseHelper.TABLE, null, cv);
        }
        close();
    }

    public void btnDelClick(View view) {
        db.delete(DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        close();
    }

    private void close() {
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
