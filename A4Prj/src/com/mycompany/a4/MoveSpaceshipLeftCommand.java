package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipLeftCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipLeftCommand(GameWorld gw)
    {
        super("Left");
        target = gw;
    }
    
    @Override
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipLeft();
        System.out.println("The spaceship moves left.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
