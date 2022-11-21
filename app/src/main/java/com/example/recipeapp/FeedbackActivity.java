package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackActivity extends AppCompatActivity {


    // creating variables for
    // EditText and buttons.
    private EditText NameEdt, ContentEdt;
    private TextView userId;
    private Button sendDatabtn;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // initializing our edittext and button
        NameEdt = findViewById(R.id.idEdtName);
        ContentEdt = findViewById(R.id.idEdtContent);

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Feedback");

        // initializing our object
        // class variable.
        feedback = new Feedback();

        sendDatabtn = findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = NameEdt.getText().toString();
                String content = ContentEdt.getText().toString();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(content)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(FeedbackActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(name, content, userId);
                }
            }
        });
    }

    private void addDatatoFirebase(String name, String phone, String userId) {
        // below 3 lines of code is used to set
        // data in our object class.
        feedback.setName(name);
        feedback.setContent(phone);
        feedback.setUserId(userId);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(feedback);

                // after adding this data we are showing toast message.
                Toast.makeText(FeedbackActivity.this, "Feedback has been sent", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(FeedbackActivity.this, "Fail to add data " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}