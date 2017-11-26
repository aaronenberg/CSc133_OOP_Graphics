package com.mycompany.a4;


import com.codename1.charts.util.ColorUtil;

public class
Button extends com.codename1.ui.Button
{
    public
    Button()
    {
        super();
        getAllStyles().setAlignment(CENTER);
        getAllStyles().setBgColor(ColorUtil.LTGRAY);
        getAllStyles().setFgColor(ColorUtil.WHITE);
        getAllStyles().setOpacity(250);
        getAllStyles().setPaddingTop(40);
        getAllStyles().setPaddingBottom(40);
        getAllStyles().setPaddingRight(5);
        getAllStyles().setPaddingLeft(5);
    }
}
