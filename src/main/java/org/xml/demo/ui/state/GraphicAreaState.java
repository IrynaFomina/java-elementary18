
package org.xml.demo.ui.state;

import lombok.Data;

/**
 *
 * @author alitvinov
 */
@Data
public class GraphicAreaState {
    
    private final boolean mousePressed;
    
    private final int startX;
    
    private final int startY;
    
    private final int currentY;
    
    private final int currentX;
    
}
