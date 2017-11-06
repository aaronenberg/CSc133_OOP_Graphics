package com.mycompany.a3;

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
    
    public
    Alien(float x, float y)
    {
        this();
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
        int xCenter = (int) (pCmpRelPrnt.getX() + getX());
        int yCenter = (int) (pCmpRelPrnt.getY() + getY());
        int xOrigin = xCenter - diameter/2;
        int yOrigin = yCenter + diameter/2;
        g.setColor(getColor());
        g.fillArc(xOrigin, yOrigin, diameter, diameter, 0, 360);
    }

    @Override
    public String
    toString()
    {
        String opponentString = super.toString();
        return "    Alien: " + opponentString;
    }

}
