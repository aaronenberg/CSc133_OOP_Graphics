package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
ContractDoorCommand extends Command
{
    private GameWorld target;
    public
    ContractDoorCommand(GameWorld gw)
    {
        super("Contract");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if(target.contractSpaceshipDoor())
            System.out.println("The spaceship door contracts.\n");
        else
            System.out.println("Spaceship's door is already contracted to minimum size.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }
}
