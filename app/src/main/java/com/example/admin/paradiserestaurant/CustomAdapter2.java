package com.example.admin.paradiserestaurant;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter2 extends ArrayAdapter<String> {
    private String[] androidosnames;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomAdapter2(Activity context,    String[] androidosnames,  Bitmap[] bitmaps  ) {
        super(context, R.layout.loadimage, androidosnames);
        this.context = context;
        // this.urls = urls;
        this.bitmaps = bitmaps;
        this.androidosnames = androidosnames;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.loadimage, null, true);
        TextView androidos = (TextView) listViewItem.findViewById(R.id.tvandroidosnames);
        // TextView textView = (TextView) listViewItem.findViewById(R.id.tvurl);
        //  textView.setText(urls[position] );
        androidos.setText(androidosnames[position] );
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position], 100, 50, false));
        return  listViewItem;
    }
}
