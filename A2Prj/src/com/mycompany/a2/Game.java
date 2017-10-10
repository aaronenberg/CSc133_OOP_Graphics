package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;


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
    private GameWorld      gameWorld = new GameWorld();
    private ScoreView      scoreView;
    private static MapView mapView;
    private int            mapHeight;
    private int            mapWidth;
    
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
    private ToggleSoundCommand          toggleSound                 = new ToggleSoundCommand(gameWorld);
    private ExitCommand                 exitCommand                 = new ExitCommand();
    private HelpCommand                 helpCommand                 = new HelpCommand();
    private AboutCommand                aboutCommand                = new AboutCommand();
    
    private CheckBox sideMenuCheckBox = new CheckBox("Sound");
    
    private Container controlWest;
    private Container controlEast;
    private Container controlSouth;

    private Toolbar toolbar;

    public
    Game()
    {
        gameWorld.init();
        scoreView = new ScoreView();
        mapView = new MapView();
        gameWorld.addObserver(scoreView);
        gameWorld.addObserver(mapView);

        toolbar = new Toolbar();
        this.setToolbar(toolbar);
        this.setLayout(new BorderLayout());

        scoreView.setLayout(new FlowLayout(Component.CENTER));
        

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
        this.addKeyListener(xKeyCode, exitCommand);

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
        sideMenuCheckBox.setCommand(toggleSound);

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
        toggleSound.putClientProperty("SideComponent", sideMenuCheckBox);
        toolbar.addCommandToSideMenu(toggleSound);
        toolbar.addCommandToSideMenu(exitCommand);
        toolbar.addCommandToSideMenu(rescueCommand);
        toolbar.addCommandToSideMenu(aboutCommand);
        toolbar.addCommandToRightBar(helpCommand);

        this.addComponent(BorderLayout.NORTH, scoreView);
        this.addComponent(BorderLayout.WEST, controlWest);
        this.addComponent(BorderLayout.EAST, controlEast);
        this.addComponent(BorderLayout.SOUTH, controlSouth);
        this.addComponent(BorderLayout.CENTER, mapView);

        this.show();
        /*
         * container dimensions are set only after calling show()
         * so we query them here and set locations of each game object
         * randomly within these limits, but not necessarily inside the
         * MapView container yet.
         */
        mapHeight = getMapHeight();
        mapWidth = getMapWidth();
        gameWorld.initLocationsOnMap();

        System.out.println("MapView dimensions are: " +
                            mapWidth + "x" + mapHeight + 
                            " (width X height)\n");
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                           "''''''''''''''''''''''''''''''''''''''\n");
    }

    public static int
    getMapHeight()
    {
        return mapView.getHeight();
    }
    
    public static int
    getMapWidth()
    {
        return mapView.getWidth();
    }

}
