package com.example.nikolay.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private String name;
    private String year;

    private ListView userList;
    private Button btnAdd;
    private Button btnUpdate;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    public static final String USER_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        userList = (ListView) findViewById(R.id.userList);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra(USER_ID, id);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        btnUpdateClick(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        btnUpdateClick(null);
    }

    public void btnAddClick(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public void btnUpdateClick(View view) {
        db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE, null);
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                cursor,
                new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_LASTNAME, DatabaseHelper.COLUMN_YEAR},
                new int[]{R.id.txt1, R.id.txt2, R.id.txt3},
                0);
        userList.setAdapter(adapter);
    }
}
