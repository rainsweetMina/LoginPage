module kroryi.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens kroryi.loginpage to javafx.fxml;
    exports kroryi.loginpage;
}