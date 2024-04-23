package com.example.signyourway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;

public class Learning extends AppCompatActivity {
    CardView cardViewActions, cardViewFeelings, cardViewFood,cardViewFamily;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);

        // Assuming you have a CardView with the id "cardViewActions" in your layout
        cardViewActions = findViewById(R.id.cardViewActions);
        cardViewFeelings = findViewById(R.id.cardViewFeelings);
        cardViewFood = findViewById(R.id.cardViewFood);
        cardViewFamily=findViewById(R.id.cardViewFamily);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView9 = findViewById(R.id.textView9);
        TextView actions=findViewById(R.id.actions);
        ImageView image=findViewById(R.id.imageView4);
        View divider =findViewById(R.id.divider2);
        cardViewActions.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale up the CardView when touched
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                        return true; // Consume the touch event
                    case MotionEvent.ACTION_UP:
                        // Scale down the CardView when touch is released
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        // Start the Actions activity
                        if (textView7.getVisibility() == View.GONE) {
                            textView7.setVisibility(View.VISIBLE);
                            textView9.setVisibility(View.VISIBLE);
                            actions.setVisibility(View.GONE);
                            image.setVisibility(View.GONE);
                            divider.setVisibility(View.VISIBLE);
                            cardViewActions.setBackgroundColor(0xFFD3D3D3);
                        } else {
                            textView7.setVisibility(View.GONE);
                            textView9.setVisibility(View.GONE);
                            divider.setVisibility(View.GONE);

                            actions.setVisibility(View.VISIBLE);
                            image.setVisibility(View.VISIBLE);
                        }
                        return true; // Consume the touch event
                }
                return false;
            }
        });
        cardViewFamily.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale up the CardView when touched
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                        return true; // Consume the touch event
                    case MotionEvent.ACTION_UP:
                        // Scale down the CardView when touch is released
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        // Start the Actions activity
                        Intent intent2 = new Intent(Learning.this, SearchFamily.class);
                        startActivity(intent2);
                        return true; // Consume the touch event
                }
                return false;
            }
        });
        cardViewFeelings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale up the CardView when touched
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                        return true; // Consume the touch event
                    case MotionEvent.ACTION_UP:
                        // Scale down the CardView when touch is released
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        // Start the Actions activity
                        Intent intent3 = new Intent(Learning.this, SearchFeelings.class);
                        startActivity(intent3);
                        return true; // Consume the touch event
                }
                return false;
            }
        });
        cardViewFood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale up the CardView when touched
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                        return true; // Consume the touch event
                    case MotionEvent.ACTION_UP:
                        // Scale down the CardView when touch is released
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        // Start the Actions activity
                        Intent intent4 = new Intent(Learning.this, SearchFood.class);
                        startActivity(intent4);
                        return true; // Consume the touch event
                }
                return false;
            }
        });
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Learning.this, Search.class);
                startActivity(intent); // Start the activity
            }
        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Learning.this, Actions.class);
                startActivity(intent); // Start the activity
            }
        });

    }}
