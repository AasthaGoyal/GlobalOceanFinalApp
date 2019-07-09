package com.example.admin.paradiserestaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class CheckUserActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnCheck;
    LinearLayout layout;
    String url = ApplicationSettings.hostUrl + "checkUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        layout = (LinearLayout)findViewById(R.id.layout);


        new checkUser().execute(url);

    }

   class checkUser extends AsyncTask<String, Void, String>
   {

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected String doInBackground(String... strings) {

           StringBuilder sb = new StringBuilder();
           try
           {
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
               while((line = reader.readLine()) != null)
               {
                   sb.append(line);
               }
               Log.d("Wow Found " , sb.toString());
           }
           catch(Exception e)
           {
               Log.d("EXception", e.getMessage());
           }

           return sb.toString();
       }

       @Override
       protected void onPostExecute(String result) {

           if(result != "")
           {
               etPassword.setText("The result is " + result);
           }
           else
           {
               etPassword.setText("the result is null");
           }


       }



   }
}
