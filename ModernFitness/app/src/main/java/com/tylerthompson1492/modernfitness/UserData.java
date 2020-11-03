package com.tylerthompson1492.modernfitness;

import java.util.HashMap;

public class UserData {

    public static final String USER_FIRESTORE_KEY = "users";

    public String Name;
    public String Color;

    public HashMap<String, Object> testMap;

    public UserData(String name, String color)
    {
        this.Name = name;
        this.Color = color;

        testMap = new HashMap<>();
        testMap.put("test", "Did this work?");
    }


}
