package com.mycompany.a1;


import java.util.Random;
import com.codename1.charts.models.Point;


public abstract class Opponent extends GameObject {

    private static final int OPP_SIZE_LOWER_LIM = 20;
    private static final int OPP_SIZE_UPPER_LIM = 50;
    private int size;
    private int speed;
    private int direction;
    private Random rand = new Random();
    private Point location = new Point();
    
    public int randomSizeOpp() {
        return OPP_SIZE_LOWER_LIM + rand.nextInt(OPP_SIZE_UPPER_LIM);
    }

}
