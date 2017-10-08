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
        if (target.getAliensRemaining() > 0) {
            target.spaceshipToAlien();
            System.out.println("The spaceship moves to a randomly selected alien.\n");

            System.out.println("''''''''''''''''''''''''''''''''''''''" +
                    "''''''''''''''''''''''''''''''''''''''\n");
        }
        else
            System.out.println("There are no aliens remaining to move to.");
    }
}
