package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class GameObject {
    private int size;
    private Point location = new Point();

    public int getSize() {
        return this.size;
    }

    public void setSize(int newSize) {
        this.size = newSize;
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

}