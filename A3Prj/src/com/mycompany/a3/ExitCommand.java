package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class
ExitCommand extends Command
{
    private boolean exitConfirm    = false;
    private String   exit           = "Exit";
    private String   cancel         = "Cancel";
    private String   exitDialogBody = "Are you sure you want to exit?";

    public
    ExitCommand()
    {
        super("Exit");
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        exitConfirm = Dialog.show( "Confirm Exit",
                                    exitDialogBody,
                                    exit, cancel );
        if (exitConfirm)
            Display.getInstance().exitApplication();
    }
}
