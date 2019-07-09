package com.example.admin.paradiserestaurant;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckNetworkConnection extends AppCompatActivity {

    Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_network_connection);

        btnCheck = (Button)findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(AmIOnline() == true)
                {
                    Toast.makeText(CheckNetworkConnection.this, "Yes, you are  online", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CheckNetworkConnection.this, "No, you are not online", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean AmIOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if((networkInfo != null) && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;

    }
}
