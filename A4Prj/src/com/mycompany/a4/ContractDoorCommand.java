package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
ContractDoorCommand extends Command
{
    private GameWorld target;
    private static int keyCode;

    public
    ContractDoorCommand(GameWorld gw, int code)
    {
        super("Contract");
        target = gw;
        keyCode = code;
    }

    @Override
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.resizeSpaceshipDoor(keyCode))
            System.out.println("The spaceship door contracts.\n");
        else
            System.out.println("Spaceship's door is already contracted to minimum size.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }

    public static int
    getKeyCode()
    {
        return keyCode;
    }
}
