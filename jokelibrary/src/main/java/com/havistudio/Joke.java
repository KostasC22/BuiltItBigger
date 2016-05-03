package com.havistudio;

import java.util.Random;

public class Joke {

    static String[] temp = {"hello joke 1","hello joke 2","hello joke 3"};

    public static String getJoke(){

        Random rm = new Random();
        int selectedJoke = rm.nextInt(3);

        return temp[selectedJoke];
    }
}
