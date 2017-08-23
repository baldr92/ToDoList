package com.lpn.todolist;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;


public class CreateTaskActivity extends AppCompatActivity {

   public final String LOG_TAG = "myLogs";
    public final String FILENAME = "file";
    public final String DIR_SD = "MyFiles";
     public final String FILENAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }

    public void onClickSendTask(View view) {
        EditText editText = (EditText) findViewById(R.id.input_text);

        String task;
        task = editText.getText().toString();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        gson.toJson(task);
        editText.clearFocus();

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(task);
            bw.close();
            Log.d(LOG_TAG,"Файл записан");
            Toast toast = Toast.makeText(getApplicationContext(), "Задача создана!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
