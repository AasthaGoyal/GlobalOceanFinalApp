package com.example.admin.paradiserestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    String menu;
    LinearLayout layout;
    TextView txtMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout = (LinearLayout) findViewById(R.id.layout);
        txtMenu = (TextView) findViewById(R.id.txtMenu);

        String menuType = getIntent().getExtras().getString("menuType");
        txtMenu.setText(menuType + "Menu");


    }
}
