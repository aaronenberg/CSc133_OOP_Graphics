package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
GameModeCommand extends Command
{
    private Game target;
    

    public
    GameModeCommand(Game game)
    {
        super("Pause");
        target = game;
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        if (!Game.gamePaused()) {
            target.pauseGame();
            BGSound.pause();
            target.repaint();
            System.out.println("The game is paused.");
        }
        else {
            target.resumeGame();
            if (GameWorld.getSound())
                BGSound.play();
            target.repaint();
            System.out.println("The game has resumed.");
        }

        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }

    
}
