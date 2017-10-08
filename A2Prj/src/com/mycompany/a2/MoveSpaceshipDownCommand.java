package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
MoveSpaceshipDownCommand extends Command
{
    private GameWorld target;
    public
    MoveSpaceshipDownCommand(GameWorld gw)
    {
        super("Down");
        this.target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        target.moveSpaceshipDown();
    }
}
