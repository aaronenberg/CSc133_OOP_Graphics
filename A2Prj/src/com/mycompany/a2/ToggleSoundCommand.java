package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
ToggleSoundCommand extends Command
{
    private GameWorld target;
    public
    ToggleSoundCommand(GameWorld gw)
    {
        super("Sound");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.toggleSound();
    }
}
