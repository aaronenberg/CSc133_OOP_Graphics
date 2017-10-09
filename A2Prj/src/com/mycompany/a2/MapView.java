package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class
MapView extends Container implements Observer
{
    private int height;
    private int width;

    public
    MapView()
    {
        height = this.getHeight();
        width = this.getWidth();
    }

    public void
    update(Observable observable, Object data)
    {
        GameWorld gw = (GameWorld) observable;
        gw.map();
        System.out.println();
    }

    public int
    mapHeight()
    {
        return this.height;
    }

    public int
    mapWidth()
    {
        return this.width;
    }
}
