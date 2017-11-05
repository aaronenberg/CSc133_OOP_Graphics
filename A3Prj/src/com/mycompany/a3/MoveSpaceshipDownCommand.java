package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipDownCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipDownCommand(GameWorld gw)
    {
        super("Down");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipDown();
        System.out.println("The spaceship moves down.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
