package com.example.reid.tutorialapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     *
     *  Boilerplate start
     *
     */
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    /**
     *
     *  Boilerplate end
     *
     */
    /**
     *
     *  Start of actual code
     *
    */
    private Vibrator myVib;
    private LinearLayout body;
    private LayoutInflater inflater;
    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(R.dimen.item_width,wrapContent);
    private String[] testTasks = {"Test 1", "Test 2", "Test 3"};
    private static int colorCounter = 0;

    private Item createItem(Context context, String[] tasks){
        Item item = new Item(context, tasks);
        item.setGravity(Gravity.CENTER_HORIZONTAL);
        item.setOnClickListener(this);

        switch (colorCounter){
            case 0: //red
                item.main.setBackgroundResource(R.drawable.red_note);
                break;
            case 1: //orange
                item.main.setBackgroundResource(R.drawable.orange_note);
                break;
            case 2: //yellow
                item.main.setBackgroundResource(R.drawable.yellow_note);
                break;
            case 3: //green
                item.main.setBackgroundResource(R.drawable.green_note);
                break;
        }
        colorCounter = (colorCounter + 1 == Item.NUM_OF_COLORS) ? 0: colorCounter + 1;
        return item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        body = (LinearLayout) findViewById(R.id.body);

        body.addView(createItem(this, testTasks));

        mVisible = true;

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        mControlsView.setOnClickListener(this);
        mContentView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myVib.vibrate(25);
       if(v.getClass().equals(Item.class)){
           System.out.println("Item Clicked");
       }
       else{
           switch (v.getId()){
               case R.id.fullscreen_content_controls:
                   body.addView(createItem(v.getContext(), testTasks));
                   break;
               case R.id.fullscreen_content:
                   System.out.println("Clicked Body");
                   toggle();
                   break;
           }
       }
    }

    /**
     *
     * Boilerplate start
     *
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        /*
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                */
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}