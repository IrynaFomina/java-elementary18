package org.xml.demo.ui.figures;

import java.awt.*;

public class Rectangle extends Figure {

    private final int startX;

    private final int startY;

    private final int width;

    private final int height;

    public Rectangle(int startX, int startY, int currentX, int currentY) {
        this.startX = Integer.min(startX, currentX);
        this.startY = Integer.min(startY, currentY);
        this.width = Math.abs(startX - currentX);
        this.height = Math.abs(startY - currentY);
    }

    @Override
    public void draw(Graphics g, boolean filled) {
        if (filled) {
            g.fillRect(startX, startY, width, height);
        } else {
            g.drawRect(startX, startY, width, height);
        }
    }
}
