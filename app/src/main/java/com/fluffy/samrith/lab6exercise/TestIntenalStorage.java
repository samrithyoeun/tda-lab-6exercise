package com.fluffy.samrith.lab6exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestIntenalStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intenal_storage);

        Button btnSave = (Button)findViewById(R.id.btnSave);
        Button btnRetrieve = (Button)findViewById(R.id.btnLoad);
        final EditText edtName = (EditText)findViewById(R.id.name);
        final EditText edtEmail = (EditText)findViewById(R.id.email);
        final TextView result = (TextView)findViewById(R.id.result);
        final int READ_BLOCK_SIZE = 100;


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                try {
                    FileOutputStream fOut = openFileOutput("contact.txt",
                            MODE_APPEND|MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    //---write the string to the file---
                    try {
                        osw.write("Name  = "+name+"\n");
                        osw.write("Email = "+email+"\n\n");
                    } catch (IOException e) {
                        e.printStackTrace(); }
                    osw.flush();
                    osw.close();
                    //---display file saved message---
                    Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
                    //---clears the EditText---
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });


        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fIn = openFileInput("contact.txt");
                    InputStreamReader isr = new InputStreamReader(fIn);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    String s = "";
                    int charRead;

                    while ((charRead = isr.read(inputBuffer)) > 0)
                    {
                        //---convert the chars to a String---
                        String readString = String.copyValueOf(inputBuffer, 0, charRead);
                        s += readString;
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }

                    result.setText(s);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });


    }
}
