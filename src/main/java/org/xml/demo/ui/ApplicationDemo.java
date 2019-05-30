package org.xml.demo.ui;


import java.awt.BorderLayout;

public class ApplicationDemo {
    public static void main(String[] args) {
        ApplicationWindow window = ApplicationWindow
                .builder()
                .windowHeight(700)
                .windowWidth(700)
                .windowTitle("Test")
                .workingDirectory("D:\\gui")
                .build();                
        window.init();                     
        
        window.setVisible(true);        
    }
}
