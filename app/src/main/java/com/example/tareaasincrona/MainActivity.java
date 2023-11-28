package com.example.tareaasincrona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
    }
    public void startAsyncTask(View v){
        TareaAsincrona task = new TareaAsincrona();
        task.execute(5);
    }

    private class TareaAsincrona extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0 ;i< integers[0]; i++){
                publishProgress((i*100 / integers[0]));
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "termina hilo, completo!!!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }

}