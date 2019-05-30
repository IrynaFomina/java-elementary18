package org.xml.demo.ui.decorators;

import org.xml.demo.ui.figures.Figure;

import java.awt.*;
import org.xml.demo.ui.state.ApplicationWindowState;
import org.xml.demo.ui.state.GraphicAreaState;

public class DefaultDecorator implements IDecorator {

    @Override
    public void doDecorate(Figure target, Graphics g, GraphicAreaState state, ApplicationWindowState ws) {        
        Graphics2D g2 = (Graphics2D) g;
        if (state.isMousePressed()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        g.setColor(ws.getCurrentColor());
        target.draw(g, true);
    }
}
