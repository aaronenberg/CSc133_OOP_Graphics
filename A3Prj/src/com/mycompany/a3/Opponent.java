package com.mycompany.a3;


import java.util.Random;
import java.lang.Math;

/*
 * Opponent is an abstract class which defines the behaviors and
 * characteristics common between its concrete subclasses 
 * Alien and Astronaut. It implements the move method from
 * the IMoving interface given an opponent's speed and direction.
 * skewDirection() keeps an opponent from moving in a straight line.
 * if an opponent hits a wall changeDirection() turns them in the
 * opposite direction. centerHitEdge() and xAtMaxWidth() also prevent
 * an opponent from moving or spawning outside of world limits.
 */

public abstract class
Opponent extends GameObject implements IMoving
{
    private static final float WORLD_ORIGIN = (float) 0.0;
    private static final int   MIN_OPP_SIZE = 20,
                               MAX_OPP_SIZE = 50,
                               DIRECTION_ANGLE_DEGREES = 359,
                               COMPASS_ANGLE_OFFSET = 90,
                               SPEED_CONSTANT = 1,
                               SMALL_DEGREE = 3;
    private int speed = SPEED_CONSTANT,
                direction;
    private Random random,
                   randomDegree,
                   randomSize;
    
    
    public
    Opponent()
    {
        super();
        direction = randomizeDirection();
        super.setSize(randomizeSize());
    }

    @Override
    public void
    setSize(int newSize)
    {}
    
    /*
     * randomly pick a number between 3 and 6 (inclusive),
     * depending on if its odd or even, direction increases or decreases
     * by that amount provided it keeps us within limits of direction
     */
    private void
    skewDirection()
    {
        random = new Random();
        int smallRandomDegree = random.nextInt(SMALL_DEGREE) + 3;
        if (smallRandomDegree % 2 == 0)
        {
            if (direction < (DIRECTION_ANGLE_DEGREES - smallRandomDegree))
                direction += smallRandomDegree;
            else
                direction -= smallRandomDegree;
        }
        else
        {
            if (direction > (smallRandomDegree))
                direction -= smallRandomDegree;
            else
                direction += smallRandomDegree;
        }
            
    }
    
    public int
    getDirection()
    {
        return direction;
    }
    
    /*
     * causes the opponent to face the opposite direction.
     * Called by move() to keep opponent from traveling
     * off of the map.
     */
    private void
    changeDirection()
    {
        final int DEGREES_180 = 180;

        if (direction < DEGREES_180)
            direction += DEGREES_180;
        else
            direction -= DEGREES_180;
    }
 
    /*
     * We subtract MIN_OPP_SIZE from MAX_OPP_SIZE to stay within upper bound
     * after adding MIN_OPP_SIZE to raise the lower bound.
     */
    private int
    randomizeSize()
    {
        randomSize = new Random();
        return randomSize.nextInt(MAX_OPP_SIZE - MIN_OPP_SIZE) + MIN_OPP_SIZE;
    }

    private int
    randomizeDirection()
    {
        randomDegree = new Random();
        return randomDegree.nextInt(DIRECTION_ANGLE_DEGREES);
    }
    
    /*
     * compass angles: (90) EAST when direction is 0
     *                  (0) NORTH when direction is 90
     *                (-90 (180 on actual compass)) SOUTH when direction is 180
     *               (-180 (270 on actual compass)) WEST when direction is 270
     */
    private double
    theta()
    {
        double compassAngleInDegrees = (double)(COMPASS_ANGLE_OFFSET - direction);
        double compassAngleInRadians = Math.toRadians(compassAngleInDegrees);
        return compassAngleInRadians;
    }

    public void
    move()
    {
        if (centerHitEdge())
            changeDirection();
        double deltaX = Math.cos(theta()) * speed;
        double deltaY = Math.sin(theta()) * speed;
        float x = (float) Math.round((getX() + deltaX));
        float y = (float) Math.round((getY() + deltaY));
        this.setLocation(x, y);
        this.skewDirection();
    }

    public int
    getSpeed()
    {
        return speed;
    }
    
    public void
    setSpeed(int speedMultiplier)
    {
        speed = speedMultiplier * SPEED_CONSTANT;
    }

    /*
     * Check to see if the opponent's center is within
     * the map dimensions meaning neither of the 
     * opponent's x or y coordinates are allowed
     * to be equal to the map's width or height, respectively.
     * 
     * With each Tick, we assume an opponent
     * can move an amount equal to it's speed
     * across a given axis, so we subtract
     * their speed from the map dimensions.
     */
    public boolean
    centerHitEdge()
    {
        boolean centerHitEdge = true;
        float x = getX();
        float y = getY();
        if (
                (x > (WORLD_ORIGIN + speed) && x < (Game.getMapWidth() - speed)) &&
                (y > (WORLD_ORIGIN + speed) && y < (Game.getMapHeight() - speed))
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
            float x = getX();
            if (x >= Game.getMapWidth())
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
            float y = getY();
            if (y >= Game.getMapHeight())
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
            float y = getY();
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
            float y = getY();
            if (y <= WORLD_ORIGIN)
                xAtOriginWidth = true;
        }
        return xAtOriginWidth;
    }
    
    public String
    toString()
    {
        String GameObjString = super.toString();
        return GameObjString + " speed=" + speed + " dir=" + direction;
    }
}
