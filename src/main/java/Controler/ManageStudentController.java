package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.StudentBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import db.DBConnection;
import dto.CourseDto;
import dto.CourseDetailDto;
import dto.ExamDto;
import dto.StudentManageDto;
import dto.TM.StudentTM;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStudentController implements Initializable {

    public AnchorPane root2;
    @FXML
    public Button btnAdd;
    @FXML
    public Button back1;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnseartch;

    @FXML
    public Button btnUpdate;

    @FXML
    public TextField txtId;

    @FXML
    public TextField txtName;

    @FXML
    public TextField txtAddress;

    @FXML
    public TextField txtMobile;

    @FXML
    public TextField txtEmail;
    public Label Print;

    @FXML
    public TextField txtPayment;
    public ComboBox cmbCourses;
    public Label lblCorse;

    @FXML
    private TableColumn<StudentTM, String> Payemnt;

    @FXML
    private Button btnClaer;


    @FXML
    private TableView<StudentTM> tblStud;

    @FXML
    private TableColumn<StudentTM,String> SAddress;

    @FXML
    private TableColumn<StudentTM,Integer > SContact;

    @FXML
    private TableColumn<StudentTM, Integer> SId;

    @FXML
    private TableColumn<StudentTM, String> SMail;


    @FXML
    private TableColumn<StudentTM, String> sName;


    StudentBO studentBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Student_BO);
    CourseBO courseBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Course_BO);

   @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        try {
            List<CourseDto> all = courseBO.getAllCourse();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (CourseDto course : all) {
                list.add(course.getCoure_Name());
            }
            cmbCourses.setItems(list);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
   }



    private void setCellValueFactory() {
        SId.setCellValueFactory(new PropertyValueFactory<StudentTM,Integer>("SId"));
        sName.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("SName"));
        SAddress.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("SNddress"));
        SContact.setCellValueFactory(new PropertyValueFactory<StudentTM,Integer>("SContact"));
        SMail.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("SEmail"));




    }

    private void getAll() {
        try {
//            ObservableList<StudentTM> obList = FXCollections.observableArrayList();
//            List<StudentManageDto> cusList = StudenatManagmentModel.getAll();

            ArrayList<StudentManageDto> cusList = studentBO.getAllStudent();

            for (StudentManageDto studentManage : cusList) {
                tblStud.getItems().add(new StudentTM(
                        studentManage.getSId(),
                        studentManage.getSName(),
                        studentManage.getSNddress(),
                        studentManage.getSContact(),
                        studentManage.getSEmail()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root2.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

   @FXML
    public void btnAddAction(ActionEvent actionEvent) {

       int SId = Integer.parseInt(txtId.getText());
       String SName = txtName.getText();
       String SNddress = txtAddress.getText();
       int SContact = 0;
       String SEmail = txtEmail.getText();


       if (!txtMobile.getText().matches("^(070|072|074|075|076|077|078)[0-9]{7}$")) {
           Print.setText("This Phone Number Is Wrong ");
           return;

       } else {
           SContact = Integer.parseInt(txtMobile.getText());
           Print.setText("");
       }

       CourseDAO courseDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);

       try {

            Student studentManage = new Student(SId, SName, SNddress, SContact, SEmail);
            studentBO.saveStudentWithCourse(studentManage,new CourseDetailDto((Integer) cmbCourses.getValue(), txtId.getText(),txtName.getText(),cmbCourses.getValue().toString(), LocalDate.now().toString()));

//
//
//            Connection connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            try {
               //boolean isSaved = StudenatManagmentModel.add(studentManage);
//                if (isSaved) {
                  // boolean courseSaved = CourseStudentModel.add(new CourseDetailDto(CourseModel.searchByName(cmbCourses.getValue().toString()).getCoure_Id(),
                     // txtId.getText(),txtName.getText(),cmbCourses.getValue().toString(), LocalDate.now().toString()));
//                    if (courseSaved) {
//                        new Alert(Alert.AlertType.CONFIRMATION, "Student saved!").show();
//                        connection.commit();
//
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
//            }
//            connection.rollback();
//            connection.setAutoCommit(true);
//            getAll();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @FXML
    public void btnUpdateAction(ActionEvent actionEvent) {

       int SId = Integer.parseInt(txtId.getText());
       String SName = txtName.getText();
       String SNddress = txtAddress.getText();
       int SContcat = Integer.parseInt(txtMobile.getText());
       String SEmail = txtEmail.getText();



       try {
           boolean isUpdated = studentBO.updateStudent(new StudentManageDto(SId,SName,SNddress,SContcat,SEmail));

           new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
       }
    }

   @FXML
    public void btnSeatchAction(ActionEvent actionEvent) {

       try {

           StudentManageDto dto = studentBO.searchStudent(Integer.valueOf(txtId.getText()));

           if (dto != null) {
               txtId.setText(String.valueOf(dto.getSId()));
               txtName.setText(dto.getSName());
               txtAddress.setText(String.valueOf(dto.getSNddress()));
               txtMobile.setText(String.valueOf(dto.getSContact()));
               txtEmail.setText(String.valueOf(dto.getSEmail()));
           }
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, "something happened!").show();
       }
   }


   @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

       Integer SId = Integer.valueOf(txtId.getText());
       try {
           //boolean isDeleted = StudenatManagmentModel.delete(SId );

           boolean b = studentBO.deleteStudent(SId);
           tblStud.getItems().remove(tblStud.getSelectionModel().getSelectedItem());
           tblStud.getSelectionModel().clearSelection();

           if (b) {
               new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
               getAll();
           }
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
       }

    }

    public void btnClearAction(ActionEvent actionEvent) {

        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtMobile.setText("");

    }

   @FXML
    public void tblOnAction(MouseEvent mouseEvent) {

       TableView.TableViewFocusModel<StudentTM> model =  tblStud.getFocusModel();
       TableView.TableViewSelectionModel<StudentTM> i = tblStud.getSelectionModel();

       try{
           txtId.setText(String.valueOf(model.getFocusedItem().getSId()));
           txtName.setText(model.getFocusedItem().getSName());
           txtAddress.setText(model.getFocusedItem().getSNddress());
           txtMobile.setText(String.valueOf(model.getFocusedItem().getSContact()));
           txtEmail.setText(model.getFocusedItem().getSEmail());


       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}


