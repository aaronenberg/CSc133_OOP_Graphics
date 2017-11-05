package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
ExpandDoorCommand extends Command
{
    private GameWorld target;
    public
    ExpandDoorCommand(GameWorld gw)
    {
        super("Expand");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if(target.expandSpaceshipDoor())
          System.out.println("The spaceship door expands.\n");
      else
          System.out.println("Spaceship's door is already expanded to maximum size.\n");

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
    }
}
