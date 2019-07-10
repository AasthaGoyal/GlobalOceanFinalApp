package com.example.admin.paradiserestaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class showActivitiesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listview;
    String link =  ApplicationSettings.hostUrl + "showActivities.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activities);

       new getActivities().execute(link);
       listview = (ListView)findViewById(R.id.listview);

       listview.setOnItemClickListener( this);
    }

    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putLong("id", id);
        intent.setClass(this, ActivityDetailsActivity.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }


    class getActivities extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            try
            {
                loadIntoListview(s);
            }
            catch(JSONException ex)
            {
                ex.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... voids) {
            try {

                URL url = new URL(voids[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();

                //We will use a buffered reader to read the string from service
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                //A simple string to read values from each line
                String json;

                //reading until we don't find null
                while ((json = bufferedReader.readLine()) != null) {

                    //appending it to string builder
                    sb.append(json + "\n");
                }

                //finally returning the read string
                return sb.toString().trim();
            } catch (Exception e) {
                return null;
            }
        }
    }


    private void loadIntoListview(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        String[] activities = new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            activities[i] = obj.getString("activityName");

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);

        listview.setAdapter(arrayAdapter);

    }



}
