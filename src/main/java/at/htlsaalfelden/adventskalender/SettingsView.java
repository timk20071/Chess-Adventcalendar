package at.htlsaalfelden.adventskalender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SettingsView {
    static MainwindowViewController controller;

    @FXML
    public CheckBox chbCheatMode;

    @FXML
    public void onCheatModeChange(ActionEvent event) {
        controller.cheat = chbCheatMode.isSelected();
    }

    public static void setmainwindowviewcontroller(MainwindowViewController c) {
        controller = c;
    }
}
