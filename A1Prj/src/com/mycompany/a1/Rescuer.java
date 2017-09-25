package com.mycompany.a1;


public abstract class
Rescuer extends GameObject implements IGuided
{
    private static final int MOVE_SPACESHIP = 10;
    public
    Rescuer()
    {
        super();
    }
    
    public void
    moveLeft()
    {
       setLocation(getX() - MOVE_SPACESHIP, getY()); 
    }

    public void
    moveRight()
    {
        setLocation(getX() + MOVE_SPACESHIP, getY());
    }

    public void
    moveUp()
    {
        setLocation(getX(), getY() + MOVE_SPACESHIP);
    }

    public void
    moveDown()
    {
        setLocation(getX(), getY() - MOVE_SPACESHIP);
    }

    public void
    jumpToLocation(float x, float y)
    {
        setLocation(x, y);
    }
    
    public String
    toString()
    {
        String gameObjString = super.toString();
        return "Spaceship: " + gameObjString;
    }
}
