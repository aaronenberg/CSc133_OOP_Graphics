package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import java.util.Observable;
import java.util.Observer;


public class
ScoreView extends Container implements Observer
{
    private Label totalScoreLabel;
    private Label astronautsRescuedLabel;
    private Label astronautsRemainingLabel;
    private Label aliensSnuckInLabel;
    private Label aliensRemainingLabel;
    private Label soundLabel;

    public
    ScoreView()
    {
        totalScoreLabel = new Label("Total Score: ");
        astronautsRescuedLabel = new Label(" Astronauts Rescued: ");
        aliensSnuckInLabel = new Label(" Aliens Snuck In: ");
        astronautsRemainingLabel = new Label(" Astronauts Remaining: ");
        aliensRemainingLabel = new Label(" Aliens Remaining: ");
        soundLabel = new Label(" Sound: ");
        this.add(totalScoreLabel);
        this.add(astronautsRescuedLabel);
        this.add(astronautsRemainingLabel);
        this.add(aliensSnuckInLabel);
        this.add(aliensRemainingLabel);
        this.add(soundLabel);
    }

    public void
    update(Observable o, Object arg)
    {
        GameWorld gw = (GameWorld) o;
        totalScoreLabel.setText(" Total Score: " + gw.getScore());
        astronautsRescuedLabel.setText(" Astronauts Rescued: " + gw.getAstronautsRescued());
        aliensSnuckInLabel.setText(" Aliens Snuck In: " + gw.getAliensSnuckIn());
        astronautsRemainingLabel.setText(" Astronauts Remaining: " + gw.getAstronautsRemaining());
        aliensRemainingLabel.setText(" Aliens Remaining: " + gw.getAliensRemaining());
        soundLabel.setText(" Sound: " + checkSoundStatus(gw.getSound()));


    }
    
    public String
    checkSoundStatus(Boolean soundOn)
    {
        if (soundOn)
            return "ON";
        else
            return "OFF";
    }

}
