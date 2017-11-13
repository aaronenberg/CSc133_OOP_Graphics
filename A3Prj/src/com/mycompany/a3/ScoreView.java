package com.mycompany.a3;


import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;


public class
ScoreView extends Container implements Observer
{
    private static Label 
        totalScoreLabel          = new Label(),
        astronautsRescuedLabel   = new Label(),
        astronautsRemainingLabel = new Label(),
        aliensSnuckInLabel       = new Label(),
        aliensRemainingLabel     = new Label(),
        totalScoreValue          = new Label(),
        astronautsRescuedValue   = new Label(),
        astronautsRemainingValue = new Label(),
        aliensSnuckInValue       = new Label(),
        aliensRemainingValue     = new Label(),
        soundLabel               = new Label(),
        soundValue               = new Label();

    private String exit = "Exit";
    private String finalScoreDialogTitle = "Winner";
    private String finalScoreDialogBody = "All astronauts have been rescued!\n"
                                          + "The spaceship flies back into "
                                          + "space.\n\nYour final score: ";

    public
    ScoreView()
    {
        setScoreViewLabelsAndValues();
        styleScoreView();
    }

    public void
    update(Observable observable, Object arg)
    {
        totalScoreValue.setText(         "" + GameWorld.getScore());
        astronautsRescuedValue.setText(  "" + GameWorld.getAstronautsRescued());
        aliensSnuckInValue.setText(      "" + GameWorld.getAliensSnuckIn());
        astronautsRemainingValue.setText("" + GameWorld.getAstronautsRemaining());
        aliensRemainingValue.setText(    "" + GameWorld.getAliensRemaining());
        soundValue.setText(              "" + checkSoundStatus(GameWorld.getSound()));

        repaint();
        checkGameOver();
    }

    public String
    checkSoundStatus(Boolean soundOn)
    {
        if (soundOn)
            return "ON";
        else
            return "OFF";
    }

    public static String
    getSoundText()
    {
        return soundLabel.getText() + soundValue.getText();
    }

    private void
    setScoreViewLabelsAndValues()
    {
        totalScoreValue.getAllStyles().setMarginRight(20);
        astronautsRescuedValue.getAllStyles().setMarginRight(20);
        astronautsRemainingValue.getAllStyles().setMarginRight(20);
        aliensSnuckInValue.getAllStyles().setMarginRight(20);
        aliensRemainingValue.getAllStyles().setMarginRight(20);

        add(totalScoreLabel);
        add(totalScoreValue);
        add(astronautsRescuedLabel);
        add(astronautsRescuedValue);
        add(astronautsRemainingLabel);
        add(astronautsRemainingValue);
        add(aliensSnuckInLabel);
        add(aliensSnuckInValue);
        add(aliensRemainingLabel);
        add(aliensRemainingValue);
        add(soundLabel);
        add(soundValue);

        totalScoreLabel.setText("Total Score:");
        astronautsRescuedLabel.setText("Astronauts Rescued:");
        aliensSnuckInLabel.setText("Aliens Snuck In:");
        astronautsRemainingLabel.setText("Astronauts Remaining:");
        aliensRemainingLabel.setText("Aliens Remaining:");
        soundLabel.setText("Sound:");
    }

    private void
    styleScoreView()
    {
        getAllStyles().setPaddingTop(10);
        getAllStyles().setBorder(Border.createCompoundBorder(
                                 Border.createLineBorder(1),
                                 Border.createLineBorder(1),
                                 Border.createLineBorder(0),
                                 Border.createLineBorder(0)));
    }

    private void
    checkGameOver()
    {
        if (GameWorld.getAstronautsRemaining() == 0) {
            Game.getUITimer().cancel();
            Dialog.show(
                finalScoreDialogTitle,
                finalScoreDialogBody + GameWorld.getScore(),
                exit, null
             );
             Display.getInstance().exitApplication();
        }
    }

}
