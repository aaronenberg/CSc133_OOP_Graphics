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
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.tick();
    }
}
