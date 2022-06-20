package br.pucpr.jge;

import java.awt.*;
import java.io.IOException;

public interface Steps {
    void load();
    boolean update(double s, InputManager keys);
    void draw(Graphics2D g2d) throws FontFormatException, IOException;
    void unload();
    void draw2(Graphics2D g2d) throws FontFormatException, IOException;
}
