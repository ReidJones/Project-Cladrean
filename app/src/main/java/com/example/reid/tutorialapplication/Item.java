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
    public Item(Context context) {
        super(context);
        init();
    }
    public Item(Context context, String[] details) {
        super(context);
        init(details);
    }

    public Item(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Item(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LinearLayout main;
    private TextView title;
    private LinearLayout details;
    private Typeface font;

    public static final int NUM_OF_COLORS = 4;

    private void init(){
        inflate(getContext(), R.layout.item, this);
        main = (LinearLayout) findViewById(R.id.item);
        font = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        title = (TextView) findViewById(R.id.item_title);
        details = (LinearLayout) findViewById(R.id.item_detail);
    }
    private void init(String[] tasks){
        inflate(getContext(), R.layout.item, this);
        main = (LinearLayout) findViewById(R.id.item);
        
        font = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");

        title = (TextView) findViewById(R.id.item_title);
        title.setTypeface(font);

        details = (LinearLayout) findViewById(R.id.item_detail);
        for(int i = 0; i < tasks.length; i++){
            TextView task = new TextView(getContext());
            task.setText(tasks[i]);

            task.setTypeface(font);
            details.addView(task);
        }
    }
}
