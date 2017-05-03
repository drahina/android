package com.example.diffogeorges.parkgmtl2905;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xpack on 03/05/17.
 */

public class Reservations extends Activity {
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservations);
        Intent intent = getIntent();
//        user = intent.getExtras().getString("user");
        UserLoginTask u=new UserLoginTask();
        u.execute();

    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {



        @Override
        protected Boolean doInBackground(Void... params) {
            // Start of merge

            String login_url = "http://www-ens.iro.umontreal.ca/~kooistrc/android/reserve.php";

            try {
                Log.e("e", "test ");
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("e", " "+httpURLConnection );

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                Log.e("e", "" + result);
                bufferedReader.close();
                ;
                inputStream.close();
                httpURLConnection.disconnect();
                return result.equals("true");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // End of insert

            return false; // J'ai fait des changements ici...
        }

        @Override
        protected void onPostExecute(final Boolean success) {


        }
    }
}
