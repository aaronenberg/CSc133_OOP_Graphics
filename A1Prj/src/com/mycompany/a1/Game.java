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
                        case 'a':
                            gw.transferSpaceshipToAlien();
                            System.out.println("The spaceship has transferred to a randomly selected alien.");
                            break;
                        case 'o':
                            gw.transferSpaceshipToAstronaut();
                            System.out.println("The spaceship has transferred to a randomly selected astronaut.");
                            break;
                        case 'e':
                            if(gw.expandSpaceshipDoor())
                                System.out.println("The spaceship door expands.");
                            else
                                System.out.println("ERROR: The size of the spaceship door is already reached its maximum."
                                                 + " Expand failed.");
                            break;
                        case 'c':
                            if (gw.contractSpaceshipDoor())
                                System.out.println("The spaceship door contracts.");
                            else
                                System.out.println("ERROR: The size of the spaceship door is already reached its minimum."
                                                 + " Contract failed.");
                            break;
                        case 'r':
                            gw.moveSpaceshipRight();
                            System.out.println("The spaceship moves right.");
                            break;
                        case 'l':
                            gw.moveSpaceshipLeft();
                            System.out.println("The spaceship moves left.");
                            break;
                        case 'u':
                            gw.moveSpaceshipUp();
                            System.out.println("The spaceship moves up.");
                            break;
                        case 'd':
                            gw.moveSpaceshipDown();
                            System.out.println("The spaceship moves down.");
                            break;
                        case 's':
                            gw.openSpaceshipDoor();
                            System.out.println("The spaceship door opens.");
                            gw.rescue();
                            System.out.println("Current score: " + gw.getScore());
                            gw.closeSpaceshipDoor();
                            System.out.println("The door automatically shuts.");
                            break;
                        case 'x':
                            System.out.println("Do you want to exit? (y) yes, (n) no.");
                            break;
                        case 'y':
                            System.exit(0);
                        case 'n':
                            break;
                        case 'm':
                            System.out.println("Here is the current world state: \n");
                            gw.map();
                            System.out.print("\n");
                            break;
                        case 't':
                            gw.tick();
                            System.out.println("The clock has ticked. All opponents have moved.");
                            break;
                        case 'f':
                            if (gw.fight() == true)
                                System.out.println("A fight breaks out between alien and astronaut... "
                                                  + "The astronaut's health has decreased.");
                            else
                                System.out.println("ERROR: There are no aliens remaining to fight.");
                            break;
                        case 'p':
                            System.out.println(gw.toString());
                            break;
                        case 'w':
                            if (gw.aliensCollide() == true)
                                System.out.println("Two aliens collide... A new alien has spawned!");
                            else
                                System.out.println("ERROR: There are less than two aliens remaining. "
                                                  + "There can not be a collision.");
                            break;
                        default:
                            System.out.println("Unknown command given... Ignoring input.");
                            break;
                    }
                    if (gw.getAstronautsRemaining() == 0) {
                        System.out.println("All astronauts rescued! The game has ended.");
                        System.exit(0);
                    }
                }
            }
        );
    }
}
