package com.tylerthompson1492.modernfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tylerthompson1492.modernfitness.Exercises.Exercise;
import com.tylerthompson1492.modernfitness.Exercises.Workout;

public class EditWorkoutActivity extends AppCompatActivity {

    private Workout currentWorkout;

    //Views
    TextView workoutDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        currentWorkout = (Workout)getIntent().getSerializableExtra(Workout.WORKOUT_INTENT_KEY);
        workoutDisplay = (TextView) findViewById(R.id.display_exercises);

        String s = "";

        for (Exercise e: currentWorkout.exercises)
        {
            s += e.Name +"\n";
        }

        workoutDisplay.setText(s);


    }

    public void ChooseExerciseOnClick(View view)
    {
        Intent intent = new Intent(this, ChooseExercise.class);
        intent.putExtra(Workout.WORKOUT_INTENT_KEY, currentWorkout);

        finish();
        startActivity(intent);
    }

}
