package com.mycompany.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.plaf.Style;

public class
Button extends com.codename1.ui.Button
{
    private Style style = new Style();
    public
    Button(String text)
    {
        super(text);
        style.setBgTransparency(200);
        style.setBgColor(ColorUtil.BLUE);
        style.setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        this.getAllStyles().setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setPaddingTop(10);
        this.getAllStyles().setPaddingBottom(10);
        this.getAllStyles().setPaddingRight(5);
        this.getAllStyles().setPaddingLeft(5);
    }
}
