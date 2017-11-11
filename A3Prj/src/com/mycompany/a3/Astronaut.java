package com.mycompany.a3;


import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


/*
 * Astronaut is a concrete subclass of Opponent. Its purpose is to
 * define the behaviors and characteristics of an astronaut instance.
 * Astronauts have a health attribute to which its speed and color
 * are directly correlated. An astronaut that collides with and alien
 * loses 1 health. When their health reaches 0, they are unable to move.
 */

public class
Astronaut extends Opponent
{
    private static final int
            INIT_HEALTH = 5,
            SPEED_MULTIPLIER = 20,
            ALIEN_ATTACK_DAMAGE = 1,
            FADE_COLOR = 25,
            HINT_OF_RED = 15,
            HINT_OF_GREEN = 15,
            MEDIUM_BLUE = 128;
    private int health;

    public
    Astronaut()
    {
        super();
        super.setColor(HINT_OF_RED, HINT_OF_GREEN, MEDIUM_BLUE);
        health = INIT_HEALTH;
        super.setSpeed(health * SPEED_MULTIPLIER);
    }
    
    public int
    getHealth()
    {
        return health;
    }

    private void
    updateHealth()
    {
        health -= ALIEN_ATTACK_DAMAGE;
    }
    private void
    updateSpeed()
    {
        super.setSpeed(health * SPEED_MULTIPLIER);
    }

     // Astronauts with zero health left can not move, they are dead.
    @Override
    public void
    move(int elapsedMilliSecs)
    {
        if (health != 0)
            super.move(elapsedMilliSecs);
    }

    public void
    draw(Graphics g, Point pCmpRelPrnt)
    {
        int base = getSize();
        int xOrigin = (int) (pCmpRelPrnt.getX() + getX());
        int yOrigin = (int) (pCmpRelPrnt.getY() + getY());
        int xCenter = xOrigin - base/2;
        int yCenter = yOrigin - base/2;

        g.setColor(getColor());
        g.fillTriangle(xCenter+base/2, yCenter+base/2, xCenter, yCenter-base, xCenter+base, yCenter-base);
    }

    @Override
    public String
    toString()
    {
        String opponentString = super.toString();
        return "Astronaut: " + opponentString + " health=" + health;
    }

    /* 
     * A collision between astronaut and alien degrades
     * the astronaut's health and speed, and fades it's color.
     */
    public void
    handleCollision(ICollider opponent)
    {
        ArrayList<ICollider> collisions = getCollisions();
        ArrayList<ICollider> collisionsOpponent = ((Opponent) opponent).getCollisions();

        if ( (opponent instanceof Alien) && (health != 0) && (!collisions.contains(opponent)) ) { 
            updateHealth();
            updateSpeed();
            setColor( ColorUtil.red(getColor())   + FADE_COLOR,
                      ColorUtil.green(getColor()) + FADE_COLOR,
                      ColorUtil.blue(getColor())  + FADE_COLOR );

            collisions.add(opponent);
            collisionsOpponent.add(this);
            GameWorld.playSound(this, (GameObject) opponent);
        }
    }

}
