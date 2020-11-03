package com.tylerthompson1492.modernfitness.Exercises;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Workout implements Serializable {

    public static final String WORKOUT_INTENT_KEY = "curr_workout_data";
    public static final String WORKOUT_FIRESTORE_KEY = "workouts";

    public String Date;
    public String Time;
    public List<Exercise> exercises;

    public Workout()
    {
        exercises = new ArrayList<>();
    }


}
