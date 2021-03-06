package com.mycompany.a2;


import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;


public class
ScoreView extends Container implements Observer
{
    private Label totalScoreLabel          = new Label(),
                  astronautsRescuedLabel   = new Label(),
                  astronautsRemainingLabel = new Label(),
                  aliensSnuckInLabel       = new Label(),
                  aliensRemainingLabel     = new Label(),
                  totalScoreValue          = new Label(),
                  astronautsRescuedValue   = new Label(),
                  astronautsRemainingValue = new Label(),
                  aliensSnuckInValue       = new Label(),
                  aliensRemainingValue     = new Label();
    private static Label soundLabel        = new Label(),
                         soundValue        = new Label();

    public
    ScoreView()
    {
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

        getAllStyles().setPaddingTop(10);
        getAllStyles().setBorder(Border.createCompoundBorder(
                                 Border.createLineBorder(1),
                                 Border.createLineBorder(1),
                                 Border.createLineBorder(0),
                                 Border.createLineBorder(0)));

        totalScoreLabel.setText("Total Score:");
        astronautsRescuedLabel.setText("Astronauts Rescued:");
        aliensSnuckInLabel.setText("Aliens Snuck In:");
        astronautsRemainingLabel.setText("Astronauts Remaining:");
        aliensRemainingLabel.setText("Aliens Remaining:");
        soundLabel.setText("Sound:");
    }

    public void
    update(Observable observable, Object arg)
    {
        GameWorld gw = (GameWorld) observable;

        totalScoreValue.setText(         " " + gw.getScore());
        astronautsRescuedValue.setText(  " " + gw.getAstronautsRescued());
        aliensSnuckInValue.setText(      " " + gw.getAliensSnuckIn());
        astronautsRemainingValue.setText(" " + gw.getAstronautsRemaining());
        aliensRemainingValue.setText(    " " + gw.getAliensRemaining());
        soundValue.setText(              " " + checkSoundStatus(gw.getSound()));

        this.repaint();

        if (gw.getAstronautsRemaining() == 0) {
            System.out.println("All astronauts have been rescued! " +
                               "The spaceship flies back into space.\n");
            System.out.println("Your final score: " + gw.getScore());
            System.out.println("\n''''''''''''''''''''''''''''''''''''''" +
                               "''''''''''''''''''''''''''''''''''''''\n");
            Display.getInstance().exitApplication();
        }
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

}
