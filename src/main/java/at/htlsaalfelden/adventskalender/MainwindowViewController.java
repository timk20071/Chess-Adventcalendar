package at.htlsaalfelden.adventskalender;

import com.sun.tools.javac.Main;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class MainwindowViewController {
    public ArrayList<ImageView> tuerchen = new ArrayList<ImageView>();
    public static boolean cheat = false;
    @FXML
    AnchorPane AnchorPane;
    int y_multiplicator;
    int x_spacer;
    public void initialize(){
        Integer[] intArray = randomPlacement();
        for (int i = 1; i <= 24; i++) {
            if (i == 1 || i == 7 || i == 13 || i == 19) {
                x_spacer = 1;
            }
            String path = "Images/Tuerchen/Tuer-" + (intArray[i-1]+1) + ".png";
            ImageView imageView = new ImageView(new Image(MainwindowViewController.class.getResourceAsStream(path)));
            imageView.setId("" + (intArray[i-1]+1));
            imageView.setId((intArray[i-1]+1)+"");
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>()  {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    Date date  = new Date(System.currentTimeMillis());
                    if(((int) date.getDate()) >= Integer.parseInt(imageView.getId()) || cheat){

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("raetsel-view.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        RaetselViewController controller = loader.getController();
                        controller.setVariables(MainwindowViewController.this, imageView.getId());

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Puzzle of the day"); // Rätsel des Tages
                        stage.getIcons().add(new Image(MainwindowViewController.class.getResourceAsStream("/at/htlsaalfelden/adventskalender/icon.png")));

                        //Image icon = new Image(HelloApplication.class.getResourceAsStream("icon.png"));
                        //stage.getIcons().add(icon);
                        stage.show();
                        AnchorPane.getChildren().remove(imageView);
                    }
                    else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("tooearly-view.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.getIcons().add(new Image(MainwindowViewController.class.getResourceAsStream("/at/htlsaalfelden/adventskalender/icon.png")));
                        stage.setTitle("");
                        //Image icon = new Image(HelloApplication.class.getResourceAsStream("icon.png"));
                        //stage.getIcons().add(icon);
                        stage.show();
                    }

                }
            });
            imageView.fitWidthProperty().setValue(200);
            imageView.fitHeightProperty().setValue(200);
            imageView.setX((200 * (x_spacer - 1) + 102*x_spacer));
            y_multiplicator = 0;
            switch (i){
                case 1,2,3,4,5,6: y_multiplicator = 1; break;
                case 7,8,9,10,11,12: y_multiplicator = 2; break;
                case 13,14,15,16,17,18: y_multiplicator = 3; break;
                case 19,20,21,22,23,24: y_multiplicator = 4; break;

            }
            imageView.setY(200 * (y_multiplicator - 1) + (40 * y_multiplicator));
            tuerchen.add(imageView);
            AnchorPane.getChildren().add(tuerchen.get(i-1));
            x_spacer++;
        }
    }

    public void openSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
        Parent root;
        root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(MainwindowViewController.class.getResourceAsStream("/at/htlsaalfelden/adventskalender/icon.png")));
        SettingsView.setmainwindowviewcontroller(MainwindowViewController.this);
        stage.setTitle("Settings");
        //Image icon = new Image(HelloApplication.class.getResourceAsStream("icon.png"));
        //stage.getIcons().add(icon);
        stage.show();
    }

    public Integer[] randomPlacement(){
        Integer[] intArr = new Integer[24];
        for (int i = 0; i < 24; i++) {
            intArr[i] = i;
        }
        List<Integer> intList = Arrays.asList(intArr);
        Collections.shuffle(intList);
        intList.toArray(intArr);
        return intArr;
    }
    public static void onClicked(){
        System.out.println("Hello");
    }
}