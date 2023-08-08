package com.mycompany.velha3d;

import java.util.Random;

public class Bot {    

    public int getRandomNumber() {

        Random rand = new Random();
        int upperbound = 3;
        return rand.nextInt(upperbound);
    }

}
