package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipLeftCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipLeftCommand(GameWorld gw)
    {
        super("Left");
        this.target = gw;
    }
    
    @Override
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipLeft();
    }
}
