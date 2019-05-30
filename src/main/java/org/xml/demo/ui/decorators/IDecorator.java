package org.xml.demo.ui.decorators;

import org.xml.demo.ui.figures.Figure;

import java.awt.*;
import org.xml.demo.ui.state.ApplicationWindowState;
import org.xml.demo.ui.state.GraphicAreaState;

public interface IDecorator {

    public void doDecorate(Figure target, Graphics g, GraphicAreaState gs, ApplicationWindowState ws);

}
