package com.mycompany.a1;


import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.TextField;
import java.lang.String;


public class
Game extends Form
{

    private GameWorld gw;
	
    public
    Game()
    {
        gw = new GameWorld();
        gw.init();
        play();
    }
	
    private void
    play()
    {
        Label label = new Label("Enter a Command:");
        this.addComponent(label);
        final TextField commandField = new TextField();
        this.addComponent(commandField);
        this.show();

        commandField.addActionListener
        (
            new ActionListener()
            {
                public void
                actionPerformed(ActionEvent evt)
                {
                    String command = commandField.getText().toString();
                    commandField.clear();
                    switch (command.charAt(0))
                    {
                        case 'e':
                            gw.expand();
                            break;
                        case 'x':
                            System.out.println("Do you want to exit? (y) yes, (n) no.");
                            break;
                        case 'y':
                            System.exit(0);
                        case 'n':
                            break;
                        default:
                            System.out.println("Unknown command given... Ignoring input.");
                            break;
                    }
                }
            }
        );
    }
}
