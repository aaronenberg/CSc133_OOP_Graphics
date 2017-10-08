package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipRightCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipRightCommand(GameWorld gw)
    {
        super("Right");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipRight();
    }
}
