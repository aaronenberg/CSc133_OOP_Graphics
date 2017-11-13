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
Astronaut extends Opponent implements ISelectable
{
    private static final int
            INIT_HEALTH = 5,
            SPEED_MULTIPLIER = 20,
            FADE_COLOR = 25,
            HINT_OF_RED = 15,
            HINT_OF_GREEN = 15,
            MEDIUM_BLUE = 128;
    private int health;
    private int no_of_collisions = 0;
    private boolean isSelected = false;

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
        health = INIT_HEALTH - no_of_collisions;
//        health -= ALIEN_ATTACK_DAMAGE;
    }
    private void
    updateSpeed()
    {
        super.setSpeed(health * SPEED_MULTIPLIER);
    }

    public void
    heal()
    {
        no_of_collisions = 0;
        updateHealth();
        updateSpeed();
        super.setColor(HINT_OF_RED, HINT_OF_GREEN, MEDIUM_BLUE);
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
        final int no_of_vertices = 3;
        int size = getSize();
        Point top = new Point(getX(), getY() + size/2);
        Point bottomLeft = new Point(getX() - size/2, getY() - size/2);
        Point bottomRight = new Point(getX() + size/2, getY() - size/2);
        int xTop = (int) (pCmpRelPrnt.getX() + top.getX());
        int yTop = (int) (pCmpRelPrnt.getY() + top.getY());
        int xBottomLeft = (int) (pCmpRelPrnt.getX() + bottomLeft.getX());
        int yBottomLeft = (int) (pCmpRelPrnt.getY() + bottomLeft.getY());
        int xBottomRight = (int) (pCmpRelPrnt.getX() + bottomRight.getX());
        int yBottomRight = (int) (pCmpRelPrnt.getY() + bottomRight.getY());
        int xPoints[] = {xTop, xBottomLeft, xBottomRight};
        int yPoints[] = {yTop, yBottomLeft, yBottomRight};

        g.setColor(getColor());

        if (isSelected() && Game.gamePaused())
            g.drawPolygon(xPoints, yPoints, no_of_vertices);
        else
            g.fillPolygon(xPoints, yPoints, no_of_vertices);
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
            no_of_collisions++;
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

    public void
    setSelected(boolean selectedState)
    {
        isSelected = selectedState;
    }

    public boolean
    isSelected()
    {
        return isSelected;
    }

    public boolean
    contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
    {
        int size = getSize();
        float pX = pPtrRelPrnt.getX();
        float pY = pPtrRelPrnt.getY();
        float xLoc = pCmpRelPrnt.getX() + getX();
        float yLoc = pCmpRelPrnt.getY() + getY();

        if ( (pX >= xLoc) && (pX <= xLoc + size)
          && (pY >= yLoc) && (pY <= yLoc + size) )
            return true;
        else
            return false;
    }

    @Override
    public String
    toString()
    {
        String opponentString = super.toString();
        return "Astronaut: " + opponentString + " health=" + health;
    }

}
