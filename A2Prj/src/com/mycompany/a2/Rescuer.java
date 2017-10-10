package com.mycompany.a2;

/*
 * Rescuer is an abstract class which defines the behaviors and
 * characteristics common between its concrete subclasses.
 * Currently the only concrete Rescuer is spaceship.
 * It implements the move methods from the IGuided interface 
 * giving the user full control of relocating the rescuer.
 */
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
