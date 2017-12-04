package com.fluffy.samrith.lab6exercise;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestSqlite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);

        Button btnSave = (Button)findViewById(R.id.btnSave);
        Button btnRetrieve = (Button)findViewById(R.id.btnLoad);
        final EditText edtName = (EditText)findViewById(R.id.name);
        final EditText edtEmail = (EditText)findViewById(R.id.email);
        final TextView result = (TextView)findViewById(R.id.result);

        final DBAdapter db = new DBAdapter(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                db.open();
                if(db.insertContact(name,email)>0)
                    Toast.makeText(TestSqlite.this, "insert successfully ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(TestSqlite.this, "Cannot insert the contact", Toast.LENGTH_SHORT).show();
                db.close();

            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                Cursor c = db.getAllContacts();
                if(c.moveToFirst()){
                    String contacts ="";
                    do{
                         contacts +="Name = "+c.getString(1)+"\nEmail = "+c.getString(2)+"\n\n";
                    }while(c.moveToNext());
                    result.setText(contacts);
                }
                db.close();


            }
        });
    }

}
