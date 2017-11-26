package com.mycompany.a4;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import java.io.InputStream;

public class
BGSound implements Runnable
{
    private static Media media;

    public
    BGSound(String fileName)
    {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
            media = MediaManager.createMedia(is, "audio/wav", this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void
    play()
    {
        media.play();
    }

    public static void
    pause()
    {
        media.pause();
    }

    public void
    run()
    {
        media.setTime(0);
        media.play();
    }
}
