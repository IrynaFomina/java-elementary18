package org.xml.demo.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.FileOutputStream;
import javax.swing.*;
import lombok.Builder;
import lombok.Getter;
import org.xml.demo.ui.state.ApplicationWindowState;
import org.xml.demo.ui.state.ApplicationMode;
import org.xml.demo.ui.state.IApplicationWindowStateProvider;
import static org.xml.demo.ui.state.ApplicationMode.ACTION_BUTTONS;

@Builder
@Getter
public class ApplicationWindow extends JFrame implements IApplicationWindowStateProvider {
    
    private final static ApplicationWindowState INITIAL_STATE = new ApplicationWindowState(Color.PINK, ApplicationMode.RECTANGLE);

    private String windowTitle;

    private int windowHeight;

    private int windowWidth;

    private String workingDirectory;

    private ApplicationWindowState appState;
    
    private GraphicArea graphicArea;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    public void init() {
        if (this.workingDirectory == null) {
            throw new RuntimeException("Please set working directory");
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(windowTitle);
        setSize(windowWidth, windowHeight);
        this.initWithButtons();
        graphicArea = new GraphicArea(this);
        this.add(graphicArea, BorderLayout.CENTER);
    }

    private void initWithButtons() {
        JPanel buttonPanel = new JPanel();
        for (ApplicationMode buttonInfo: ACTION_BUTTONS){
            JButton button = new JButton((String) buttonInfo.getStrRepresentation());
            button.addActionListener((buttonEvent) -> {
                ApplicationWindowState currentState = provideState();
                changeState(new ApplicationWindowState(currentState.getCurrentColor(), buttonInfo));        
            });
            buttonPanel.add(button);
        };
        
        //color chooser button
        JButton colorButton = new JButton("Color");
        colorButton.addActionListener((e) -> {
                    Color c = JColorChooser.showDialog(
                     (Component) e.getSource(),
                     "Choose Background Color",
                     provideState().getCurrentColor());
                    ApplicationWindowState currentState = provideState();
                    changeState(new ApplicationWindowState(c, currentState.getCurrentMode()));        
        });
        buttonPanel.add(colorButton);
        
        //save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((e) -> {
            try {
                mapper.writeValue(new FileOutputStream(this.workingDirectory+"\\data.text"), graphicArea.getFigures());
            } catch (Exception anyException) {
                throw new RuntimeException(anyException);
            }
        });
        buttonPanel.add(saveButton);
        
        this.add(buttonPanel, BorderLayout.PAGE_START);
        buttonPanel.setVisible(true);
    }

    @Override
    public ApplicationWindowState provideState() {        
        return appState == null ? INITIAL_STATE : appState;
    }

    @Override
    public void changeState(ApplicationWindowState newState) {        
        this.appState = newState;
    }
}
