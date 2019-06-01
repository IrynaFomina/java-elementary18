package org.xml.demo.ui.states;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApplicationWindowState implements Serializable {

    private final ApplicationMode mode;

    private final Color color;

    public ApplicationMode getMode() {
        return mode;
    }

    public Color getColor() {
        return color;
    }
}
