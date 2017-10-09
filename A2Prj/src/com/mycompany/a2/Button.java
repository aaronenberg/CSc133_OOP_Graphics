package com.mycompany.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;

public class
Button extends com.codename1.ui.Button
{
    public
    Button()
    {
        super();
        this.getAllStyles().setAlignment(CENTER);
        this.getAllStyles().setFont(
                Font.createSystemFont(
                        Font.FACE_PROPORTIONAL,
                        Font.STYLE_PLAIN,
                        Font.SIZE_LARGE
                )
        );
        this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        this.getAllStyles().setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setOpacity(250);
        this.getAllStyles().setPaddingTop(40);
        this.getAllStyles().setPaddingBottom(40);
        this.getAllStyles().setPaddingRight(5);
        this.getAllStyles().setPaddingLeft(5);
    }
}
