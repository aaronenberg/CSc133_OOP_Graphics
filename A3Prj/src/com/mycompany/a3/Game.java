package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import java.lang.Runnable;


/*
 * Game is the controller which enforces rules as to what
 * commands a user can issue while playing the game.
 * It instantiates a GameWorld and calls its initLocationsOnMap() to
 * populate the map with GameObjects. Also responsible
 * for prompting the user for input and taking certain actions
 * based on that input. Most of these actions are calls to
 * GameWorld methods. When there are no more astronauts remaining,
 * the game ends.
 */
public class
Game extends Form implements Runnable
{
    private GameWorld      gameWorld = new GameWorld();
    private ScoreView      scoreView;
    private static MapView mapView;
    private int            mapHeight;
    private int            mapWidth;
    private static final int elapsedMilliSecs = 20;

    private static int 
            eKeyCode = 101,
            cKeyCode =  99,
            sKeyCode = 115,
            rKeyCode = 114,
            lKeyCode = 108,
            uKeyCode = 117,
            dKeyCode = 100,
            oKeyCode = 111,
            aKeyCode =  97,
            wKeyCode = 119,
            fKeyCode = 102,
            xKeyCode = 120;

    private Button 
            expandDoorButton           = new Button(),
            upButton                   = new Button(),
            leftButton                 = new Button(),
            spaceshipToAstronautButton = new Button(),
            contractDoorButton         = new Button(),
            downButton                 = new Button(),
            rightButton                = new Button(),
            spaceshipToAlienButton     = new Button(),
            rescueButton               = new Button(),
            newAlienButton             = new Button(),
            fightButton                = new Button();

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
    private ToggleSoundCommand          toggleSound                 = new ToggleSoundCommand(gameWorld);
    private ExitCommand                 exitCommand                 = new ExitCommand();
    private HelpCommand                 helpCommand                 = new HelpCommand();
    private AboutCommand                aboutCommand                = new AboutCommand();

    private CheckBox sideMenuCheckBox = new CheckBox("Sound");

    private Container controlWest;
    private Container controlEast;
    private Container controlSouth;

    private Toolbar toolbar;
    private UITimer timer;

    public
    Game()
    {
        gameWorld.init();
        scoreView = new ScoreView();
        mapView = new MapView();
        gameWorld.addObserver(scoreView);
        gameWorld.addObserver(mapView);

        timer = new UITimer(this);

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

        timer.schedule(elapsedMilliSecs, true, this);
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

    public void
    run()
    {
        gameWorld.tick(elapsedMilliSecs);
    }
}
