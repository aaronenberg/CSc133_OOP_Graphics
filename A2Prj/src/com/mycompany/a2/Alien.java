package com.mycompany.a2;

/*
 * Alien is a concrete sublcass of Opponent. Its purpose is to
 * define the behaviors and characteristics of an alien instance.
 */

public class
Alien extends Opponent
{
    private static final int SPEED_MULTIPLIER = 5;
    private static final int HINT_OF_GREEN = 15;
    private static final int DARK_BLUE = 128;
    private static final int ZERO_RED = 0;
    public
    Alien()
    {
        super();
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

    public int
    getSpeed()
    {
        return super.getSpeed() * SPEED_MULTIPLIER;
        
    }
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
