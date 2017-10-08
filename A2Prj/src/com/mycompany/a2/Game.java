package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;


/*
 * Game is the controller which enforces rules as to what
 * commands a user can issue while playing the game.
 * It instantiates a GameWorld and calls its init() to 
 * populate the game with GameObjects. Also responsible
 * for prompting the user for input and taking certain actions
 * based on that input. Most of these actions are calls to 
 * GameWorld methods. When there are no more astronauts remaining,
 * the game ends.
 */
public class
Game extends Form
{

    private GameWorld gameWorld;
    private ScoreView scoreView;

    private Button expandDoorButton;
    private ExpandDoorCommand expandDoorCommand;
    private Button upButton;
    private Button leftButton;
    private Button moveToAstronautButton;
    private Button contractButton;
    private Button downButton;
    private Button rightButton;
    private Button moveToAlienButton;
    private Button scoreButton;
    private Button newAlienButton;
    private Button fightButton;
    private Button tickButton;

    private Container controlWest;
    private Container controlEast;
    private Container controlSouth;
    
    private Toolbar toolbar;
    private Command sideMenuItem1;
    private Command helpClickable;
	
    public
    Game()
    {
        gameWorld = new GameWorld();
        gameWorld.init();
        //play();
        scoreView = new ScoreView();
        scoreView.setLayout(new FlowLayout(Component.CENTER));
        scoreView.getAllStyles().setBorder(Border.createCompoundBorder(
                                           Border.createLineBorder(1),
                                           Border.createLineBorder(1),
                                           Border.createLineBorder(0),
                                           Border.createLineBorder(0)));
        scoreView.getAllStyles().setPaddingTop(10);

        controlWest = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlWest.getAllStyles().setBorder(Border.createCompoundBorder(
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(1)));
        expandDoorCommand = new ExpandDoorCommand(gameWorld);
        expandDoorButton = new Button("Expand");
        expandDoorButton.setCommand(expandDoorCommand);
        upButton = new Button("Up");
        leftButton = new Button("Left");
        moveToAstronautButton = new Button("MoveToAstronaut");
        
        controlEast = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlEast.getAllStyles().setBorder(Border.createCompoundBorder(
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(1),
                                             Border.createLineBorder(0)));
        contractButton = new Button("Contract");
        downButton = new Button("Down");
        rightButton = new Button("Right");
        moveToAlienButton = new Button("MoveToAlien");
        scoreButton = new Button("Score");
        
        controlSouth = new Container(new FlowLayout(Component.CENTER));
        controlSouth.getAllStyles().setBorder(Border.createCompoundBorder(
                                              Border.createLineBorder(1),
                                              Border.createLineBorder(0),
                                              Border.createLineBorder(0),
                                              Border.createLineBorder(0)));
        newAlienButton = new Button ("NewAlien");
        fightButton = new Button ("Fight");
        tickButton = new Button ("Tick");
        

        gameWorld.addObserver(scoreView);
        gameWorld.notifyObservers();
        
        this.setLayout(new BorderLayout());
        this.setTitle("Space Fights Game");

        controlWest.getAllStyles().setPaddingTop(100);
        controlWest.add(expandDoorButton);
        controlWest.add(upButton);
        controlWest.add(leftButton);
        controlWest.add(moveToAstronautButton);
        
        controlEast.getAllStyles().setPaddingTop(100);
        controlEast.add(contractButton);
        controlEast.add(downButton);
        controlEast.add(rightButton);
        controlEast.add(moveToAlienButton);
        controlEast.add(scoreButton);
        
        controlSouth.add(newAlienButton);
        controlSouth.add(fightButton);
        controlSouth.add(tickButton);
        
        toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.getAllStyles().setPaddingTop(40);
        toolbar.setTitle("Space Fights Game");
        toolbar.setTitleCentered(true);
        sideMenuItem1 = new Command("test");
        helpClickable = new Command("Help");
        toolbar.addCommandToSideMenu(sideMenuItem1);
        toolbar.addCommandToRightBar(helpClickable);

        this.addComponent(BorderLayout.NORTH, scoreView);
        this.addComponent(BorderLayout.WEST, controlWest);
        this.addComponent(BorderLayout.EAST, controlEast);
        this.addComponent(BorderLayout.SOUTH, controlSouth);
        
        
        
        this.show();
    }
	
//    private void
//    play()
//    {
//        Label label = new Label("Enter a Command:");
//        this.addComponent(label);
//        final TextField commandField = new TextField();
//        this.addComponent(commandField);
//        this.show();
//
//        commandField.addActionListener
//        (
//            new ActionListener()
//            {
//                public void
//                actionPerformed(ActionEvent evt)
//                {
//                    String command = commandField.getText().toString();
//                    commandField.clear();
//                    switch (command.charAt(0))
//                    {
//                        case 'a':
//                            gw.transferSpaceshipToAlien();
//                            System.out.println("The spaceship has transferred to a randomly selected alien.");
//                            break;
//                        case 'o':
//                            gw.transferSpaceshipToAstronaut();
//                            System.out.println("The spaceship has transferred to a randomly selected astronaut.");
//                            break;
//                        case 'e':
//                            if(gw.expandSpaceshipDoor())
//                                System.out.println("The spaceship door expands.");
//                            else
//                                System.out.println("ERROR: The size of the spaceship door is already reached its maximum."
//                                                 + " Expand failed.");
//                            break;
//                        case 'c':
//                            if (gw.contractSpaceshipDoor())
//                                System.out.println("The spaceship door contracts.");
//                            else
//                                System.out.println("ERROR: The size of the spaceship door is already reached its minimum."
//                                                 + " Contract failed.");
//                            break;
//                        case 'r':
//                            gw.moveSpaceshipRight();
//                            System.out.println("The spaceship moves right.");
//                            break;
//                        case 'l':
//                            gw.moveSpaceshipLeft();
//                            System.out.println("The spaceship moves left.");
//                            break;
//                        case 'u':
//                            gw.moveSpaceshipUp();
//                            System.out.println("The spaceship moves up.");
//                            break;
//                        case 'd':
//                            gw.moveSpaceshipDown();
//                            System.out.println("The spaceship moves down.");
//                            break;
//                        case 's':
//                            gw.openSpaceshipDoor();
//                            System.out.println("The spaceship door opens.");
//                            gw.rescue();
//                            System.out.println("Current score: " + gw.getScore());
//                            gw.closeSpaceshipDoor();
//                            System.out.println("The door automatically shuts.");
//                            break;
//                        case 'x':
//                            System.out.println("Do you want to exit? (y) yes, (n) no.");
//                            break;
//                        case 'y':
//                            System.exit(0);
//                        case 'n':
//                            break;
//                        case 'm':
//                            System.out.println("Here is the current world state: \n");
//                            gw.map();
//                            System.out.print("\n");
//                            break;
//                        case 't':
//                            gw.tick();
//                            System.out.println("The clock has ticked. All opponents have moved.");
//                            break;
//                        case 'f':
//                            if (gw.fight() == true)
//                                System.out.println("A fight breaks out between alien and astronaut... "
//                                                  + "The astronaut's health has decreased.");
//                            else
//                                System.out.println("ERROR: There are no aliens remaining to fight.");
//                            break;
//                        case 'p':
//                            System.out.println(gw.toString());
//                            break;
//                        case 'w':
//                            if (gw.aliensCollide() == true)
//                                System.out.println("Two aliens collide... A new alien has spawned!");
//                            else
//                                System.out.println("ERROR: There are less than two aliens remaining. "
//                                                  + "There can not be a collision.");
//                            break;
//                        default:
//                            System.out.println("Unknown command given... Ignoring input.");
//                            break;
//                    }
//                    if (gw.getAstronautsRemaining() == 0) {
//                        System.out.println("All astronauts rescued! The game has ended.");
//                        System.exit(0);
//                    }
//                }
//            }
//        );
//    }
}
