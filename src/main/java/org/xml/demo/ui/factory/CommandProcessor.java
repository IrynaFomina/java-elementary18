package org.xml.demo.ui.factory;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.xml.demo.ui.figures.Circle;
import org.xml.demo.ui.figures.Figure;
import org.xml.demo.ui.figures.Line;
import org.xml.demo.ui.figures.Rectangle;
import org.xml.demo.ui.decorators.DefaultDecorator;
import org.xml.demo.ui.decorators.IDecorator;
import static org.xml.demo.ui.state.ApplicationMode.LINE;
import org.xml.demo.ui.state.ApplicationWindowState;
import org.xml.demo.ui.state.GraphicAreaState;

/**
 *
 * @author alitvinov
 */
public class CommandProcessor {
    
public static Figure drawNewFigure(ApplicationWindowState windowState, GraphicAreaState graphicState, Graphics g, boolean executeDecoration) {        
        IDecorator decorator = getDecorator(graphicState, windowState);
        Figure f = null;
        switch (windowState.getCurrentMode()) {
            case RECTANGLE: 
                f = createRectangle(graphicState);
                break;
            case LINE: 
                f = createLine(graphicState);                
                break;
            case CIRCLE: 
                f = drawCircle(graphicState);
                break;
        }        
        if (f != null) {
            f.setGs(graphicState);
            f.setWs(windowState); 
            if (executeDecoration) {
                decorator.doDecorate(f, g, graphicState, windowState);
            }
        }
        return f;
    }
    
    private static Figure createRectangle(GraphicAreaState graphicState) {
        Figure rect = new Rectangle(graphicState.getStartX(), graphicState.getStartY(), graphicState.getCurrentX(), graphicState.getCurrentY());        
        return rect;
    }
        
    private static Figure createLine(GraphicAreaState graphicState) {
        Line rect = new Line(graphicState.getStartX(), graphicState.getStartY(), graphicState.getCurrentX(), graphicState.getCurrentY());                
        return rect;
    }
    
    private static Figure drawCircle(GraphicAreaState graphicState) {
        Circle rect = new Circle(graphicState.getStartX(), graphicState.getStartY(), graphicState.getCurrentX(), graphicState.getCurrentY());        
        return rect;
    }
    
    public  static void drawExistingFigure(Figure f, Graphics g) {
        IDecorator decorator = getDecorator(f.getGs(), f.getWs());
        decorator.doDecorate(f, g, f.getGs(), f.getWs());
    }
    
    private static IDecorator getDecorator(GraphicAreaState graphicState, ApplicationWindowState windowState ) {
        IDecorator decorator = new DefaultDecorator();
        if (windowState.getCurrentMode() == LINE) {
            return new DefaultDecorator(){
                @Override
                public void doDecorate(Figure target, Graphics g, GraphicAreaState state, ApplicationWindowState ws) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(10));
                    super.doDecorate(target, g, state, ws);
                }                
            };
        }
        return decorator;
    }
}
