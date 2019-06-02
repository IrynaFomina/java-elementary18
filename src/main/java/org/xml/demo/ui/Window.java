package org.xml.demo.ui;

import lombok.Builder;
import lombok.Getter;
import org.xml.demo.ui.states.ApplicationMode;
import org.xml.demo.ui.states.ApplicationWindowState;
import org.xml.demo.ui.states.IApplicationWindowStateManager;
import org.xml.demo.ui.states.Memento;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

@Builder
@Getter
public class Window extends JFrame implements IApplicationWindowStateManager {
    private static Logger log= Logger.getLogger(Window.class.getName());

    private static ApplicationWindowState INITIAL_STATE = new ApplicationWindowState(
            ApplicationMode.DRAW_RECTANGLE,
            Color.BLUE);

    private String windowTitle;

    private int windowHeight;

    private int windowWidth;

    private String workingDirectory;

    private GraphicArea graphicArea;

    private ApplicationWindowState applicationState;

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(windowTitle);
        setSize(windowWidth, windowHeight);
        graphicArea = new GraphicArea();
        graphicArea.setManager(this);
        initWithButtons();
        add(graphicArea, BorderLayout.CENTER);
        applicationState = getInitialState();
    }

    private ApplicationWindowState getInitialState() {
        File file = new File(Constance.WORKSPASE_FILE_NAME);
        if (file.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                log.info("State has been loaded from " + file.getName());
                return (ApplicationWindowState) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File was not found");
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        log.info("State has been initialised with default settings");
        return new ApplicationWindowState(ApplicationMode.DRAW_RECTANGLE, Color.BLUE);
    }

    private void initWithButtons() {
        JPanel buttonPanel = new JPanel();
        HashMap<String, ApplicationMode> buttonsMap = new LinkedHashMap<>();
        buttonsMap.put("Rectangle", ApplicationMode.DRAW_RECTANGLE);
        buttonsMap.put("Circle", ApplicationMode.DRAW_CIRCLE);
        buttonsMap.put("Line", ApplicationMode.DRAW_LINE);
        buttonsMap.put("Triangle", ApplicationMode.DRAW_TRIANGLE);

        addModeButtonGroup(buttonsMap,buttonPanel);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> new Memento().saveApplicationWindowState(applicationState));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.PAGE_START);

    }

    private void addModeButtonGroup(HashMap<String, ApplicationMode> map, JPanel parentPanel) {
        ButtonGroup buttonGroup = new ButtonGroup();
        map.forEach((key, value) -> {
            JToggleButton button = (JToggleButton) createModeButton(key, value);
            parentPanel.add(button);
            buttonGroup.add(button);
        });
    }

    private AbstractButton createModeButton(String label, ApplicationMode mode) {
        JToggleButton button = new JToggleButton(label);
        button.addActionListener(e -> changeState(new ApplicationWindowState(mode, provideState().getColor())));
        return button;
    }

    @Override
    public ApplicationWindowState provideState() {
        return applicationState;
    }

    @Override
    public void changeState(ApplicationWindowState state) {
        applicationState = state;
    }
}
