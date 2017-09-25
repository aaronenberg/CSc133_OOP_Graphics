package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class
Astronaut extends Opponent
{
    private static final int INIT_HEALTH = 5;
    private static final int ATTACK_POWER = 1;
    private static final int FADE_GREEN = 15;
    private static final int HINT_OF_RED = 15;
    private static final int DARK_GREEN = 128;
    private static final int ZERO_BLUE = 0;
    private int health;

    public
    Astronaut()
    {
        super();
        super.setColor(HINT_OF_RED, DARK_GREEN, ZERO_BLUE);
        this.health = INIT_HEALTH;
    }
    
    public int
    getHealth()
    {
        return this.health;
    }

    private void
    updateHealth()
    {
        if (getHealth() != 0)
            this.health -= ATTACK_POWER;
    }
    
    private void
    updateColor(int r, int g, int b)
    {
        setColor(r, g - FADE_GREEN, b);   
    }

    // called by GameWorld.fight()
    public void
    collidesWithAlien()
    {
        updateHealth();
        updateColor(ColorUtil.red(getColor()),
                    ColorUtil.green(getColor()),
                    ColorUtil.blue(getColor()));
    }
    
    public int
    getSpeed()
    {
        return super.getSpeed() * getHealth();
    }

    public String
    toString()
    {
        String opponentString = super.toString();
        return "Astronaut: " + opponentString + " health=" + getHealth();
    }
    

     // if health is 0, do not allow opponent to move
    public void
    move()
    {
        if (getHealth() != 0)
            super.move();
    }
}
