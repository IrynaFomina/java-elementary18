package org.xml.demo.ui.figures;

import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import org.xml.demo.ui.state.ApplicationWindowState;
import org.xml.demo.ui.state.GraphicAreaState;

@Getter
@Setter
public abstract class Figure {
    
    protected GraphicAreaState gs;
    
    protected ApplicationWindowState ws;
    
    protected boolean isDraft;
    
    protected boolean isActive;
    
    public abstract void draw(Graphics g, boolean filled);
}

