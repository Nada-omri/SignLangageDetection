package com.example.signyourway;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

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

public class Feelings extends AppCompatActivity {
    public static ArrayList<ModelClass> listOfQues;
    DatabaseReference databasereference;

    private ProgressBar progressBar;
    private int progressStatus = 50; // Start with 2 seconds
    private CountDownTimer countDownTimer;
    List<ModelClass> allQuestionsList;
    private Dialog progressDialog;
    ModelClass modelclass;
    int index = 0;
    int correctCount=0;
    int wrongCount=0;
    TextView optionA, optionB, optionC, optionD;
    ImageView cardquestion;
    CardView cardOA, cardOB, cardOC, cardOD;
    Button nextbtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feelings);

            showProgressDialog();

            // Initialize the secondary Firebase project
        thirdFirebaseApp();
        /*FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(FirebaseDatabase.getInstance("secondary").getReference().child("Question"), ModelClass.class)
                        .build();
        questionadapter= new QuestionAdapter(options);
        recyclerView.setAdapter(questionadapter);*/
            listOfQues = new ArrayList<>();
            FirebaseApp thirdApp = FirebaseApp.getInstance("third");
            FirebaseDatabase thirdDatabase = FirebaseDatabase.getInstance(thirdApp);
            databasereference = thirdDatabase.getReference("Question");
            databasereference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listOfQues.clear(); // Clear the existing list
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        ModelClass modelclass=dataSnapshot.getValue(ModelClass.class);
                        listOfQues.add(modelclass);
                    }
                    allQuestionsList = listOfQues;
                    if (allQuestionsList != null && !allQuestionsList.isEmpty()) {
                        Collections.shuffle(allQuestionsList);
                        modelclass = allQuestionsList.get(index);
                        setAllData();
                        progressDialog.dismiss();

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Hooks();

            progressBar.setMax(progressStatus);

            // Initialize and start the CountDownTimer
            countDownTimer = new CountDownTimer(progressStatus * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    progressStatus--; // Decrease the progress value
                    progressBar.setProgress(progressStatus);
                }

                @Override
                public void onFinish() {
                    // Create and show the dialog when the timer finishes
                    Dialog dialog = new Dialog(Feelings.this);
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                    dialog.setContentView(R.layout.time_out_dialog);

                    dialog.setTitle("Time's Up");

                    // Customize dialog dimensions
                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT; // Set width to match parent
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT; // Set height to wrap content
                    dialog.getWindow().setAttributes(params);
                    dialog.findViewById(R.id.dialogTimeoutButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            // Restart the countdown timer
                            progressStatus = 2; // Reset the progress status
                            progressBar.setProgress(progressStatus); // Reset the progress bar
                            countDownTimer.start(); // Restart the countdown timer
                        }
                    });

                    dialog.show();
                }

            }.start(); // Start the CountDownTimer
        }
        private void showProgressDialog() {
            progressDialog = new Dialog(this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setCancelable(false);
            progressBar = progressDialog.findViewById(R.id.progressBar);
            progressDialog.show();
        }
        private void thirdFirebaseApp() {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApplicationId("1:733888633230:android:02bf28b12ce92bea36ecdf")
                    .setApiKey("AIzaSyBXmugZPvwabU5d2zV9MHn7o5crcukmU20")
                    .setDatabaseUrl("https://feelings-98a53-default-rtdb.firebaseio.com")
                    .build();

            try {
                FirebaseApp.initializeApp(this, options, "third");
            } catch (Exception e) {
                Log.d("Firebase error", "App already exists");
            }
        }

        @SuppressLint("WrongViewCast")
        private void Hooks() {
            progressBar = findViewById(R.id.progressBar);
            cardOA = findViewById(R.id.resp1);
            cardOB = findViewById(R.id.resp2);
            cardOC = findViewById(R.id.resp3);
            cardOD = findViewById(R.id.resp4);
            optionA = findViewById(R.id.optionA);
            optionB = findViewById(R.id.optionb);
            optionC = findViewById(R.id.optionc);
            optionD = findViewById(R.id.optiond);
            nextbtn=findViewById(R.id.nextbutton);

            cardquestion=findViewById(R.id.card_question);
        }

        private void setAllData() {
            // Load the image into the ImageView using Picasso
            Picasso.get()
                    .load(modelclass.gettURL())
                    .resize(300, 200) // Resize the image to fit the ImageView
                    .centerCrop() // Center crop the image to maintain aspect ratio
                    .into(cardquestion); // cardquestion is your ImageView

            optionA.setText(modelclass.getOA());
            optionB.setText(modelclass.getOB());
            optionC.setText(modelclass.getOC());
            optionD.setText(modelclass.getOD());
        }











        public void Correct(CardView cardOA){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.Green));
            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    correctCount ++;
                    index++;
                    modelclass=allQuestionsList.get(index);
                    setAllData();
                    resetColor();
                }
            });


        }
        public void Wrong(CardView cardOA){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.red));
            nextbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    wrongCount ++;
                    if (index<allQuestionsList.size()-1){
                        index++;
                        modelclass=allQuestionsList.get(index);
                        setAllData();
                        resetColor();
                    }
                    else{
                        Gamewon();
                    }
                }
            });


        }

        private void Gamewon() {
            Intent intent=new Intent(Feelings.this, WonActivity.class);
            intent.putExtra("correct",correctCount);
            intent.putExtra("wrong",wrongCount);
            startActivity(intent);
        }
        public void enableButton(){
            cardOA.setClickable(true);
            cardOB.setClickable(true);
            cardOC.setClickable(true);
            cardOD.setClickable(true);
        }
        public void siableButton(){
            cardOA.setClickable(false);
            cardOB.setClickable(false);
            cardOC.setClickable(false);
            cardOD.setClickable(false);
        }
        public void resetColor(){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.white));
        }

        public void optionClickA(View view) {
            if(modelclass.getOA().equals(modelclass.getAns())){

                if (index<allQuestionsList.size()-1){
                    Correct(cardOA);
                }
                else{
                    Gamewon();
                }
            }
            else {
                Wrong(cardOA);
            }
        }

        public void optionClickB(View view) {
            if(modelclass.getOB().equals(modelclass.getAns())){

                if (index<allQuestionsList.size()-1){
                    Correct(cardOB);
                }
                else{
                    Gamewon();
                }
            }
            else {
                Wrong(cardOB);
            }
        }

        public void optionClickC(View view) {
            if(modelclass.getOC().equals(modelclass.getAns())){

                if (index<allQuestionsList.size()-1){
                    Correct(cardOC);
                }
                else{
                    Gamewon();
                }
            }
            else {
                Wrong(cardOC);
            }
        }

        public void optionClickD(View view) {
            if(modelclass.getOD().equals(modelclass.getAns())){

                if (index<allQuestionsList.size()-1){
                    Correct(cardOD);
                }
                else{
                    Gamewon();
                }
            }
            else {
                Wrong(cardOD);
            }
        }
}