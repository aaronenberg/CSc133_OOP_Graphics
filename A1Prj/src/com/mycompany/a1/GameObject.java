package com.mycompany.a1;


import com.codename1.charts.models.Point;
import java.util.Random;


public abstract class
GameObject
{

    private static final float WORLD_MAX_WIDTH = (float) 1024.0;
    private static final float WORLD_MAX_HEIGHT = (float) 768.0; 
    private Point location;
    private Random rX, rY;


    public
    GameObject()
    {
        location = new Point();
        setLocation(randomX(), randomY());
    }


    public abstract int
    getColor();

    public abstract void
    setColor(int r, int g, int b);


    public abstract Point
    getLocation();

    public abstract void
    setLocation(float x, float y);


    // Random.nextFloat() value ranges from 0.0 to 1.0 (inclusive),
    // so we multiply by the X and Y limits of game world
    private float
    randomX()
    {
        rX = new Random();
        float x = rX.nextFloat() * WORLD_MAX_WIDTH;
        return x;
    }

    private float
    randomY()
    {
        rY = new Random();
        float y = rY.nextFloat() * WORLD_MAX_HEIGHT;
        return y;
    }


    public abstract int
    getSize();

    public abstract void
    setSize(int newSize);


}
