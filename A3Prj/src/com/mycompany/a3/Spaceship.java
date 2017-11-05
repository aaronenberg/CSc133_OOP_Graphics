package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/*
 * Spaceship is a concrete subclass of Rescuer. Its purpose is to
 * define the behaviors and characteristics of a spaceship instance.
 * the size of the spaceship refers to the size of its door which
 * can be contracted, expanded, opened and closed.
 */
public class
Spaceship extends Rescuer
{
    private static final int HINT_OF_RED = 15,
                             DARK_GREEN = 128,
                             HINT_OF_BLUE = 30,
                             DOOR_RESIZE = 10,
                             DOOR_MINIMUM_SIZE = 50,
                             DOOR_MAXIMUM_SIZE = 1024;
    private boolean doorState;
    private static Spaceship spaceship;
    
    private
    Spaceship()
    {
        super();
        setSize(100);
        this.doorState = false;
        super.setColor(HINT_OF_RED, DARK_GREEN, HINT_OF_BLUE);
    }
    
    public static Spaceship
    getSpaceship()
    {
        if (spaceship == null)
            spaceship = new Spaceship();
        return spaceship;
    }
    
    public void
    setColor()
    {}
    
    public void
    openDoor()
    {
        this.doorState = true;
    }
    
    public void
    closeDoor()
    {
        this.doorState = false;
    }
    
    /*
     * the door expands by 10 until its size is >= 1015
     * after this point, the door expands by 1 
     * to keep it from breaking passed 1024
     */
    public void
    expandDoor()
    {
        int doorSize = getSize();
        if (doorSize < 1020 && (doorSize + DOOR_RESIZE) <= DOOR_MAXIMUM_SIZE)
            setSize(doorSize + DOOR_RESIZE);
        else if ((doorSize >= (DOOR_MAXIMUM_SIZE - DOOR_RESIZE)) && (doorSize < DOOR_MAXIMUM_SIZE)) 
            setSize(++doorSize);
    }
    
    public void
    contractDoor()
    {
        if (doorCanContract()) {
            int doorSize = getSize();
            setSize(doorSize - DOOR_RESIZE);
        }
    }
    
    public boolean
    doorCanContract()
    {
        boolean doorCanContract;
        int doorSize = getSize();
        if (doorSize > DOOR_MINIMUM_SIZE)
            doorCanContract = true;
        else
            doorCanContract = false;
        return doorCanContract;
    }
    
    public boolean
    doorCanExpand()
    {
        boolean doorCanExpand;
        int doorSize = getSize();
        if (doorSize < DOOR_MAXIMUM_SIZE)
            doorCanExpand = true;
        else
            doorCanExpand = false;
        return doorCanExpand;
    }

    public void
    draw(Graphics g, Point pCmpRelPrnt)
    {
        int x = (int) (pCmpRelPrnt.getX() + getX());
        int y =  (int) (pCmpRelPrnt.getY() + getY());
        g.setColor(getColor());
        g.fillRect(x, y, getSize(), getSize());
    }

}
