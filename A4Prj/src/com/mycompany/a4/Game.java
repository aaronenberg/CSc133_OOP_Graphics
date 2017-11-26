package com.mycompany.a4;

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
    private GameWorld gameWorld = new GameWorld();
    private ScoreView scoreView;
    private static MapView mapView;
    private int
        mapHeight,
        mapWidth;
    private static final int elapsedMilliSecs = 20;
    private static boolean gamePaused = false;
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
        healButton                 = new Button(),
        gameModeButton             = new Button();

    private ExpandDoorCommand           expandDoorCommand           = new ExpandDoorCommand(gameWorld, eKeyCode);
    private MoveSpaceshipUpCommand      moveSpaceshipUpCommand      = new MoveSpaceshipUpCommand(gameWorld);
    private MoveSpaceshipLeftCommand    moveSpaceshipLeftCommand    = new MoveSpaceshipLeftCommand(gameWorld);
    private SpaceshipToAstronautCommand spaceshipToAstronautCommand = new SpaceshipToAstronautCommand(gameWorld);
    private ContractDoorCommand         contractDoorCommand         = new ContractDoorCommand(gameWorld, cKeyCode);
    private MoveSpaceshipDownCommand    moveSpaceshipDownCommand    = new MoveSpaceshipDownCommand(gameWorld);
    private MoveSpaceshipRightCommand   moveSpaceshipRightCommand   = new MoveSpaceshipRightCommand(gameWorld);
    private SpaceshipToAlienCommand     spaceshipToAlienCommand     = new SpaceshipToAlienCommand(gameWorld);
    private RescueCommand               rescueCommand               = new RescueCommand(gameWorld);
    private HealCommand                 healCommand                 = new HealCommand(gameWorld);
    private GameModeCommand             gameModeCommand             = new GameModeCommand(this);
    private ToggleSoundCommand          toggleSound                 = new ToggleSoundCommand(gameWorld);
    private ExitCommand                 exitCommand                 = new ExitCommand();
    private HelpCommand                 helpCommand                 = new HelpCommand();
    private AboutCommand                aboutCommand                = new AboutCommand();

    private CheckBox sideMenuCheckBox = new CheckBox();

    private Container
        controlWest,
        controlEast,
        controlSouth;

    private Toolbar toolbar;
    private static UITimer timer;

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
        setToolbar(toolbar);

        setLayout(new BorderLayout());
        scoreView.setLayout(new FlowLayout(Component.CENTER));

        addKeyListener(xKeyCode, exitCommand);

        setButtonsDisabledStyle();
        bindCommandsToButtons();
        setPlayModeCommands();
        setControlWest();
        setControlEast();
        setControlSouth();
        styleToolbar();
        addCommandsToToolbar();
        addComponentsToControlRegions();

        show();

        /*
         * container dimensions are set only after calling show()
         * so we query them here and set locations of each game object
         * randomly within those limits.
         */
        mapHeight = getMapHeight();
        mapWidth = getMapWidth();
        gameWorld.initLocationsOnMap();
        printMapDimensions();

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

    private void
    printMapDimensions()
    {
        System.out.println("MapView dimensions are: " +
                mapWidth + "x" + mapHeight +
                " (width X height)\n");
        System.out.println("''''''''''''''''''''''''''''''''''''''" +
                            "''''''''''''''''''''''''''''''''''''''\n");
    }
    public void
    run()
    {
        gameWorld.tick(elapsedMilliSecs);
        gameWorld.checkForAndHandleCollisions();
    }

    public static UITimer
    getUITimer()
    {
        return timer;
    }
    public void
    resumeGame()
    {
        timer.schedule(elapsedMilliSecs, true, this);
        GameWorld.clearSelected();
        setPlayModeCommands();
        gamePaused = false;
        gameModeButton.setText("Pause");
        controlSouth.repaint();
    }

    public void
    pauseGame()
    {
        timer.cancel();
        gamePaused = true;
        setPauseModeCommands();
        gameModeButton.setText("  Play  ");
        controlSouth.repaint();
    }

    public static boolean
    gamePaused()
    {
        return gamePaused;
    }

    private void
    setPauseModeCommands()
    {
        expandDoorButton.setEnabled(false);
        upButton.setEnabled(false);
        leftButton.setEnabled(false);
        spaceshipToAstronautButton.setEnabled(false);
        contractDoorButton.setEnabled(false);
        downButton.setEnabled(false);
        rightButton.setEnabled(false);
        spaceshipToAlienButton.setEnabled(false);
        rescueButton.setEnabled(false);
        healButton.setEnabled(true);
        rescueCommand.setEnabled(false);

        removeKeyListener(eKeyCode, expandDoorCommand);
        removeKeyListener(cKeyCode, contractDoorCommand);
        removeKeyListener(sKeyCode, rescueCommand);
        removeKeyListener(rKeyCode, moveSpaceshipRightCommand);
        removeKeyListener(lKeyCode, moveSpaceshipLeftCommand);
        removeKeyListener(uKeyCode, moveSpaceshipUpCommand);
        removeKeyListener(dKeyCode, moveSpaceshipDownCommand);
        removeKeyListener(oKeyCode, spaceshipToAstronautCommand);
        removeKeyListener(aKeyCode, spaceshipToAlienCommand);
    }

    private void
    setPlayModeCommands()
    {
        expandDoorButton.setEnabled(true);
        upButton.setEnabled(true);
        leftButton.setEnabled(true);
        spaceshipToAstronautButton.setEnabled(true);
        contractDoorButton.setEnabled(true);
        downButton.setEnabled(true);
        rightButton.setEnabled(true);
        spaceshipToAlienButton.setEnabled(true);
        rescueButton.setEnabled(true);
        healButton.setEnabled(false);
        gameModeButton.setEnabled(true);
        rescueCommand.setEnabled(true);

        addKeyListener(eKeyCode, expandDoorCommand);
        addKeyListener(cKeyCode, contractDoorCommand);
        addKeyListener(sKeyCode, rescueCommand);
        addKeyListener(rKeyCode, moveSpaceshipRightCommand);
        addKeyListener(lKeyCode, moveSpaceshipLeftCommand);
        addKeyListener(uKeyCode, moveSpaceshipUpCommand);
        addKeyListener(dKeyCode, moveSpaceshipDownCommand);
        addKeyListener(oKeyCode, spaceshipToAstronautCommand);
        addKeyListener(aKeyCode, spaceshipToAlienCommand);
        
    }

    private void
    setButtonsDisabledStyle()
    {
        expandDoorButton.getDisabledStyle().setBgTransparency(0);
        expandDoorButton.getDisabledStyle().setFgColor(0);
        upButton.getDisabledStyle().setBgTransparency(0);
        upButton.getDisabledStyle().setFgColor(0);
        leftButton.getDisabledStyle().setBgTransparency(0);
        leftButton.getDisabledStyle().setFgColor(0);
        spaceshipToAstronautButton.getDisabledStyle().setBgTransparency(0);
        spaceshipToAstronautButton.getDisabledStyle().setFgColor(0);
        contractDoorButton.getDisabledStyle().setBgTransparency(0);
        contractDoorButton.getDisabledStyle().setFgColor(0);
        downButton.getDisabledStyle().setBgTransparency(0);
        downButton.getDisabledStyle().setFgColor(0);
        rightButton.getDisabledStyle().setBgTransparency(0);
        rightButton.getDisabledStyle().setFgColor(0);
        spaceshipToAlienButton.getDisabledStyle().setBgTransparency(0);
        spaceshipToAlienButton.getDisabledStyle().setFgColor(0);
        rescueButton.getDisabledStyle().setBgTransparency(0);
        rescueButton.getDisabledStyle().setFgColor(0);
        healButton.getDisabledStyle().setBgTransparency(0);
        healButton.getDisabledStyle().setFgColor(0);
    }

    private void
    bindCommandsToButtons()
    {
        expandDoorButton.setCommand(expandDoorCommand);
        upButton.setCommand(moveSpaceshipUpCommand);
        leftButton.setCommand(moveSpaceshipLeftCommand);
        spaceshipToAstronautButton.setCommand(spaceshipToAstronautCommand);
        contractDoorButton.setCommand(contractDoorCommand);
        downButton.setCommand(moveSpaceshipDownCommand);
        rightButton.setCommand(moveSpaceshipRightCommand);
        spaceshipToAlienButton.setCommand(spaceshipToAlienCommand);
        rescueButton.setCommand(rescueCommand);
        healButton.setCommand(healCommand);
        gameModeButton.setCommand(gameModeCommand);
        sideMenuCheckBox.setCommand(toggleSound);
    }

    private void
    addComponentsToControlRegions()
    {
        addComponent(BorderLayout.NORTH, scoreView);
        addComponent(BorderLayout.WEST, controlWest);
        addComponent(BorderLayout.EAST, controlEast);
        addComponent(BorderLayout.SOUTH, controlSouth);
        addComponent(BorderLayout.CENTER, mapView);
    }

    private void
    addCommandsToToolbar()
    {
        toggleSound.putClientProperty("SideComponent", sideMenuCheckBox);
        toolbar.addCommandToSideMenu(toggleSound);
        toolbar.addCommandToSideMenu(exitCommand);
        toolbar.addCommandToSideMenu(rescueCommand);
        toolbar.addCommandToSideMenu(aboutCommand);
        toolbar.addCommandToRightBar(helpCommand);
    }

    private void
    styleToolbar()
    {
        toolbar.getAllStyles().setPaddingTop(40);
        toolbar.setTitle("Space Fights Game");
        toolbar.setTitleCentered(true);
    }

    private void
    setControlWest()
    {
        controlWest = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlWest.getAllStyles().setPaddingTop(100);
        controlWest.getAllStyles().setBorder(Border.createCompoundBorder(
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(0),
                                             Border.createLineBorder(1)));
        controlWest.add(expandDoorButton);
        controlWest.add(upButton);
        controlWest.add(leftButton);
        controlWest.add(spaceshipToAstronautButton);
    }

    private void
    setControlEast()
    {
        controlEast = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        controlEast.getAllStyles().setPaddingTop(100);
        controlEast.getAllStyles().setBorder(
            Border.createCompoundBorder(
                 Border.createLineBorder(0),
                 Border.createLineBorder(0),
                 Border.createLineBorder(1),
                 Border.createLineBorder(0)
            )
        );
        controlEast.add(contractDoorButton);
        controlEast.add(downButton);
        controlEast.add(rightButton);
        controlEast.add(spaceshipToAlienButton);
        controlEast.add(rescueButton);
    }

    private void
    setControlSouth()
    {
        controlSouth = new Container(new FlowLayout(Component.CENTER));
        controlSouth.getAllStyles().setBorder(
            Border.createCompoundBorder(
                 Border.createLineBorder(1),
                 Border.createLineBorder(0),
                 Border.createLineBorder(0),
                 Border.createLineBorder(0)
            )
        );
        gameModeButton.getAllStyles().setPaddingRight(20);
        gameModeButton.getAllStyles().setPaddingLeft(20);
        controlSouth.add(healButton);
        controlSouth.add(gameModeButton);
        
    }

}
