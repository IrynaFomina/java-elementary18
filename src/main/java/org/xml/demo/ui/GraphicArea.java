package org.xml.demo.ui;

import org.xml.demo.ui.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import org.xml.demo.ui.factory.CommandProcessor;
import org.xml.demo.ui.state.GraphicAreaState;
import org.xml.demo.ui.state.IApplicationWindowStateProvider;

public class GraphicArea extends JComponent {

    private int gridStep = 10;

    private boolean isMousePressed = false;

    private int startX, startY, currentX, currentY;
    
    @Getter
    private List<Figure> figures = new LinkedList<>();

    private IApplicationWindowStateProvider stateProvider;

    public GraphicArea(IApplicationWindowStateProvider stateProvider) {
        //add mouse listeners to draw on graphic panel
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isMousePressed = true;
                currentX = startX = e.getX();
                currentY = startY = e.getY();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMousePressed = false;
                figures.add(CommandProcessor.drawNewFigure(stateProvider.provideState(), getGraphicAreaState(), null, false));
                figures.forEach(f -> f.setDraft(false));
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                repaint();
            }
        });
        this.stateProvider = stateProvider;
    }

    @Override
    public void paint(Graphics g) {
        drawGrid(g);
        for (Figure f : figures) {
            CommandProcessor.drawExistingFigure(f, g);
        }
        if (isMousePressed) {
            CommandProcessor.drawNewFigure(stateProvider.provideState(), getGraphicAreaState(), g, true);
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < getHeight(); i += gridStep) {
            g.drawLine(0, i, getWidth(), i);
        }
        for (int i = 0; i < getWidth(); i += gridStep) {
            g.drawLine(i, 0, i, getHeight());
        }
    }

    private GraphicAreaState getGraphicAreaState() {
        return new GraphicAreaState(isMousePressed, startX, startY, currentY, currentX);
    }
}
