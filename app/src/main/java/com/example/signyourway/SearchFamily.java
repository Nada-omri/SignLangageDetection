package com.example.signyourway;

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

public class SearchFamily extends AppCompatActivity {
    TextView text;
    Button generate;
    ImageView result;
    public static ArrayList<ModelClass> listOfQues;
    DatabaseReference databasereference;
    List<ModelClass> allQuestionsList;
    ModelClass modelclass;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_family);
        fifthFirebaseApp();
        listOfQues = new ArrayList<>();
        FirebaseApp fifthApp = FirebaseApp.getInstance("fifth");
        FirebaseDatabase fifthDatabase = FirebaseDatabase.getInstance(fifthApp);
        databasereference = fifthDatabase.getReference("Question");
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


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = text.getText().toString();
                // Check if search text is not null or empty
                if (!search.isEmpty()) {
                    checkAnswer(search);
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
            else{
                result.setImageResource(R.drawable.img);
            }
        }

    }

    private void fifthFirebaseApp() {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:1049750937678:android:69529d1bd5f9d58099a31f")
                .setApiKey("AIzaSyB4eQ9U33DSwiSU13OxoZ9tAYQErsiF3kE")
                .setDatabaseUrl("https://family-35c6c-default-rtdb.firebaseio.com")
                .build();

        try {
            FirebaseApp.initializeApp(this, options, "fifth");
        } catch (Exception e) {
            Log.d("Firebase error", "App already exists");
        }
    }
}
