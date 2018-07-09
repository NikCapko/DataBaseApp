package com.example.nikolay.databaseapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView userList;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public static final String USER_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        userList.setAdapter(Query.selectAll(db, databaseHelper, this));
    }

    @Override
    public void onResume() {
        super.onResume();
        userList.setAdapter(Query.selectAll(db, databaseHelper, this));
    }

    public void btnAddClick(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }
}
