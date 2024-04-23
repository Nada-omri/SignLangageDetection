package com.example.signyourway;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFood extends AppCompatActivity {
    TextView text, introuvable;
    Button generate;
    ImageView result;
    public static ArrayList<ModelClass> listOfQues;
    DatabaseReference databasereference;
    List<ModelClass> allQuestionsList;
    ModelClass modelclass;
    int index = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_food);
        fourthFirebaseApp();
        listOfQues = new ArrayList<>();
        FirebaseApp fourthApp = FirebaseApp.getInstance("fourth");
        FirebaseDatabase fourthDatabase = FirebaseDatabase.getInstance(fourthApp);
        databasereference = fourthDatabase.getReference("Question");
        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfQues.clear(); // Clear the existing list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClass modelclass = dataSnapshot.getValue(ModelClass.class);
                    listOfQues.add(modelclass);
                }
                allQuestionsList = listOfQues;
                if (allQuestionsList != null && !allQuestionsList.isEmpty()) {
                    Collections.shuffle(allQuestionsList);
                    modelclass = allQuestionsList.get(index);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error fetching data", error.toException());
            }
        });

        text = findViewById(R.id.input_text);
        generate = findViewById(R.id.generate_btn);
        result = findViewById(R.id.image_view);
        introuvable = findViewById(R.id.introuvable);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = text.getText().toString();
                // Check if search text is not null or empty
                if (!search.isEmpty()) {
                    checkAnswer(search);
                } else {
                    // Optionally, handle the case where search text is empty
                    introuvable.setText("Please enter a search term.");
                }
            }
        });
    }

    private void checkAnswer(String userInput) {
        for (ModelClass question : listOfQues) {
            if (userInput.equals(question.getAns().toLowerCase().trim())) {
                // Found a match, load the image
                Picasso.get()
                        .load(question.gettURL()) // Assuming gettURL() returns the correct image URL
                        .resize(300, 200)
                        .centerCrop()
                        .into(result); // Use the correct ImageView object
                return; // Exit the loop once a match is found
            }
        }
        // If no match is found, set the text of the TextView
        introuvable.setText("introuvable");
    }

    private void fourthFirebaseApp() {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:237603043126:android:29f570eb0cbc3b102ce597")
                .setApiKey("AIzaSyDlWnR4_jMau0nfjppkpp1WPX4p5heOECk")
                .setDatabaseUrl("https://food-20bbf-default-rtdb.firebaseio.com")
                .build();

        try {
            FirebaseApp.initializeApp(this, options, "fourth");
        } catch (Exception e) {
            Log.d("Firebase error", "App already exists");
        }
    }
}
