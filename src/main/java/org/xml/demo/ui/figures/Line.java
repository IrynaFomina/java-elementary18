
package org.xml.demo.ui.figures;

import java.awt.Graphics;
import lombok.Data;

/**
 *
 * @author alitvinov
 */
@Data
public class Line extends Figure{
    
    private final int x1, y1, x2, y2;
    
    @Override
    public void draw(Graphics g, boolean filled) {
        g.drawLine(x1, y1, x2, y2);
    }        
}
