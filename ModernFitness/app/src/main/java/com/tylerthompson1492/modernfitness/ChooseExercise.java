package com.tylerthompson1492.modernfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tylerthompson1492.modernfitness.Exercises.Exercise;
import com.tylerthompson1492.modernfitness.Exercises.Workout;

import java.util.HashMap;
import java.util.List;

public class ChooseExercise extends AppCompatActivity {

    FirebaseFirestore firestore;
    //TextView view;
    LinearLayout scrollViewLayout;

    HashMap<String, DocumentSnapshot> currentDocs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        currentDocs = new HashMap<>();
        firestore = FirebaseFirestore.getInstance();

        scrollViewLayout = (LinearLayout) findViewById(R.id.scrollview_linear_layout);

        firestore.collection(Exercise.EXERCISE_FIRESTORE_KEY).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete())
                {
                    String s = "Test Exercises\n";
                    //let's get all the exercises
                    List<DocumentSnapshot> myDocs = task.getResult().getDocuments();
                    for (DocumentSnapshot doc: myDocs)
                    {
                       // s += doc.get("name") + "\n";
                        currentDocs.put((String)doc.get("name"), doc);
                        ///https://stackoverflow.com/questions/45196556/how-can-i-create-a-textview-programmatically-in-a-linear-layout
                        ///Took some code from here and applied it to creating buttons programatically
                        Button button = GenerateExerciseButton(doc);



                    }

                   // view.setText(s);
                }
            }
        });
    }

    private Button GenerateExerciseButton(DocumentSnapshot doc)
    {
        Button button = new Button(this);
        button.setText("" + doc.get("name"));
        button.setTag(doc.get("name")); //we can pass parms this way

        button.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollViewLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ExerciseButtonClicked(""+ v.getTag());

            }
        });
        return button;
    }

    private void ExerciseButtonClicked(String s)
    {
        //This is where we'll switch to the edit excerise

        Exercise exercise = GetExerciseFromDocs(s);

        Intent intent = new Intent(this, EditExerciseActivity.class);
        intent.putExtra("currExercise", exercise);

        //The current workout obj and put it in the new intent
        intent.putExtra(Workout.WORKOUT_INTENT_KEY, getIntent().getSerializableExtra(Workout.WORKOUT_INTENT_KEY));

        finish();
        startActivity(intent);

        Toast.makeText(this, "Clicked: " + s, Toast.LENGTH_LONG).show();
    }

    public Exercise GetExerciseFromDocs(String docName)
    {
        DocumentSnapshot doc = currentDocs.get(docName);

        Exercise exercise = new Exercise((String) doc.get("name"), (String)doc.get("Description"));

        return exercise;
    }

}
