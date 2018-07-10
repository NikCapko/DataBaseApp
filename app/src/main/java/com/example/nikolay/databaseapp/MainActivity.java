package com.example.nikolay.databaseapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.OnItemClickListener{

    private RecyclerView recyclerView;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public static final String USER_ID = "id";

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        users = Query.selectAll(db, databaseHelper);
        RecycleAdapter adapter = new RecycleAdapter(users);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(long id) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(MainActivity.USER_ID, id);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        users = Query.selectAll(db, databaseHelper);
        RecycleAdapter adapter = new RecycleAdapter(users);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void btnAddClick(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }
}
