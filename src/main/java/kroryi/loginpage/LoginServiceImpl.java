package kroryi.loginpage;

import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService {

    public  LoginServiceImpl() {
    }

    @Override
    public int LoginProc(Parent root) {
        // fxml에 있는 tfIdField등을 제어하는 방법이 크게 두가지로
        // 아래는 로딩된 root(fxml)에서 fx:id 로 lookup으로 직접 접근 하는방법
        TextField idField = (TextField) root.lookup("#tfIdField");
        TextField pwField = (TextField) root.lookup("#tfPwField");
        return MyDB.chkIdPw(idField.getText(), pwField.getText());
    }
}
