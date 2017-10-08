package com.mycompany.a2;

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
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
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
