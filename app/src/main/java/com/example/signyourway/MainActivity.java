package com.example.signyourway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button moveToText;
    private Button moveToSign;
    private Button moveToLearn;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        moveToText = findViewById(R.id.buttonx2);
        moveToSign = findViewById(R.id.buttonx1);
        moveToLearn = findViewById(R.id.buttonx3);


        moveToLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Learning.class);
                startActivity(intent);
            }
        });

        moveToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignToText.class);
                startActivity(intent); // Start the activity
            }
        });



        String avatar = getIntent().getStringExtra("avatar");
        if (avatar != null && avatar.equals("Mia")) {
            moveToSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TextToSign.class);
                    intent.putExtra("avatar", "Mia");
                    startActivity(intent);
                }
            });
        } else {
            moveToSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TextToSign.class);
                    intent.putExtra("avatar", "Alex");
                    startActivity(intent);
                }
            });
        }
    }
}
