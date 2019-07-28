package statics;

import java.util.Random;

public class Rand{

    public static int random(int upperBound){
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    public static int randomNumberBetween(int lowerBound ,int upperBound){
        Random rand = new Random();
        return rand.nextInt(upperBound-lowerBound) + lowerBound;
    }

}