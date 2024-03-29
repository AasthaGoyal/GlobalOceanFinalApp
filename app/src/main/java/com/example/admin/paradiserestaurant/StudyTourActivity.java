package com.example.admin.paradiserestaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
import java.net.URLConnection;

public class StudyTourActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView txtDesc;
    ListView listview;
    String url = ApplicationSettings.hostUrl + "studyTour.php";
    String featuresUrl = ApplicationSettings.hostUrl + "showFeatures.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_tour);

        layout = (LinearLayout)findViewById(R.id.layout);
        txtDesc = (TextView)findViewById(R.id.txtDesc);
        listview = (ListView)findViewById(R.id.listview);

        new studyTour().execute(url);
        new getFeatures().execute(featuresUrl);
    }


    class studyTour extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
              /* String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("bonnie", "UTF-8");
               data+= "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                                       URLEncoder.encode("bonnie","UTF-8");*/
                URLConnection con = url.openConnection();
                // con.setDoOutput(true);
              /* OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
               os.write(data);
               os.flush();*/
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                Log.d("Wow Found ", sb.toString());
            } catch (Exception e) {
                Log.d("EXception", e.getMessage());
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            txtDesc.setText(result);

        }
    }

    class getFeatures extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
            activities[i] = obj.getString("featureDescription");

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);

        listview.setAdapter(arrayAdapter);

    }


    }
