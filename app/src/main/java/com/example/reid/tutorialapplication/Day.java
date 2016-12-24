package com.example.reid.tutorialapplication;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Reid on 12/23/2016.
 */



public class Day extends LinearLayout {

    private LinearLayout day;
    private ArrayList<Item> items;
    private static int dayCounter = 1;

    public Day(Context context){
        super(context);
        init(context);
    }

    public Day(Context context, ArrayList<Item> items) {
        super(context);
        this.items = items;
        init(context);
    }
    private void init(Context context){
        inflate(getContext(), R.layout.day, this);
        day = (LinearLayout) findViewById(R.id.day);
        TextView title = (TextView) findViewById(R.id.day_title);
        this.items = new ArrayList<Item>();
        title.setTypeface(MainActivity.Fonts.BODINI_XT);
        title.setText("11/"+dayCounter+"/16");
        dayCounter++;

        for (Item item : this.items) {
            day.addView(item);
        }
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }
    public void addItems(ArrayList<Item> items){
        for(Item item : items)
            day.addView(item);
        this.items.addAll(items);
    }
}
