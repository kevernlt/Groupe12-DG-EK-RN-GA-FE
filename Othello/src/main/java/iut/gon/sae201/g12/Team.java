package iut.gon.sae201.g12;

import java.awt.*;

public enum Team {
    BLACK,
    WHITE;

    private Color color;

    public Color getColor() {
        return color;
    }

    public Team other(){

        return null;
    }
}
