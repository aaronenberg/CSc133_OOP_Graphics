package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
RescueCommand extends Command
{
    private GameWorld target;
    public
    RescueCommand(GameWorld gw)
    {
        super("Score");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (Game.gamePaused())
            return;
        target.openSpaceshipDoor();
        System.out.println("The spaceship door opens...");
        if (!target.rescue())
            System.out.println("but there is no one at the door.\n" +
                                "The door automatically shuts.\n");
        target.closeSpaceshipDoor();

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
