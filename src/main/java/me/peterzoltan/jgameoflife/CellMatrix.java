package me.peterzoltan.jgameoflife;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.util.Random;

public class CellMatrix {

    boolean isActive = false;

    Timer timer = new Timer(100, event -> update());

    @FXML
    private GridPane cellPane;

    private final Cell[][] cellMatrix = new Cell[32][32];

    private final Button[][] buttonMatrix = new Button[32][32];

    public void initialize() {
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < 32; k++) {
                cellMatrix[i][k] = new Cell();
                Button button = getButton(i, k);
                buttonMatrix[i][k] = button;
                cellPane.add(button, i, k);
            }
        }
    }

    private Button getButton(int row, int column) {
        Button button = new Button();
        EventHandler<?super MouseEvent> onClick = event -> {
            cellMatrix[row][column].alive = !cellMatrix[row][column].alive;
            button.getOnAction().handle(new ActionEvent());
        };
        button.setOnMouseClicked(onClick);
        EventHandler<ActionEvent> onChanged = event -> button.setStyle(cellMatrix[row][column].getColor());
        button.setOnAction(onChanged);
        button.setStyle("-fx-background-radius: 0");
        button.setStyle(cellMatrix[row][column].getColor());
        button.setMinSize(16.0, 16.0);
        button.setPrefSize(button.getMinWidth(), button.getMinHeight());
        return button;
    }

    @FXML
    private void start() {
        isActive = true;
        timer.start();
    }

    @FXML
    private void stop() {
        timer.stop();
        isActive = false;
    }

    @FXML
    private void reset() {
        stop();
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < 32; k++) {
                cellMatrix[i][k].alive = false;
                buttonMatrix[i][k].getOnAction().handle(new ActionEvent());
            }
        }
    }

    @FXML
    private void randomize() {
        Random rand = new Random();
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < 32; k++) {
                cellMatrix[i][k].alive = rand.nextInt(100) < 20;
                buttonMatrix[i][k].getOnAction().handle(new ActionEvent());
            }
        }
    }

    private int aliveNeighbours(int row, int column) {
        if (row < 0 || row >= 32 || column < 0 || column >= 32) { throw new IndexOutOfBoundsException(); }
        int neighbours = 0;

        int northern = row - 1;     if (northern < 0)   northern = 31;
        int southern = row + 1;     if (southern == 32) southern = 0;
        int western = column - 1;   if (western < 0)    western = 31;
        int eastern = column + 1;   if (eastern == 32)  eastern = 0;

        if (cellMatrix[northern][column].alive)  neighbours++;
        if (cellMatrix[southern][column].alive)  neighbours++;
        if (cellMatrix[row][western].alive)      neighbours++;
        if (cellMatrix[row][eastern].alive)      neighbours++;
        if (cellMatrix[northern][western].alive) neighbours++;
        if (cellMatrix[northern][eastern].alive) neighbours++;
        if (cellMatrix[southern][western].alive) neighbours++;
        if (cellMatrix[southern][eastern].alive) neighbours++;

        return neighbours;
    }

    private void update() {
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < 32; k++) {
                int n = aliveNeighbours(i, k);
                if (cellMatrix[i][k].alive) {
                    if (n == 0 || n == 1 || n >=4) cellMatrix[i][k].nextState = false;
                    if (n == 2 || n == 3) cellMatrix[i][k].nextState = true;
                } else cellMatrix[i][k].nextState = (n == 3);
            }
        }
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < 32; k++) {
                cellMatrix[i][k].alive = cellMatrix[i][k].nextState;
                buttonMatrix[i][k].getOnAction().handle(new ActionEvent());
            }
        }
    }

}