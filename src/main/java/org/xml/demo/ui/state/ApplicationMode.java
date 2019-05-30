package org.xml.demo.ui.state;

import lombok.Getter;

/**
 *
 * @author alitvinov
 */
@Getter
public enum ApplicationMode {        
    RECTANGLE("Rectangle"), 
    CIRCLE("Circle"),
    LINE("Line"),
    SELECT("select"),
    SETFILL("setFille");
    
    public static final ApplicationMode[] ACTION_BUTTONS = new ApplicationMode[]{
        ApplicationMode.RECTANGLE,
        ApplicationMode.CIRCLE,
        ApplicationMode.LINE,        
    };

    private String strRepresentation;
    
    ApplicationMode(String strRepresentation) {
        this.strRepresentation = strRepresentation;
    }
    
}
