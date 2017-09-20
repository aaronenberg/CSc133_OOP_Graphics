package com.mycompany.a1;


import com.codename1.charts.util.ColorUtil;
import java.util.Random;
import java.lang.Math;


public abstract class Opponent extends GameObject implements IMoveable {

    private static final int MIN_OPP_SIZE = 20;
    private static final int MAX_OPP_SIZE = 50;
    private static final int DEGREES_OPP_DIRECTION = 359;
    private static final int DEGREES_TURN_AMOUNT = 90;
    private int speed;
    private int color;
    private int direction;
    private Random randomDegree, randomSize;
    
    
    public
    Opponent()
    {
        super();
        this.direction = randomizeDirection();
    }


    public int
    getColor() 
    {
        return this.color;
    }

    public void
    setColor(int r, int g, int b)
    {
        this.color = ColorUtil.rgb(r, g, b);
    }


    public void
    setSize(int newSize)
    {}

    public int
    getSize()
    {
        return randomizeSize();
    }
 
    /*
     * We subtract MIN_OPP_SIZE from MAX_OPP_SIZE to stay within upper bound
     * after adding MIN_OPP_SIZE to raise the lower bound.
     */
    private int
    randomizeSize()
    {
        this.randomSize = new Random();
        return randomSize.nextInt(MAX_OPP_SIZE - MIN_OPP_SIZE) + MIN_OPP_SIZE;
    }

    private int
    randomizeDirection()
    {
        this.randomDegree = new Random();
        return randomDegree.nextInt(DEGREES_OPP_DIRECTION);
    }
    
    private double
    turningAngle()
    {
        double degreeOfTurn = (double)(DEGREES_TURN_AMOUNT - this.direction);
        double angle = Math.toRadians(degreeOfTurn);
        return angle;
    }

    public void
    move()
    {
        double deltaX = Math.cos(turningAngle()) * this.speed;
        double deltaY = Math.sin(turningAngle()) * this.speed;
        float x = (float)(getLocation().getX() + deltaX);
        float y = (float)(getLocation().getY() + deltaY);
        setLocation(x, y);
    }

}
