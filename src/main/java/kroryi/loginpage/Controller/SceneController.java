package kroryi.loginpage.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene(ActionEvent event, String fxlfileName) throws IOException {
       try {
           Parent root = FXMLLoader.load(getClass().getResource(fxlfileName));
           stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }catch(IOException e) {
           e.printStackTrace();
       }
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        switchScene(event, "login-view.fxml");
    }

    public void switchToRegister(ActionEvent event) throws IOException {
        switchScene(event, "register-view.fxml");
    }




//    public void switchToLogin(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
//        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToRegister(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
//        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }


}
