module at.htlsaalfelden.adventskalender {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens at.htlsaalfelden.adventskalender to javafx.fxml;
    exports at.htlsaalfelden.adventskalender;
}