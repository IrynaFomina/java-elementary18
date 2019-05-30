
package org.xml.demo.ui.state;

/**
 *
 * @author alitvinov
 */
public interface IApplicationWindowStateProvider {
    
    ApplicationWindowState provideState();
    
    void changeState(ApplicationWindowState newState);
    
}
