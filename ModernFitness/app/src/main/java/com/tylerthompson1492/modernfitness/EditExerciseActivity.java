package com.tylerthompson1492.modernfitness;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tylerthompson1492.modernfitness.Exercises.Exercise;
import com.tylerthompson1492.modernfitness.Exercises.Workout;

public class EditExerciseActivity extends AppCompatActivity {

    private Exercise currentExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        //This is where we're editing our sets for a given exercise
        currentExercise = (Exercise) getIntent().getSerializableExtra("currExercise");


    }

    public void CreateSet()
    {

    }


    public void BackToWorkoutOnClick(View view)
    {
        //bundle up the current workout in a new intent and move to the edit workout screen
        Workout currWorkout = (Workout)getIntent().getSerializableExtra(Workout.WORKOUT_INTENT_KEY);
        currWorkout.exercises.add(currentExercise);


        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra(Workout.WORKOUT_INTENT_KEY, currWorkout);



        finish();
        startActivity(intent);
    }

}
