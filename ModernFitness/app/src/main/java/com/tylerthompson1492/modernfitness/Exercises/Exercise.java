package com.tylerthompson1492.modernfitness.Exercises;

import java.io.Serializable;

public class Exercise implements Serializable {

    public static final String EXERCISE_FIRESTORE_KEY = "StrengthWorkouts";

    public String Name;
    public String Description;

    public Exercise(String name, String Description)
    {
        this.Name = name;
        this.Description = Description;
    }

}
