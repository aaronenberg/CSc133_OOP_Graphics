package com.mycompany.a3;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/*
 * Alien is a concrete subclass of Opponent. Its purpose is to
 * define the behaviors and characteristics of an alien instance.
 */

public class
Alien extends Opponent
{
    private static final int
            SPEED_MULTIPLIER = 100,
            MEDIUM_GREEN = 128,
            HINT_OF_BLUE = 15,
            HINT_OF_RED = 15;
    
    public
    Alien()
    {
        super();
        super.setSpeed(SPEED_MULTIPLIER);
        super.setColor(HINT_OF_RED, MEDIUM_GREEN, HINT_OF_BLUE);
    }

    /*
     * When two aliens collide, a new alien spawns near the
     * point of collision.
     */
    public
    Alien(float x, float y)
    {
        this(); // call the default constructor
        setLocation(x, y);
    }

    
    @Override
    public void
    setColor(int r, int g, int b) 
    {}

    @Override
    public void
    setSpeed(int spd)
    {}

    public void
    draw(Graphics g, Point pCmpRelPrnt)
    {
        int diameter = getSize();
        int xOrigin = (int) (pCmpRelPrnt.getX() + getX());
        int yOrigin = (int) (pCmpRelPrnt.getY() + getY());
        int xCenter = xOrigin - diameter/2;
        int yCenter = yOrigin - diameter/2;

        g.setColor(getColor());
        g.fillArc(xCenter, yCenter, diameter, diameter, 0, 360);
    }

    public void
    handleCollision(ICollider opponent)
    {
        ArrayList<ICollider> collisions = getCollisions();
        ArrayList<ICollider> collisionsOpponent = ((Opponent) opponent).getCollisions();

        if (opponent instanceof Alien && !collisions.contains(opponent)) {
            GameWorld.aliensCollide(this, opponent);
            collisions.add(opponent);
            collisionsOpponent.add(this);
        }  
    }

    @Override
    public String
    toString()
    {
        String opponentString = super.toString();
        return "    Alien: " + opponentString;
    }
}
