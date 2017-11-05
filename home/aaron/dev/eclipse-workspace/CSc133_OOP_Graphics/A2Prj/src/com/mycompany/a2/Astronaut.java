package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;


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
    private static final int INIT_HEALTH   = 5,
                             ATTACK_DAMAGE = 1,
                             FADE_GREEN    = 15,
                             HINT_OF_RED   = 15,
                             DARK_GREEN    = 128,
                             ZERO_BLUE     = 0;
    private int health;

    public
    Astronaut()
    {
        super();
        super.setColor(HINT_OF_RED, DARK_GREEN, ZERO_BLUE);
        health = INIT_HEALTH;
        super.setSpeed(health);
    }
    
    public int
    getHealth()
    {
        return health;
    }

    private void
    updateHealth()
    {
        if (health != 0)
            health -= ATTACK_DAMAGE;
    }
    private void
    updateSpeed()
    {
        super.setSpeed(health);
    }
    
    private void
    updateColor(int r, int g, int b)
    {
        setColor(r, g - FADE_GREEN, b);   
    }

    /* GameWorld.fight() calls this method with which
     * we are pretending a collision between alien
     * and astronaut has occurred. The collision
     * damages the astronaut, decreasing it's health by 1,
     * changes it's color, and decreases it's speed by 1.
     */
    public void
    collidesWithAlien()
    {
        updateHealth();
        updateColor(ColorUtil.red(getColor()),
                    ColorUtil.green(getColor()),
                    ColorUtil.blue(getColor()));
        updateSpeed();
    }

    public String
    toString()
    {
        String opponentString = super.toString();
        return "Astronaut: " + opponentString + " health=" + health;
    }
    

     // Astronauts with zero health left can not move, they are dead.
    public void
    move()
    {
        if (health != 0)
            super.move();
    }
}
