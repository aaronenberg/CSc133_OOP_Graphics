package com.mycompany.a3;

/*
 * Alien is a concrete subclass of Opponent. Its purpose is to
 * define the behaviors and characteristics of an alien instance.
 */

public class
Alien extends Opponent
{
    private static final int SPEED_MULTIPLIER = 5,
                             HINT_OF_GREEN    = 15,
                             DARK_BLUE        = 128,
                             ZERO_RED         = 0;
    public
    Alien()
    {
        super();
        super.setSpeed(SPEED_MULTIPLIER);
        super.setColor(ZERO_RED, HINT_OF_GREEN, DARK_BLUE);
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

    public void
    setSpeed(int spd)
    {}
    
    public String
    toString()
    {
        String opponentString = super.toString();
        return "    Alien: " + opponentString;
    }
}
