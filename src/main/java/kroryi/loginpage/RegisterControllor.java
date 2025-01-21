package kroryi.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterControllor {
    @FXML private TextField tfNameField;
    @FXML private TextField tfIdField;
    @FXML private PasswordField pfPwdField;
    @FXML private PasswordField pfConfirmPwdField;
    @FXML private TextField tfEmailField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private CommService commService;




    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onRegisterBtnClick() {
        System.out.println("회원가입 버튼 실행");
        String name = tfNameField.getText();
        String id = tfIdField.getText();
        String password = pfPwdField.getText();
        String passwordRe = pfConfirmPwdField.getText();
        String email = tfEmailField.getText();

        if(name.isEmpty() || id.isEmpty() || password.isEmpty() || passwordRe.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("검증 실패!!");
            alert.setContentText("빈 값을 채워주세요.");
            alert.showAndWait();
        }


        if(!email.contains("@")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("검증 실패!!");
            alert.setContentText("메일 구성이 잘못되었습니다.");
            alert.showAndWait();
        }

        Member newMember = new Member(name, id, password, email);
        System.out.println(newMember);

        MyDB.saveMember(newMember);

//        for(Member member:myDB.getListMember()){
//            System.out.println(member.toString());
//        }

    }

    @FXML
    public void onLoginBtnClick(ActionEvent event) throws IOException  {
        System.out.println("로그인 페이지로 이동 클릭");
        commService = new CommonServiceImpl(stage);
        commService.showLoginPage("login-view.fxml");

    }

    @FXML
    public void onGoToListBtnClick(ActionEvent event)  {
        System.out.println("목록 페이지로 이동 클릭");
        commService = new CommonServiceImpl(stage);
        commService.showListPage("list-view.fxml");
    }


}
