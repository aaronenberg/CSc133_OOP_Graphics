package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipRightCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipRightCommand(GameWorld gw)
    {
        super("Right");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipRight();
        System.out.println("The spaceship moves right.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
