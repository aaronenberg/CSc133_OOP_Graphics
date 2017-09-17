package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public abstract class GameObject {

    private int size;
    private int color;
    private Point location = new Point();


    public int getColor() {
        return this.color;
    }
    public void setColor(int r, int g, int b) {
        this.color = ColorUtil.rgb(r, g, b);
    }


    public Point getLocation() {
        return this.location;
    }
    public void setLocation(float pX, float pY) {
        this.setX(pX);
        this.setY(pY);
    }

    
    private void setX(float x) {
        this.location.setX(x);
    }
    private void setY(float y) {
        this.location.setY(y);
    }


    public int getSize() {
        return this.size;
    }
    public void setSize(int newSize) {
        this.size = newSize;
    }


}
