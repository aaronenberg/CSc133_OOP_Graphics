package com.mycompany.a4;


public interface
ICollider
{
    public boolean
    collidesWith(ICollider opponent);

    public void
    handleCollision(ICollider opponent);
}
