package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
FightCommand extends Command
{
    private GameWorld target;
    public
    FightCommand(GameWorld gw)
    {
        super("Fight");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.fight();
    }
}
