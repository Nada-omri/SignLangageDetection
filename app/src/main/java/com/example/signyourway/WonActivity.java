package com.example.signyourway;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WonActivity extends AppCompatActivity {
    TextView textresult;
    int correct,wrong;
    ImageView image;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_won);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);
        image=findViewById(R.id.resultImage);
        textresult=findViewById(R.id.resultText);
        textresult.setText(correct+"/10");
        if (correct<5){
            image.setImageResource(R.drawable.loser);

        }
        else{
            image.setImageResource(R.drawable.winner);
        }


    }
}