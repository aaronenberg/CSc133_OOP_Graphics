package com.mycompany.a1;


import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public abstract class Opponent extends GameObject {

    private static final int MIN_OPP_SIZE = 20;
    private static final int MAX_OPP_SIZE = 50;
    private static final int DEGREES_OPP_DIRECTION = 359;
    private final int size;
    private int speed;
    private int color;
    private int direction;
    private Random randomDegree;
    private Random randomSize;
    private Point location = new Point();
    
    
    public Opponent() {
        super();
        this.size = randomizeSize();
        this.direction = randomizeDirection();
    }


    public int getColor() {
        return this.color;
    }

    public void setColor(int r, int g, int b) {
        this.color = ColorUtil.rgb(r, g, b);
    }


    public int getSize() {
        return this.size;
    }

    public void setSize(int newSize) {}

    // Random.nextInt() range starts from 0,
    // so we add MIN_OPP_SIZE to raise the lower bound,
    // and we subtract it from MAX_OPP_SIZE to stay within upper bound
    private int randomizeSize() {
        this.randomSize = new Random();
        
        return randomSize.nextInt(MAX_OPP_SIZE - MIN_OPP_SIZE) + MIN_OPP_SIZE;
    }


    private int randomizeDirection() {
        this.randomDegree = new Random();
        return randomDegree.nextInt(DEGREES_OPP_DIRECTION);
    }
}
