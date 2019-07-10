package com.example.admin.paradiserestaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityDetailsActivity extends AppCompatActivity {

    TextView heading;
    ListView listview, txtHeading;
    ListView btnDuration;
    String link =  ApplicationSettings.hostUrl + "activityDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        heading = (TextView)findViewById(R.id.heading);
        txtHeading = (ListView)findViewById(R.id.txtheading);
        btnDuration = (ListView)findViewById(R.id.duration);
        listview = (ListView)findViewById(R.id.listview);


        Bundle bundle = getIntent().getExtras();

        Long name = bundle.getLong("id");
        heading.setText(name.toString());

       if(name ==0)
       {

       }


    }

    class getDetails extends AsyncTask<String, Void, String> {

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
        String[] duration = new String[jsonArray.length()];
        String[] description = new String[jsonArray.length()];

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            activities[i] = obj.getString("activityName");
            description[i] = obj.getString("aDescription");
            duration[i] = obj.getString("duration");

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);
        listview.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, duration);
        btnDuration.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapte3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, description);
        txtHeading.setAdapter(arrayAdapte3);


    }

}
