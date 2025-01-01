package at.htlsaalfelden.adventskalender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainwindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hauptfenster");
        stage.setScene(scene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        AudioController audioController = new AudioController();
        audioController.play();
        stage.getIcons().add(new Image(MainwindowViewController.class.getResourceAsStream("/at/htlsaalfelden/adventskalender/icon.png")));
        stage.setX(-7);
        stage.setY(0);
        stage.setWidth(bounds.getWidth() + 15);
        stage.setHeight(bounds.getHeight()+7);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}