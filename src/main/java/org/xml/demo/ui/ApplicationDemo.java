package org.xml.demo.ui;

import java.io.IOException;
import java.util.logging.LogManager;

public class ApplicationDemo {

    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(ApplicationDemo.class.getClassLoader().getResourceAsStream("logger.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Window window = Window
                .builder()
                .windowHeight(700)
                .windowWidth(700)
                .windowTitle("Test")
                .build();
        window.init();

        window.setVisible(true);
    }
}
