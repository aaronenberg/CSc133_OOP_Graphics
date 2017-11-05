package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
NewAlienCommand extends Command
{
    private GameWorld target;
    public
    NewAlienCommand(GameWorld gw)
    {
        super("NewAlien");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.getAliensRemaining() >= 2) {
            target.aliensCollide();
            System.out.println("Two aliens collide... A new alien spawns.\n");
        }
        else
            System.out.println("ERROR: There are less than two aliens remaining. "
                             + "There can not be a collision.\n");
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
