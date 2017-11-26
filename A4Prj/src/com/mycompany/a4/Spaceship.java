package com.mycompany.a4;

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
    private static final int
        HINT_OF_RED = 15,
        HINT_OF_GREEN = 15,
        MEDIUM_BLUE = 128,
        DOOR_MINIMUM_SIZE = 50,
        DOOR_MAXIMUM_SIZE = 1024,
        INIT_SIZE = 100,
        RESIZE = 22;
    private boolean doorOpen;
    private static Spaceship spaceship;
    
    private
    Spaceship()
    {
        super(INIT_SIZE);
        doorOpen = false;
        super.setColor(HINT_OF_RED, HINT_OF_GREEN, MEDIUM_BLUE);
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

    /*
     * the door expands by 10 until its size is >= 1015
     * after this point, the door expands by 1 
     * to keep it from breaking passed 1024
     */
    
    public boolean
    resizeDoor(int keyCode)
    {
        int doorSize = getSize(); 
        if (doorCanContract() && (keyCode == ContractDoorCommand.getKeyCode())) {
            setSize(doorSize - RESIZE);
            return true;
        }
        else if (doorCanExpand() && (keyCode == ExpandDoorCommand.getKeyCode())) {
            setSize(doorSize + RESIZE);
            return true;
        }
        return false;
    }

    public void
    openDoor()
    {
        doorOpen = true;
    }

    public void
    closeDoor()
    {
        doorOpen = false;
    }
    
    public boolean
    doorCanContract()
    {
        return ((getSize() - RESIZE) >= DOOR_MINIMUM_SIZE);

    }
    
    public boolean
    doorCanExpand()
    {
        return ((getSize() + RESIZE) <= DOOR_MAXIMUM_SIZE);
    }

    public void
    draw(Graphics g, Point pCmpRelPrnt)
    {
        int width = getSize();
        int xOrigin = (int) (pCmpRelPrnt.getX() + getX());
        int yOrigin = (int) (pCmpRelPrnt.getY() + getY());
        int xCenter = xOrigin - width/2;
        int yCenter = yOrigin - width/2;
        g.setColor(getColor());
        g.fillRect(xCenter, yCenter, width, width);
    }

}
