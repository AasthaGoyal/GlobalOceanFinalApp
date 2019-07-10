package com.example.admin.paradiserestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class PopWindowActivity extends AppCompatActivity {

    Spinner spinner;
    CustomAdapter adapter;
    String[] names = {"China - Mandarin","New Zealand - English"};
    int[] images = {R.drawable.manflag, R.drawable.nzflag};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new CustomAdapter(this, names, images);

        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {
                Toast.makeText(getApplicationContext(), names[i], Toast.LENGTH_SHORT).show();
            }
        });




    }
}
