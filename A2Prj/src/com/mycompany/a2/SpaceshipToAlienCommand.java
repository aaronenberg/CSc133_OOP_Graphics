package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
SpaceshipToAlienCommand extends Command
{
    private GameWorld target;
    public
    SpaceshipToAlienCommand(GameWorld gw)
    {
        super("MoveToAlien");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.spaceshipToAlien();
    }
}
