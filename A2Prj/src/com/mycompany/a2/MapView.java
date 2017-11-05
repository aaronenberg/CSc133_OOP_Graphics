package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;

public class
MapView extends Container implements Observer
{
    public void
    update(Observable observable, Object data)
    {
        GameWorld gw = (GameWorld) observable;
        gw.map();
        System.out.println();
    }

}
