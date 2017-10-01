package com.mycompany.a1;


import java.util.Random;
import java.lang.Math;

/*
 * Opponent is an abstract class which defines the behaviors and
 * characteristics common between its concrete subclasses 
 * Alien and Astronaut. It implements the move method from
 * the IMoving interface given an opponent's speed and direction.
 * skewDirection() keeps an opponent from moving in a straight line.
 * if an opponent hits a wall changeDirection() turns them in the
 * opposite direction.
 */

public abstract class
Opponent extends GameObject implements IMoving
{

    private static final int MIN_OPP_SIZE = 20;
    private static final int MAX_OPP_SIZE = 50;
    private static final int DEGREES_OPP_DIRECTION = 359;
    private static final int COMPASS_OFFSET = 90;
    private static final int SPEED_CONSTANT = 1;
    private static final int SMALL_DEGREE = 3;
    private int speed = SPEED_CONSTANT;
    private int direction;
    private Random random, randomDegree, randomSize;
    
    
    public
    Opponent()
    {
        super();
        this.direction = randomizeDirection();
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
            if (this.direction < (DEGREES_OPP_DIRECTION - smallRandomDegree))
                this.direction += smallRandomDegree;
            else
                this.direction -= smallRandomDegree;
        }
        else
        {
            if (this.direction > (smallRandomDegree))
                this.direction -= smallRandomDegree;
            else
                this.direction += smallRandomDegree;
        }
            
    }
    
    public int
    getDirection()
    {
        return this.direction;
    }
    
    private void
    changeDirection()
    {
        if (this.direction < 180)
            this.direction += 180;
        else
            this.direction -= 180;
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
    
    /*
     * compass angles: (90) EAST when direction is 0
     *                  (0) NORTH when direction is 90
     *                (-90 (180 on actual compass)) SOUTH when direction is 180
     *               (-180 (270 on actual compass)) WEST when direction is 270
     */
    private double
    theta()
    {
        double compassAngleInDegrees = (double)(COMPASS_OFFSET - this.direction);
        double compassAngleInRadians = Math.toRadians(compassAngleInDegrees);
        return compassAngleInRadians;
    }

    public void
    move()
    {
        if (centerHitEdge())
            changeDirection();
        double deltaX = Math.cos(theta()) * this.speed;
        double deltaY = Math.sin(theta()) * this.speed;
        float x = (float) Math.round((this.getX() + deltaX));
        float y = (float) Math.round((this.getY() + deltaY));
        this.setLocation(x, y);
        this.skewDirection();
    }

    public int
    getSpeed()
    {
        return this.speed;
    }
    
    public void
    setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    public String
    toString()
    {
        String GameObjString = super.toString();
        return GameObjString + " speed=" + getSpeed() + " dir=" + getDirection();
    }
}
