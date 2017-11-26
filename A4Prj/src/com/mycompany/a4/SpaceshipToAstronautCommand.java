package com.mycompany.a4;

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
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.getAstronautsRemaining() > 0) {
            target.spaceshipToAstronaut();
            System.out.println("The spaceship moves to a randomly selected astronaut.\n");

            System.out.println("''''''''''''''''''''''''''''''''''''''" +
                    "''''''''''''''''''''''''''''''''''''''\n");
        }
    }
}
