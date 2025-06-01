package me.peterzoltan.jgameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JGameOfLife extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JGameOfLife.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 620);
        stage.setTitle("GameOfLife");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}