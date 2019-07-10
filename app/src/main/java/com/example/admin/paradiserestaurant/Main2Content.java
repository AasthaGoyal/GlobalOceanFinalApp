package com.example.admin.paradiserestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Main2Content extends AppCompatActivity {

    Button txtstudytour, txttravel, txtimmig, txtinvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_network_connection);

        txtstudytour = (Button)findViewById(R.id.txtStudytour);
        txttravel = (Button)findViewById(R.id.txttravel);
        txtimmig = (Button)findViewById(R.id.txtimmig);
        txtinvest = (Button)findViewById(R.id.txtinvest);



    }

    }
