package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.TextField;

import java.lang.String;

public class Game extends Form {
    private GameWorld gw;
	
    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }
	
    private void play() {
        Label myLabel = new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField = new TextField();
        this.addComponent(myTextField);
        this.show();

        myTextField.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    String sCommand = myTextField.getText().toString();
                    myTextField.clear();
                    switch (sCommand.charAt(0)) {
                        case 'e':
                            gw.expand();
                            break;
                    }
                }
            }
        );
    }
}
