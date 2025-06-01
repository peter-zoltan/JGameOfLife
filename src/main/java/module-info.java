module me.peterzoltan.jgameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens me.peterzoltan.jgameoflife to javafx.fxml;
    exports me.peterzoltan.jgameoflife;
}