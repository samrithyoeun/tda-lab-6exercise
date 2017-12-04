package com.fluffy.samrith.lab6exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestDataStoreActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_data_store);

        Button btnSharePreference = (Button)findViewById(R.id.btnSharedPreference);
        Button btnInternalStorage = (Button)findViewById(R.id.btnInternalStorage);
        Button btnSql = (Button)findViewById(R.id.btnSql);

        btnSharePreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TestSharedPreferences.class);
                startActivity(i);
            }
        });

        btnInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TestIntenalStorage.class);
                startActivity(i);
            }
        });


        btnSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TestSqlite.class);
                startActivity(i);
            }
        });

    }
}
