package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
RescueCommand extends Command
{
    private GameWorld target;
    public
    RescueCommand(GameWorld gw)
    {
        super("Score");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.rescue();
    }
}
