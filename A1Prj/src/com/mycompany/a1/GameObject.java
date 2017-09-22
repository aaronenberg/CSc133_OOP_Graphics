package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.Random;


public abstract class
GameObject
{

    private static final float WORLD_MAX_WIDTH = (float) 1024.0;
    private static final float WORLD_MAX_HEIGHT = (float) 768.0;
    private int size;
    private int color;
    private Point location;
    private Random rX, rY;


    public
    GameObject()
    {
        this.location = new Point();
        setLocation(randomX(), randomY());
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


    public Point
    getLocation()
    {
        return this.location;
    }

    public void
    setLocation(float x, float y)
    {
        this.location.setX(x);
        this.location.setY(y);
    }

    /*
     * Random.nextFloat() value ranges from 0.0 to 1.0 (inclusive),
     * so we multiply by the X and Y limits of game world
     */
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


    public int
    getSize()
    {
        return this.size;
    }

    public void
    setSize(int newSize)
    {
        this.size = newSize;
    }

}
