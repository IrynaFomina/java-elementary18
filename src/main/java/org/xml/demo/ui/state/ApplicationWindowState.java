package org.xml.demo.ui.state;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author alitvinov
 */
@Getter
@AllArgsConstructor
@ToString
public class ApplicationWindowState {
    
    public static ApplicationMode DEFAULT_MODE = ApplicationMode.RECTANGLE;
       
    protected Color currentColor;
    
    protected ApplicationMode currentMode = DEFAULT_MODE;
}
