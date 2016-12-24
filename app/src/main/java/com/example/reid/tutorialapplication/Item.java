package com.example.reid.tutorialapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Reid on 12/22/2016.
 */

public class Item extends LinearLayout{

    public Item(Context context, String[] details) {
        super(context);
        init(details);
    }

    public LinearLayout main;
    private TextView title;
    private LinearLayout details;

    public static final int NUM_OF_COLORS = 4;

    private void init(String[] tasks){
        inflate(getContext(), R.layout.item, this);

        main = (LinearLayout) findViewById(R.id.item);
        title = (TextView) findViewById(R.id.item_title);
        title.setTypeface(MainActivity.Fonts.OPEN_SANS_BOLD);

        details = (LinearLayout) findViewById(R.id.item_detail);
        for(int i = 0; i < tasks.length; i++){
            TextView task = new TextView(getContext());
            task.setText(tasks[i]);

            task.setTypeface(MainActivity.Fonts.OPEN_SANS_BOLD);
            details.addView(task);
        }
    }
}
