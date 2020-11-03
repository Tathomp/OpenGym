package com.tylerthompson1492.modernfitness.Exercises;

import java.util.ArrayList;
import java.util.List;

public class StrengthExercise extends Exercise {

    public List<Integer> Reps;
    public List<Integer> Weight;

    public StrengthExercise(String name, String Description) {
        super(name, Description);

        Reps = new ArrayList<>();
        Weight = new ArrayList<>();
    }
}
