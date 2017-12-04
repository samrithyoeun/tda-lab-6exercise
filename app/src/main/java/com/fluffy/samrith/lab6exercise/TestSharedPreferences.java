package com.fluffy.samrith.lab6exercise;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestSharedPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_preferences);

        Button btnSave = (Button)findViewById(R.id.btnSave);
        Button btnRetrieve = (Button)findViewById(R.id.btnLoad);
        final EditText edtName = (EditText)findViewById(R.id.name);
        final EditText edtEmail = (EditText)findViewById(R.id.email);
        final TextView result = (TextView)findViewById(R.id.result);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences appPrefs = getSharedPreferences("com.fluffy.samrith.lab6excercise_preference", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = appPrefs.edit();
                prefsEditor.putString("name", edtName.getText().toString());
                prefsEditor.putString("email", edtEmail.getText().toString());
                if(prefsEditor.commit()){
                    Toast.makeText(TestSharedPreferences.this, "Added to shared preference successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences appPrefs = getSharedPreferences("com.fluffy.samrith.lab6excercise_preference", MODE_PRIVATE);
                String name = appPrefs.getString("name", "" );
                String email = appPrefs.getString("email","");

                result.setText("Name : "+name+"\nEmail : "+email);


            }
        });
    }

}
