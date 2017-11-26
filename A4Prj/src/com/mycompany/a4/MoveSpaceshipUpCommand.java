package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipUpCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipUpCommand(GameWorld gw)
    {
        super("Up");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipUp();
        System.out.println("The spaceship moves up.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
