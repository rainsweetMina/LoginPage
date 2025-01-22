package kroryi.loginpage;

import kroryi.loginpage.Service.CommService;
import kroryi.loginpage.Service.CommonServiceImpl;
import javafx.stage.Stage;
import kroryi.loginpage.Dao.MyDB;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        MyDB myDB = new MyDB(); // 단일 인스턴스 사용


        try {
//            CommService commService = new CommonServiceImpl(stage);
//            commService.showExamplePage("example.fxml");
//            commService.showLoginPage("login-view.fxml");
//            commService.showListPage("list-view.fxml");
            SceneManager.setPrimaryState(stage);
            SceneManager.switchScene("login-view.fxml","샘플");


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}