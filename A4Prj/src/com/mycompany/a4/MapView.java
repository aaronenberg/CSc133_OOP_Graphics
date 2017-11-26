package com.mycompany.a4;

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
        GameWorld.map();
        repaint();
        System.out.println();
    }

    @Override
    public void
    paint(Graphics g)
    {
        super.paint(g);

        IIterator gameObjects = GameWorld.getGameObjectCollection().getIterator();
        Point mapOriginRelPrnt = new Point(getX(), getY());

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();
            gameObject.draw(g, mapOriginRelPrnt);
        }
    }

    @Override
    public void
    pointerPressed(int x, int y)
    {
        if (!Game.gamePaused())
            return;
        float xRelPrntOrigin = x - getParent().getAbsoluteX();
        float yRelPrntOrigin = y - getParent().getAbsoluteY();
        Point pPtrRelPrnt = new Point(xRelPrntOrigin, yRelPrntOrigin);
        Point mapOriginRelPrnt = new Point(getX(), getY());
        IIterator gameObjects = GameWorld.getGameObjectCollection().getIterator();

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof ISelectable) {
                ISelectable astronaut = (ISelectable) gameObject;

                if (astronaut.contains(pPtrRelPrnt, mapOriginRelPrnt))
                    astronaut.setSelected(true);
                else
                    astronaut.setSelected(false);                    
            }
        }
        repaint();
    }

}
