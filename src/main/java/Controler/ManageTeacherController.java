package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.TeacherBO;
import dto.ExamDto;
import dto.TM.CourseTM;
import dto.TM.TeacherTM;
import dto.TeacherManageDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageTeacherController implements Initializable {

    @FXML
    public Button btnBAcks;

    @FXML
    public Button btnSearch;

    @FXML
    public Button btnUpdate;

    @FXML
    public Button btnDelete;

    @FXML
    public TextField txtId;

    @FXML
    public TextField txtName;

    @FXML
    public TextField txtAdress;

    @FXML
    public TextField txtMobile;

    @FXML
    public Button btnAdd;

    @FXML
    public TextField txtMAil;

    @FXML
    public Button btnClaer;

    @FXML
    public Label print;

    @FXML
    private TableView<TeacherTM> tblTeacherView;

    @FXML
    private TableColumn<TeacherTM,String> tblAddress;

    @FXML
    private TableColumn<TeacherTM, String> tblEmail;

    @FXML
    private TableColumn<TeacherTM, Integer> tblMobile;

    @FXML
    private TableColumn<TeacherTM, String> tblName;

    @FXML
    private TableColumn<TeacherTM, Integer> tblTeacherId;


    TeacherBO teacherBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Teacher_BO);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        tblTeacherId.setCellValueFactory(new PropertyValueFactory<TeacherTM,Integer>("TId"));
        tblName.setCellValueFactory(new PropertyValueFactory<TeacherTM,String>("TName"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<TeacherTM,String>("TAddress"));
        tblMobile.setCellValueFactory(new PropertyValueFactory<TeacherTM,Integer>("TContact"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<TeacherTM,String>("TEmail"));

    }

    private void getAll() {
        try {
//            ObservableList<TeacherTM> obList = FXCollections.observableArrayList();
//            List<TeacherManageDto> cusList = teacherManageModel.getAll();

             ArrayList<TeacherManageDto> cusList = teacherBO.getAllTeacher();


            for (TeacherManageDto teacherManage : cusList) {
                tblTeacherView.getItems().add(new TeacherTM(
                        teacherManage.getTId(),
                        teacherManage.getTName(),
                        teacherManage.getTAddress(),
                        teacherManage.getTContact(),
                        teacherManage.getTEmail()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

        Integer TId = Integer.valueOf(txtId.getText());
        try {
            boolean b = teacherBO.deleteTeacher(TId);
            tblTeacherView.getItems().remove(tblTeacherView.getSelectionModel().getSelectedItem());
            tblTeacherView.getSelectionModel().clearSelection();

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                getAll();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    public void btnUpdateAction(ActionEvent actionEvent) {


        int TId = Integer.parseInt(txtId.getText());
        String TName = txtName.getText();
        String TNddress = txtAdress.getText();
        int TContcat = Integer.parseInt(txtMobile.getText());
        String TEmail = txtMAil.getText();


        try {
            boolean isUpdated = teacherBO.updateTeacher(new TeacherManageDto(TId,TName, TNddress, TContcat,TEmail));

            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    public void btnSeartchAction(ActionEvent actionEvent) {

        try {

            TeacherManageDto dto = teacherBO.searchTeacher(Integer.valueOf(txtId.getText()));

            if (dto != null) {
                txtId.setText(String.valueOf(dto.getTId()));
                txtName.setText(dto.getTName());
                txtAdress.setText(String.valueOf(dto.getTAddress()));
                txtMobile.setText(String.valueOf(dto.getTContact()));
                txtMAil.setText(String.valueOf(dto.getTEmail()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    public void btnBAckAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btnBAcks.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

   @FXML
    public void btnAddAction(ActionEvent actionEvent) {

       int TId = Integer.parseInt(txtId.getText());
       String TName = txtName.getText();
       String TNddress = txtAdress.getText();
       int TContact = 0;
       String TEmail = txtMAil.getText();


       if (!txtMobile.getText().matches("^(070|072|074|075|076|077|078)[0-9]{7}$")) {
           print.setText("This Phone Number Is Wrong ");
           return;

       }else {
           TContact = Integer.parseInt(txtMobile.getText());
           print.setText("");
       }



       try {
           boolean b =teacherBO.saveTeacher(new TeacherManageDto(TId,TName,TNddress,TContact,TEmail));
           tblTeacherView.getItems().add(new TeacherTM(TId,TName,TNddress,TContact,TEmail));

           if (b) {
               new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
           }
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
       }
       getAll();
    }



    public void btnClearchAction(ActionEvent actionEvent) {

        txtId.setText("");
        txtName.setText("");
        txtAdress.setText("");
        txtMAil.setText("");
        txtMobile.setText("");
    }

    public void tblOnAction(MouseEvent mouseEvent) {

        TableView.TableViewFocusModel<TeacherTM> model =  tblTeacherView.getFocusModel();
        TableView.TableViewSelectionModel<TeacherTM> i = tblTeacherView.getSelectionModel();

        try{
            txtId.setText(String.valueOf(model.getFocusedItem().getTId()));
            txtName.setText(String.valueOf(model.getFocusedItem().getTName()));
            txtAdress.setText(model.getFocusedItem().getTAddress());
            txtMobile.setText(String.valueOf(model.getFocusedItem().getTContact()));
            txtMAil.setText(String.valueOf(model.getFocusedItem().getTEmail()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
