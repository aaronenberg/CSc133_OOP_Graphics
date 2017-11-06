package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public class
MapView extends Container implements Observer
{
    GameWorld gw;
    Point mapOriginRelPrnt;

    public void
    update(Observable observable, Object data)
    {
        gw = (GameWorld) observable;
        gw.map();
        repaint();
        System.out.println();
    }

    @Override
    public void
    paint(Graphics g)
    {
        super.paint(g);

        IIterator gameObjects = gw.getGameObjectCollection().getIterator();
        mapOriginRelPrnt = new Point(getX(), getY());

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();
            gameObject.draw(g, mapOriginRelPrnt);
        }
    }
}
