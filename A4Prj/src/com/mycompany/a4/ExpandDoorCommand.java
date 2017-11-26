package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
ExpandDoorCommand extends Command
{
    private GameWorld target;
    private static int keyCode;

    public
    ExpandDoorCommand(GameWorld gw, int code)
    {
        super("Expand");
        target = gw;
        keyCode = code;
    }

    @Override
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.resizeSpaceshipDoor(keyCode))
          System.out.println("The spaceship door expands.\n");
        else
          System.out.println("Spaceship's door is already expanded to maximum size.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }

    public static int
    getKeyCode()
    {
        return keyCode;
    }
}
