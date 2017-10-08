package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
SpaceshipToAstronautCommand extends Command
{
    private GameWorld target;
    public
    SpaceshipToAstronautCommand(GameWorld gw)
    {
        super("MoveToAstronaut");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.spaceshipToAstronaut();
    }
}
