package com.example.nikolay.databaseapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private DatabaseHelper sqlHelper;
    private SQLiteDatabase db;
    private long userId = 0;

    private EditText etName;
    private EditText etLastname;
    private EditText etYear;

    private Button btnSave;
    private Button btnDel;

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
            etName.setText(Query.selectById(db, userId)[0]);
            etLastname.setText(Query.selectById(db, userId)[1]);
            etYear.setText(String.valueOf(Query.selectById(db, userId)[2]));
        } else {
            btnDel.setVisibility(View.GONE);
        }
    }

    public void btnSaveClick(View view) {
        try {
            String[] params = new String[]{
                    etName.getText().toString(),
                    etLastname.getText().toString(),
                    etYear.getText().toString()};

            if (userId > 0) {
                Query.updateById(params, db, userId);
            } else {
                Query.insertById(params, db, userId);
            }
            close();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDelClick(View view) {
        Query.deleteById(db, userId);
        close();
    }

    private void close() {
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
