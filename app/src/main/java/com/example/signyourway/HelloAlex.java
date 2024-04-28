package com.example.signyourway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelloAlex extends AppCompatActivity {
    TextView texteAlex;
    ImageView imageAlex;
    Button buttonAlex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello_alex);

        //imageAlex = findViewById(R.id.alexImg);
        texteAlex = findViewById(R.id.helloAlex);
        //buttonAlex = findViewById(R.id.letsStartAlex);
        buttonAlex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAlex = new Intent(HelloAlex.this, MainActivity.class);
                intentAlex.putExtra("avatar", "Alex");
                startActivity(intentAlex); // Start the activity
            }
        });
    }

}