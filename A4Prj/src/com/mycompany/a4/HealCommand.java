package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class
HealCommand extends Command
{
    private GameWorld target;

    public
    HealCommand(GameWorld gw)
    {
        super("Heal");
        target = gw;
    }

    @Override
    public void
    actionPerformed(ActionEvent evt)
    {
        if (target.healAstronaut())
            System.out.println("The astronaut is healed back to full health.\n");
        else
            System.out.println("You must select an astronaut to heal.\n");
        
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }
}
