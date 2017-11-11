package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;

@SuppressWarnings("deprecation")
public class
ToggleSoundCommand extends Command
{
    private GameWorld target;
    public
    ToggleSoundCommand(GameWorld gw)
    {
        super("Sound");
        target = gw;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {   
        target.toggleSound();
        System.out.println(ScoreView.getSoundText() + "\n");
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                "''''''''''''''''''''''''''''''''''''''\n");
        SideMenuBar.closeCurrentMenu();
    }
}
