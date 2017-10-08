package com.mycompany.a2;

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
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.aliensCollide();
        System.out.println("A new alien spawns.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
