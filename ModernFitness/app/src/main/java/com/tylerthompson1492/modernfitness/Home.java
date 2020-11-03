package com.tylerthompson1492.modernfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tylerthompson1492.modernfitness.Exercises.Workout;

import java.util.List;

public class Home extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    //private DatabaseReference databaseReference;
    private FirebaseFirestore firestore;

    private EditText editTextName, editTextAddress;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textViewUserEmail = (TextView) findViewById(R.id.welcom_header);
        buttonLogout = (Button) findViewById(R.id.logout_button);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        //databaseReference = FirebaseDatabase.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();





        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail.setText("Welcome "+user.getEmail());

        UserData ud = new UserData("Hello", "World");

        firestore.collection("users").document(user.getUid()).collection("workout").document("test").set(ud);

    }

    public void LogoutButtonOnclick(View view)
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    // Go to new workout activity
    //
    public void NewWorkoutButtonClicked(View view)
    {
        Intent intent = new Intent (this, EditWorkoutActivity.class);
        intent.putExtra(Workout.WORKOUT_INTENT_KEY, new Workout());

        finish();
        startActivity(intent);
    }


    public  void SaveDataButtonOnClick(View view)
    {
        String name = editTextName.getText().toString().trim();
        String color = editTextAddress.getText().toString().trim();

        UserData ud = new UserData(name, color);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        firestore.collection(UserData.USER_FIRESTORE_KEY).document(user.getUid()).collection(Workout.WORKOUT_FIRESTORE_KEY).document("test").set(ud);

        //databaseReference.child(user.getUid()).setValue(ud);

       // firestore.collection(user.getUid()).add(ud);
       // firestore.collection("users").document("user id").collection("workout").add("data");

        firestore.collection("users").document(user.getUid()).collection("workout").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    List<DocumentSnapshot> myDocs = task.getResult().getDocuments();
                    Toastt(""+(myDocs.size()));

                    editTextName.setText((String)myDocs.get(0).getData().get("Color"));

                }else
                {
                    Log.d("Home", "Task failed");
                }
            }
        });

        String testdata = "Informaation Saved";

    }

    private void Toastt(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}
