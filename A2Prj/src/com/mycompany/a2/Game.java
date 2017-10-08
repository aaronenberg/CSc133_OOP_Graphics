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
    private GameWorld gameWorld = new GameWorld();
    private ScoreView scoreView;
    private MapView mapView;
    
    private static int eKeyCode = 101;
    private static int cKeyCode =  99;
    private static int sKeyCode = 115;
    private static int rKeyCode = 114;
    private static int lKeyCode = 108;
    private static int uKeyCode = 117;
    private static int dKeyCode = 100;
    private static int oKeyCode = 111;
    private static int aKeyCode =  97;
    private static int wKeyCode = 119;
    private static int fKeyCode = 102;
    private static int tKeyCode = 116;
    private static int xKeyCode = 120;

    private Button expandDoorButton           = new Button();
    private Button upButton                   = new Button();
    private Button leftButton                 = new Button();
    private Button spaceshipToAstronautButton = new Button();
    private Button contractDoorButton         = new Button();
    private Button downButton                 = new Button();
    private Button rightButton                = new Button();
    private Button spaceshipToAlienButton     = new Button();
    private Button rescueButton               = new Button();
    private Button newAlienButton             = new Button();
    private Button fightButton                = new Button();
    private Button tickButton                 = new Button();

    private ExpandDoorCommand           expandDoorCommand           = new ExpandDoorCommand(gameWorld);
    private MoveSpaceshipUpCommand      moveSpaceshipUpCommand      = new MoveSpaceshipUpCommand(gameWorld);
    private MoveSpaceshipLeftCommand    moveSpaceshipLeftCommand    = new MoveSpaceshipLeftCommand(gameWorld);
    private SpaceshipToAstronautCommand spaceshipToAstronautCommand = new SpaceshipToAstronautCommand(gameWorld);
    private ContractDoorCommand         contractDoorCommand         = new ContractDoorCommand(gameWorld);
    private MoveSpaceshipDownCommand    moveSpaceshipDownCommand    = new MoveSpaceshipDownCommand(gameWorld);
    private MoveSpaceshipRightCommand   moveSpaceshipRightCommand   = new MoveSpaceshipRightCommand(gameWorld);
    private SpaceshipToAlienCommand     spaceshipToAlienCommand     = new SpaceshipToAlienCommand(gameWorld);
    private RescueCommand               rescueCommand               = new RescueCommand(gameWorld);
    private NewAlienCommand             newAlienCommand             = new NewAlienCommand(gameWorld);
    private FightCommand                fightCommand                = new FightCommand(gameWorld);
    private TickCommand                 tickCommand                 = new TickCommand(gameWorld);
//    private exitCommand                 exitCommand                 = new ExitCommand(gameWorld);

    private Container controlWest;
    private Container controlEast;
    private Container controlSouth;

    private Toolbar toolbar;
    private ToggleSoundCommand toggleSound = new ToggleSoundCommand(gameWorld);
    private Command sideMenuItem1;
    private Command helpClickable;

    public
    Game()
    {
        gameWorld.init();

        scoreView = new ScoreView();
        mapView = new MapView();

        gameWorld.addObserver(scoreView);
        gameWorld.addObserver(mapView);
        gameWorld.notifyObservers();
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                           "''''''''''''''''''''''''''''''''''''''\n");

        toolbar = new Toolbar();
        this.setToolbar(toolbar);
        this.setLayout(new BorderLayout());

        scoreView.setLayout(new FlowLayout(Component.CENTER));
        scoreView.getAllStyles().setPaddingTop(10);
        scoreView.getAllStyles().setBorder(Border.createCompoundBorder(
                                           Border.createLineBorder(1),
                                           Border.createLineBorder(1),
                                           Border.createLineBorder(0),
                                           Border.createLineBorder(0)));

        this.addKeyListener(eKeyCode, expandDoorCommand);
        this.addKeyListener(cKeyCode, contractDoorCommand);
        this.addKeyListener(sKeyCode, rescueCommand);
        this.addKeyListener(rKeyCode, moveSpaceshipRightCommand);
        this.addKeyListener(lKeyCode, moveSpaceshipLeftCommand);
        this.addKeyListener(uKeyCode, moveSpaceshipUpCommand);
        this.addKeyListener(dKeyCode, moveSpaceshipDownCommand);
        this.addKeyListener(oKeyCode, spaceshipToAstronautCommand);
        this.addKeyListener(aKeyCode, spaceshipToAlienCommand);
        this.addKeyListener(wKeyCode, newAlienCommand);
        this.addKeyListener(fKeyCode, fightCommand);
        this.addKeyListener(tKeyCode, tickCommand);
//        this.addKeyListener(xKeyCode, exitCommand);
        
        expandDoorButton.setCommand(expandDoorCommand);
        upButton.setCommand(moveSpaceshipUpCommand);
        leftButton.setCommand(moveSpaceshipLeftCommand);
        spaceshipToAstronautButton.setCommand(spaceshipToAstronautCommand);
        contractDoorButton.setCommand(contractDoorCommand);
        downButton.setCommand(moveSpaceshipDownCommand);
        rightButton.setCommand(moveSpaceshipRightCommand);
        spaceshipToAlienButton.setCommand(spaceshipToAlienCommand);
        rescueButton.setCommand(rescueCommand);
        newAlienButton.setCommand(newAlienCommand);
        fightButton.setCommand(fightCommand);
        tickButton.setCommand(tickCommand);

        controlWest = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlWest.getAllStyles().setPaddingTop(100);
        controlWest.getAllStyles().setBorder(Border.createCompoundBorder(
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(1)));

        controlEast = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlEast.getAllStyles().setPaddingTop(100);
        controlEast.getAllStyles().setBorder(Border.createCompoundBorder(
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(1),
                                             Border.createLineBorder(0)));

        controlSouth = new Container(new FlowLayout(Component.CENTER));
        controlSouth.getAllStyles().setBorder(Border.createCompoundBorder(
                                              Border.createLineBorder(1),
                                              Border.createLineBorder(0),
                                              Border.createLineBorder(0),
                                              Border.createLineBorder(0)));

        controlWest.add(expandDoorButton);
        controlWest.add(upButton);
        controlWest.add(leftButton);
        controlWest.add(spaceshipToAstronautButton);

        controlEast.add(contractDoorButton);
        controlEast.add(downButton);
        controlEast.add(rightButton);
        controlEast.add(spaceshipToAlienButton);
        controlEast.add(rescueButton);

        controlSouth.add(newAlienButton);
        controlSouth.add(fightButton);
        controlSouth.add(tickButton);

        
        toolbar.getAllStyles().setPaddingTop(40);
        toolbar.setTitle("Space Fights Game");
        toolbar.setTitleCentered(true);
        sideMenuItem1 = new Command("test");
        helpClickable = new Command("Help");
        toolbar.addCommandToSideMenu(toggleSound);
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
