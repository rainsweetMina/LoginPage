package kroryi.loginpage.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import kroryi.loginpage.Service.CommService;
import kroryi.loginpage.Service.LoginService;
import kroryi.loginpage.Dao.Member;
import kroryi.loginpage.Dao.MyDB;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, String> tvName;
    @FXML private TableColumn<Member, String> tvId;
    @FXML private TableColumn<Member, String> tvPw;
    @FXML private TableColumn<Member, String> tvEmail;



    private Stage stage;
    private Scene scene;
    private Parent root;

    private CommService commService;
    private LoginService loginService;
    private ObservableList<Member> memberData = FXCollections.observableArrayList();

    public  void setRoot(Parent root) {
        this.root = root;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        tvName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvPw.setCellValueFactory(new PropertyValueFactory<>("pw"));
        tvEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        memberData.clear();
        memberData.addAll(MyDB.getListMember());
        tableView.setItems(memberData);

        tableView.setOnMouseClicked(this::handleRowClick);

    }


    public void handleRowClick(MouseEvent event) {
        if(event.getClickCount() == 1){
            Member selectedMember = tableView.getSelectionModel().getSelectedItem();

            if(selectedMember != null){
                System.out.println("선택값: " + selectedMember);
            }

        }
    }

}
