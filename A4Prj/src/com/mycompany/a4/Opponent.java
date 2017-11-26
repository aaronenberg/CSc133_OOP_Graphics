package com.mycompany.a4;


import java.util.ArrayList;
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
Opponent extends GameObject implements IMoving, ICollider
{
    private static final float WORLD_ORIGIN = (float) 0.0;
    private static final int
            MIN_SIZE = 40,
            MAX_SIZE = 60,
            MAX_DIRECTION_DEGREES = 359,
            COMPASS_ANGLE_OFFSET = 90,
            SPEED_CONSTANT = 1;
    private int
            speed = SPEED_CONSTANT,
            direction;
    private Random
            random,
            randomDegree,
            randomSize;
    private ArrayList<ICollider> collisions = new ArrayList<ICollider>();
    
    public
    Opponent()
    {
        super();
        direction = randomDirection();
        super.setSize(randomSize());
    }

    @Override
    public void
    setSize(int newSize)
    {}
    
    /*
     * randomly pick a number between 3 and 18 (inclusive),
     * depending on if its odd or even, direction increases or decreases
     * by that amount which is within acceptable limits of direction
     */
    private void
    skewDirection()
    {
        final int SMALL_DEGREE = 3;
        random = new Random();
        int smallRandomDegree = random.nextInt(SMALL_DEGREE) + 15;

        if (smallRandomDegree % 2 == 0)
        {
            if ((direction + smallRandomDegree) <= MAX_DIRECTION_DEGREES)
                direction += smallRandomDegree;
            else
                direction -= smallRandomDegree;
        }
        else if (smallRandomDegree % 3 == 0)
        {
            if (direction >= (smallRandomDegree))
                direction -= smallRandomDegree;
            else
                direction += smallRandomDegree;
        }
        else
            return;
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
     * We subtract MIN_OPP_SIZE from MAX_OPP_SIZE to stay within
     * upper bound after adding MIN_OPP_SIZE to raise the lower bound.
     */
    private int
    randomSize()
    {
        randomSize = new Random();
        return randomSize.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
    }

    private int
    randomDirection()
    {
        randomDegree = new Random();
        return randomDegree.nextInt(MAX_DIRECTION_DEGREES);
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
        double compassAngleDegrees = (double)(COMPASS_ANGLE_OFFSET - direction);
        double compassAngleRadians = Math.toRadians(compassAngleDegrees);
        return compassAngleRadians;
    }

    public void
    move(int elapsedMilliSecs)
    {
        if (centerHitEdge())
            changeDirection();
        else
            skewDirection();

        double distanceTravelled = speed * (elapsedMilliSecs / 1000.0);
        double deltaX = Math.cos(theta()) * distanceTravelled;
        double deltaY = Math.sin(theta()) * distanceTravelled;
        float x = (float) (getX() + deltaX);
        float y = (float) (getY() + deltaY);

        setLocation(x, y);

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
                (x > (WORLD_ORIGIN) && x < (Game.getMapWidth())) &&
                (y > (WORLD_ORIGIN) && y < (Game.getMapHeight()))
            )
            return centerHitEdge = false;

        return centerHitEdge;
    }

    public boolean
    xAtMaxWidth()
    {
        boolean xAtMaxWidth = false;

        if (centerHitEdge()) {
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

        if (centerHitEdge()) {
            float y = getY();

            if (y >= Game.getMapHeight())
                yAtMaxHeight = true;
        }
        return yAtMaxHeight;
    }

    public boolean
    yAtOrigin()
    {
        boolean yAtOriginHeight = false;

        if (centerHitEdge()) {
            float y = getY();

            if (y <= WORLD_ORIGIN)
                yAtOriginHeight = true;
        }
        return yAtOriginHeight;
    }

    public boolean
    xAtOrigin()
    {
        boolean xAtOriginWidth = false;

        if (centerHitEdge()) {
            float y = getY();

            if (y <= WORLD_ORIGIN)
                xAtOriginWidth = true;
        }
        return xAtOriginWidth;
    }

    public boolean
    collidesWith(ICollider opponent)
    {
        boolean result = false;
        float halfSize = getSize()/2.0f;
        float halfSizeOpp = ((GameObject) opponent).getSize()/2.0f;
        float xCenter = getX() + halfSize;
        float yCenter = getY() + halfSize;
        float xCenterOpp =  ((GameObject) opponent).getX() + halfSizeOpp;
        float yCenterOpp =  ((GameObject) opponent).getY() + halfSizeOpp;
        float dx = xCenter - xCenterOpp;
        float dy = yCenter - yCenterOpp;
        float distanceBetweenCentersSqrd = (dx*dx + dy*dy);
        float radiiSqrd = (      halfSize * halfSize
                            + 2 * halfSize * halfSizeOpp
                            +  halfSizeOpp * halfSizeOpp );

        if (distanceBetweenCentersSqrd <= radiiSqrd)
            result = true;
        else {
            collisions.remove(opponent);
            ((Opponent) opponent).getCollisions().remove(this);
        }
        return result;
    }

    public ArrayList<ICollider>
    getCollisions()
    {
        return collisions;
    }

    @Override
    public String
    toString()
    {
        String GameObjString = super.toString();
        return GameObjString + " speed=" + speed + " dir=" + direction;
    }

}
