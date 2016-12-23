package com.example.reid.tutorialapplication;

import android.content.Context;
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

    public Item(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Item(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView title;
    private TextView detail;

    private void init(){
        inflate(getContext(), R.layout.item, this);
        title = (TextView) findViewById(R.id.item_title);
        detail = (TextView) findViewById(R.id.item_detail);
    }
}
