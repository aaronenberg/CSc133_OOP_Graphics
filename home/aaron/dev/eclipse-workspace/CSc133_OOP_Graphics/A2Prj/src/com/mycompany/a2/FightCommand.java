package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
FightCommand extends Command
{
    private GameWorld target;
    public
    FightCommand(GameWorld gw)
    {
        super("Fight");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.fight()) 
            System.out.println("A fight breaks out between alien and astronaut... " +
                               "The astronaut's health has decreased.\n");
        else
            System.out.println("ERROR: There are no aliens remaining to fight.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
