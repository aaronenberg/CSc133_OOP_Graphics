package com.mycompany.a1;

public class
Spaceship extends Rescuer
{
    private static final int HINT_OF_RED = 15;
    private static final int DARK_GREEN = 128;
    private static final int HINT_OF_BLUE = 30;
    private static final int DOOR_EXPAND_CONTRACT_AMOUNT = 10;
    private static final int DOOR_MINIMUM_SIZE = 50;
    private static final int DOOR_MAXIMUM_SIZE = 1024;
    private boolean doorState;
    public
    Spaceship()
    {
        super();
        setSize(100);
        this.doorState = false;
        super.setColor(HINT_OF_RED, DARK_GREEN, HINT_OF_BLUE);
    }
    
    public void
    setColor()
    {}
    
    public boolean
    doorIsOpen()
    {
        return this.doorState;
    }
    
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
    
    public void
    expandDoor()
    {
        int doorSize = getSize();
        if (doorSize < 1014)
            setSize(doorSize + DOOR_EXPAND_CONTRACT_AMOUNT);
        else if (doorSize >= 1020 && doorSize < 1024)
            setSize(doorSize++);
    }
    
    public void
    contractDoor()
    {
        if (doorCanContract()) {
            int doorSize = getSize();
            setSize(doorSize - DOOR_EXPAND_CONTRACT_AMOUNT);
        }
    }
    
    public boolean
    doorCanContract()
    {
        boolean doorCanContract;
        int doorSize = getSize();
        if (doorSize > 50)
            doorCanContract = true;
        else
            doorCanContract = false;
        return doorCanContract;
    }
}
