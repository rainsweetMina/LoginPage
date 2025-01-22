package kroryi.loginpage.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.scene.input.MouseEvent;
import kroryi.loginpage.Service.CommService;
import kroryi.loginpage.Service.LoginService;
import kroryi.loginpage.Dao.Member;
import kroryi.loginpage.Dao.MyDB;
import javafx.scene.Node;


//import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, String> colName;
    @FXML private TableColumn<Member, String> colId;
    @FXML private TableColumn<Member, String> colPw;
    @FXML private TableColumn<Member, String> colEmail;
    @FXML private TableColumn<Member, Void> colCheckBox;

    @FXML private TextField tfName;
    @FXML private TextField tfId;
    @FXML private TextField pfPw;
    @FXML private TextField tfEmail;
    @FXML private CheckBox chkCheckBox;

    @FXML private Pagination pagination;

    private int totalCount = 0;
    private int pageSize = 10;
    private int pageIndex = 1;
    private int totalPage;

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
        totalCount = MyDB.selectTatalCount();
        totalCount = totalCount == 0 ? 1 : totalCount;
        totalPage = (totalCount + pageSize -1 ) / pageSize;
        pagination.setPageCount(totalPage);
        pagination.setMaxPageIndicatorCount(pageSize);
        pagination.setPageFactory(new Callback<Integer, Node>(){
            @Override
            public Node call(Integer integer) {
                System.out.println("클릭된 페이지:" + integer);
                MyDB.getObservableListMemberPage(integer);
                refreshTableView();

                return new Label(String.format("현재 페이지 :%d", pagination.getCurrentPageIndex()+1) );
            }
        });

        tableView.setEditable(true);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPw.setCellValueFactory(new PropertyValueFactory<>("pw"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colId.setCellFactory(TextFieldTableCell.forTableColumn());
        colPw.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());


        colCheckBox.setCellFactory(getCheckBoxCellFactory());




        colName.setOnEditCommit(event -> {
            Member member = event.getRowValue();
            member.setName(event.getNewValue());
            updateMemberDB(member);
        });


        colId.setOnEditCommit(event -> {
            Member member = event.getRowValue();
            member.setName(event.getNewValue());
            updateMemberDB(member);

        });


        colPw.setOnEditCommit(event -> {

            Member member = event.getRowValue();
            member.setName(event.getNewValue());
            updateMemberDB(member);
        });


        colEmail.setOnEditCommit(event -> {

            Member member = event.getRowValue();
            member.setName(event.getNewValue());
            updateMemberDB(member);
        });


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
        System.out.println(MyDB.getListMember().toString());
    }

    private void updateMemberDB(Member member){

        MyDB.updateMember(member);

    }

    @FXML
    public void memberAdd(ActionEvent event) {
        System.out.println("memberAdd 실행");
        String name = tfName.getText();
        String id = tfId.getText();
        String pw = pfPw.getText();
        String email = tfEmail.getText();
        if(name.isEmpty() || id.isEmpty() || pw.isEmpty() || email.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("빠진 정보가 있네요.. 입력 필요");
            alert.showAndWait();
        }else {
//            Member newMember = new Member(name, id, pw, email);
//            MyDB.saveMember(newMember);
//
//            // 기존 데이터에 새로운 멤버만 추가
//            memberData.add(newMember);
//            System.out.println("추가된 멤버: " + newMember);
//            clearInputFields();

            MyDB.saveMember(new Member(name, id, pw, email));
            tfName.clear();
            tfId.clear();
            pfPw.clear();
            tfEmail.clear();

        }

        refreshTableView();
    }

//    private void clearInputFields() {
//        tfName.clear();
//        tfId.clear();
//        pfPw.clear();
//        tfEmail.clear();
//    }

    @FXML
    public void refreshTableView(){
        memberData.clear();
//        memberData = MyDB.getObservableListMember();
        memberData = MyDB.getObservableListMemberPage(pagination.getCurrentPageIndex());
        tableView.setItems(memberData);

    }

    public Callback<TableColumn<Member, Void>, TableCell<Member, Void>> getCheckBoxCellFactory() {
        return column -> new TableCell<>() {
            private final Button checkBox = new Button("삭제");


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(checkBox);
                    checkBox.setOnAction(event -> {
                        Member member = getTableView().getItems().get(getIndex());
                        MyDB.deleteMember(member);
                        refreshTableView();
//                        System.out.println("CheckBox clicked for: " + member);
                    });
                }
            }
        };
    }

    public void pageListAll(int pageIndex){

    }

}
