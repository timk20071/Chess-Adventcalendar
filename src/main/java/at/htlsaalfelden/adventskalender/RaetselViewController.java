package at.htlsaalfelden.adventskalender;

import com.sun.tools.javac.Main;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class RaetselViewController {
    MainwindowViewController mainWindowViewController;
    String id;
    ArrayList<Image> imageList = new ArrayList<>();
    ArrayList<String[]> solution = new ArrayList<>();

    int imageIndex = 0;
    int misses = 0; // Danke Jonas
    @FXML
    private Button btnOk;
    @FXML
    private ImageView ivImage;
    @FXML
    private Label lblFalse;
    @FXML
    private TextField tfInput;
    @FXML
    private Button tip;


    @FXML
    void acceptInput(ActionEvent event) throws InterruptedException {
        if (tfInput.getText().equals(solution.get(Integer.parseInt(id)-1)[imageIndex])) {
            lblFalse.setVisible(false);
            if (imageIndex != (imageList.size()-1)) {
                imageIndex++;
                ivImage.setImage(imageList.get(imageIndex));
                tip.setVisible(false);
                misses = 0;
            }
            else {
                misses = 0;
                tip.setVisible(false);
                lblFalse.setVisible(true);
                lblFalse.setText("You won!"); // Du hast Gewonnen!
                lblFalse.setTextFill(Color.color(0, 0, 0));
                ivImage.setImage(new Image(MainwindowViewController.class.getResourceAsStream("Images/Spielfeld/Spielfeld-" + id + "-Solved.png")));
                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        close();
                    }
                });
                delay.play();
            }
        }

        else {
            lblFalse.setVisible(true);
            lblFalse.setText("Wrong solution!"); // Falsche Eingabe!
            lblFalse.setTextFill(Color.color(1, 0, 0));
            misses++;
        }
        if(misses >= 3) {
            tip.setText("Tip?");
            tip.setVisible(true);
        }
    }
    public void close(){
        Stage c = (Stage) tip.getScene().getWindow();
        c.close();
    }
    public void onTip(){
        ivImage.setImage(new Image(MainwindowViewController.class.getResourceAsStream("Images/Spielfeld/Spielfeld-" + id + "-" + (imageIndex+1) + "-Tipp.png")));
    }

    public void setVariables(MainwindowViewController mainwindowviewcontroller, String id) {
        this.mainWindowViewController = mainwindowviewcontroller;
        this.id = id;
        initImage();
        initSolution();
    }
    public void initImage(){
        lblFalse.setVisible(false);
        tip.setVisible(false);
        int i = 1;
        while(true){
            try{
                imageList.add(new Image(MainwindowViewController.class.getResourceAsStream("Images/Spielfeld/Spielfeld-" + id + "-" + i + ".png")));
            }
            catch(Exception e){break;}
            ivImage.setImage(imageList.get(imageIndex));
            i++;
        }
    }

    public void initSolution(){
        solution.clear();
        solution.add(new String[]{"e6xe5"});//1 Black to play
        solution.add(new String[]{"b6xb5", "b5xe5"});//2 Black To Play
        solution.add(new String[]{"c1xb2", "d2xc4"});//3
        solution.add(new String[]{"d2xh6", "e5xg7"});//4
        solution.add(new String[]{"g2xg7", "g7xe7"});//5
        solution.add(new String[]{"h2xf2", "g4xe3", "f2xc2"});//6
        solution.add(new String[]{"c1xa3"});//7
        solution.add(new String[]{"c5xf2"});//8
        solution.add(new String[]{"d1xd7"});//9
        solution.add(new String[]{"b7xe7"});//10
        solution.add(new String[]{"d6xb4", "b4xc3"});//11
        solution.add(new String[]{"g5xh4", "h4xf2"});//12
        solution.add(new String[]{"a4xb5"});//13
        solution.add(new String[]{"a3xd3"});//14
        solution.add(new String[]{"g2xg1"});//15
        solution.add(new String[]{"a7xf2"});//16
        solution.add(new String[]{"c1xa3"});//17
        solution.add(new String[]{"c2xd2", "d2xd1"});//18
        solution.add(new String[]{"h4xf2"});//19
        solution.add(new String[]{"c7xg3"});//20
        solution.add(new String[]{"e4xf4"});//21
        solution.add(new String[]{"c6xd4"});//22
        solution.add(new String[]{"e3xe2", "d4xf2"});//23
        solution.add(new String[]{"d4xe6", "g5xg8", "g3xg8"});//24
    }
}