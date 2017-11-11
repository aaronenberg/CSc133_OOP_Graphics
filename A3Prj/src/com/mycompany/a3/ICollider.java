package com.mycompany.a3;


public interface
ICollider
{
    public boolean
    collidesWith(ICollider opponent);

    public void
    handleCollision(ICollider opponent);
}
