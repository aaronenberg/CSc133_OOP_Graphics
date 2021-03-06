package com.mycompany.a2;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/*
 * GameObject is an abstract class which defines the behaviors and
 * characteristics common between all Opponents and Rescuers including:
 * color, location and size. Every GameObject instance is given a 
 * random location that is within the world limits.
 */
public abstract class
GameObject
{
    private int size,
                color;
    private Point location = new Point();

    public int
    getColor() 
    {
        return color;
    }

    public void
    setColor(int r, int g, int b)
    {
        color = ColorUtil.rgb(r, g, b);
    }


    public Point
    getLocation()
    {
        return location;
    }

    public void
    setLocation(float x, float y)
    {
        location.setX(x);
        location.setY(y);
    }
    
    public float
    getX()
    {
        return location.getX();
    }
    public float
    getY()
    {
        return location.getY();
    }

    /*
     * Random.nextFloat() value ranges from 0.0 to 1.0 (inclusive),
     * so we multiply by the X and Y limits of game world
     */


    public int
    getSize()
    {
        return size;
    }

    public void
    setSize(int newSize)
    {
        size = newSize;
    }

    
    public String
    toString()
    {
        String gameObjectStats = "loc=" + getX() + ","
                                        + getY() +
                                 " color=[" + ColorUtil.red(color) + ","
                                            + ColorUtil.green(color) + ","
                                            + ColorUtil.blue(color) + "]" +
                                 " size=" + size;
        return gameObjectStats;
    }

}
