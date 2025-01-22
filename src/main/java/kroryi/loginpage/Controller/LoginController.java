package kroryi.loginpage.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kroryi.loginpage.SceneManager;
import kroryi.loginpage.Service.CommService;
import kroryi.loginpage.Service.CommonServiceImpl;
import kroryi.loginpage.Service.LoginService;
import kroryi.loginpage.Service.LoginServiceImpl;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField tfIdField;

    @FXML
    private TextField tfPassField;

    @FXML
    private Button btnLogin;

    @FXML
    private Button resignBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private LoginService loginService;

    public  void setRoot(Parent root) {
        this.root = root;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    protected void onLoginBtnClick() {
        System.out.println("로그인 버튼 실행");
        loginService = new LoginServiceImpl();

        switch (loginService.LoginProc(root)){
            case 0:
                System.out.println("id가 없는 경우");
                break;
            case 1:
                System.out.println("아이디는 맞다.");
                break;

            case 2:
                System.out.println("id, pw가 맞다.");
                break;

        }

    }

    @FXML
    protected void onResignBtnClick(ActionEvent event) throws IOException {
        System.out.println("회원가입 버튼 실행");
//        SceneManager.setPrimaryState(stage);
        SceneManager.switchScene("register-view.fxml","회원가입 화면");

    }

}