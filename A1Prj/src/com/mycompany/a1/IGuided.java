package com.mycompany.a1;


/*
 * interface which allows the user to
 * control the movement of a Rescuer subclass.
 */

public interface IGuided {

    public void
    moveLeft();

    public void
    moveRight();

    public void
    moveUp();

    public void
    moveDown();

    public void
    jumpToLocation(float x, float y);

}
