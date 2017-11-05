package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class
AboutCommand extends Command
{
    private String  ok           = "OK";
    private String  aboutDialogBody = "Author: Aaron Enberg\n" +
                                       "Course: CSC133 Object-Oriented " +
                                       "Computer Graphics Programming\n" +
                                       "version 2";

    public
    AboutCommand()
    {
        super("About");
    }
    
    public void
    actionPerformed(ActionEvent evt)
    {
        Dialog.show( "About Space Fights Game",
                      aboutDialogBody,
                      ok, null 
                   );
    }
}
