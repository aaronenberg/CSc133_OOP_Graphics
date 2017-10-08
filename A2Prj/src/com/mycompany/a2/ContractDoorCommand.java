package com.mycompany.a2;

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
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.contractSpaceshipDoor();
    }
}
