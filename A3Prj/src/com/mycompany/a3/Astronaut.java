package com.mycompany.a3;


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
        super.setSpeed(health*SPEED_MULTIPLIER);
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
        super.setSpeed(health*SPEED_MULTIPLIER);
    }
    
    private void
    updateColor(int r, int g, int b)
    {
        setColor(r + FADE_COLOR, g + FADE_COLOR, b + FADE_COLOR);   
    }

    /* GameWorld.fight() calls this method with which
     * we are pretending a collision between alien
     * and astronaut has occurred. The collision
     * damages the astronaut, decreasing it's health,
     * speed, and fading it's color.
     */
    public void
    collidesWithAlien()
    {
        if (health != 0) {
            updateHealth();
            updateColor(
                    ColorUtil.red(getColor()),
                    ColorUtil.green(getColor()),
                    ColorUtil.blue(getColor())
            );
            updateSpeed();
        }
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
        int xCenter = (int) (pCmpRelPrnt.getX() + getX());
        int yCenter = (int) (pCmpRelPrnt.getY() + getY());
        int xOrigin = xCenter - base/2;
        int yOrigin = yCenter + base/2;
        g.setColor(getColor());
        g.fillTriangle(xOrigin+base/2, yOrigin+base/2, xOrigin, yOrigin-base, xOrigin+base, yOrigin-base);
    }

    @Override
    public String
    toString()
    {
        String opponentString = super.toString();
        return "Astronaut: " + opponentString + " health=" + health;
    }
}
