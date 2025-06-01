package me.peterzoltan.jgameoflife;

import javafx.scene.paint.Color;

public class Cell {

    boolean alive = false;

    boolean nextState = false;

    public String getColor() {
        if (alive) { return "-fx-background-color: #642BD7"; }
        return "-fx-background-color: #d9d4d4";
    }
}
