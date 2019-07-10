package com.example.admin.paradiserestaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Content extends AppCompatActivity {

    ImageView imgstudytour, imgtravel, imgimmigration, imginvestment;
    TextView txtstudy, txttravel, txtimmgration, txtinvest;
    ImageView imgdemo;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_network_connection);

        imgstudytour = (ImageView)findViewById(R.id.imgStudytour);
        imgtravel = (ImageView)findViewById(R.id.imgTravel);
        imgimmigration = (ImageView)findViewById(R.id.imgImmigration);
        imginvestment = (ImageView)findViewById(R.id.imgInvestment);

        txtstudy = (TextView)findViewById(R.id.txtStudytour);
        txttravel = (TextView)findViewById(R.id.txttravel);
        txtimmgration = (TextView)findViewById(R.id.txtimmig);
        txtinvest = (TextView)findViewById(R.id.txtinvest);

        imgdemo = (ImageView)findViewById(R.id.imgDemo);



        imgdemo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main2Content.this, StudyTourActivity.class);
                startActivity(intent);

            }
        });



        imgstudytour.setClickable(true);
        imgstudytour.bringToFront();
        imgstudytour.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), StudyTourActivity.class);
                startActivity(intent);

            }
        });







    }

  /* public void imageClick (View view)
    {

    }*/

    }
