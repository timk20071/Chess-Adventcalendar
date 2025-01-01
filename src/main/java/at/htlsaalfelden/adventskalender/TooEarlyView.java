package at.htlsaalfelden.adventskalender;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TooEarlyView {
    @FXML
    Button button;
    public void onClick(){
        ((Stage) button.getScene().getWindow()).close();
    }
}
