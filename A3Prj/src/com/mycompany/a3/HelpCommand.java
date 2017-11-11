package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class
HelpCommand extends Command
{
    private String  ok = "OK",
                     helpDialogBody =
            "'e' - expands the spaceship's door\n" + 
            "'c' - contracts the spaceship's door\n" + 
            "'s' - opens the spaceship's door to let in opponents and updates the score\n" +
            "       (you EARN points for astronauts, but LOSE points for aliens)\n" + 
            "'r' - moves the spaceship right\n" + 
            "'l' - moves the spaceship left\n" + 
            "'u' - moves the spaceship up\n" + 
            "'d' - moves the spaceship down\n" + 
            "'o' - moves the spaceship to a random astronaut\n" + 
            "'a' - moves the spaceship to a random alien\n" + 
            "'x' - shows the exit screen\n";

    public
    HelpCommand()
    {
        super("Help");
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        Dialog.show( "Commands Available",
                      helpDialogBody,
                      ok, null 
                   );
    }
}
