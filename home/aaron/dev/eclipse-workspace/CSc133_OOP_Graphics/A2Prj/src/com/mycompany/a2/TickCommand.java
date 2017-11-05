package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
TickCommand extends Command
{
    private GameWorld target;
    public
    TickCommand(GameWorld gw)
    {
        super("Tick");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.getAstronautsRemaining() + target.getAliensRemaining() > 0) {
            target.tick();
            System.out.println("All aliens and healthy astronauts move.\n");

            System.out.println("''''''''''''''''''''''''''''''''''''''" +
                    "''''''''''''''''''''''''''''''''''''''\n");
        }
        
    }
}
