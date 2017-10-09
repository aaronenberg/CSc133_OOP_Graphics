package com.mycompany.a2;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

/*
 * GameObject is an abstract class which defines the behaviors and
 * characteristics common between all Opponents and Rescuers including:
 * color, location and size. Every GameObject instance is given a 
 * random location that is within the world limits. Methods such as
 * centerHitEdge() and xAtMaxWidth() prevent GameObjects, except
 * spaceship, from moving or spawning outside of world limits.
 */
public abstract class
GameObject
{
    
    private static final float WORLD_MAX_WIDTH = (float) 1024.0;
    private static final float WORLD_MAX_HEIGHT = (float) 768.0;
    private static final float WORLD_ORIGIN = (float) 0.0;
    private int mapHeight;
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
    
    public float
    getX()
    {
        return this.location.getX();
    }
    public float
    getY()
    {
        return this.location.getY();
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
        float roundedX = Math.round(x*10.0f)/10.0f;
        return roundedX;
    }

    private float
    randomY()
    {
        rY = new Random();
        float y = rY.nextFloat() * WORLD_MAX_HEIGHT;
        float roundedY = Math.round(y*10.0f)/10.0f;
        return roundedY;
    }
    
    /*
     * couple of checks to see whether the GameObjects
     * center is near the edge of the GameWorld
     */

    public boolean
    centerHitEdge()
    {
        boolean centerHitEdge = true;
        float x = this.getX();
        float y = this.getY();
        if (
                (x > (WORLD_ORIGIN + 1) && x < (WORLD_MAX_WIDTH  - 1)) &&
                (y > (WORLD_ORIGIN + 1) && y < (WORLD_MAX_HEIGHT - 1))
            )
            return centerHitEdge = false;
        return centerHitEdge;
    }
    
    public boolean
    xAtMaxWidth()
    {
        boolean xAtMaxWidth = false;
        if (centerHitEdge())
        {
            float x = this.getX();
            if (x >= WORLD_MAX_WIDTH)
                xAtMaxWidth = true;
        }
        return xAtMaxWidth;
    }
    public boolean
    yAtMaxHeight()
    {
        boolean yAtMaxHeight = false;
        if (centerHitEdge())
        {
            float y = this.getY();
            if (y >= WORLD_MAX_HEIGHT)
                yAtMaxHeight = true;
        }
        return yAtMaxHeight;
    }
    public boolean
    yAtOriginHeight()
    {
        boolean yAtOriginHeight = false;
        if (centerHitEdge())
        {
            float y = this.getY();
            if (y <= WORLD_ORIGIN)
                yAtOriginHeight = true;
        }
        return yAtOriginHeight;
    }
    public boolean
    xAtOriginWidth()
    {
        boolean xAtOriginWidth = false;
        if (centerHitEdge())
        {
            float y = this.getY();
            if (y <= WORLD_ORIGIN)
                xAtOriginWidth = true;
        }
        return xAtOriginWidth;
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
    
    private static float
    getMapHeight(MapView map)
    {
        return (float) map.getHeight();
    }
    
    public String
    toString()
    {
        String gameObjectStats = "loc=" + getX() + ","
                                        + getY() +
                                 " color=[" + ColorUtil.red(this.color) + ","
                                            + ColorUtil.green(this.color) + ","
                                            + ColorUtil.blue(this.color) + "]" +
                                 " size=" + getSize();
        return gameObjectStats;
    }

}
