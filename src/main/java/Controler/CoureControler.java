package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.RegistationBO;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.TM.AddmissionTM;
import dto.TM.CourseTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoureControler implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpadate;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnsearth;
    @FXML
    private TextField txtCourseID;
    @FXML
    private ComboBox<String> txtCourseName;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtTeacherID;
    @FXML
    private TextField TxtDate;
    @FXML
    private TextField txtCoureName;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<CourseTM> tableViewCourse;
    @FXML
    private TableColumn<CourseTM, Integer> CIDD;
    @FXML
    private TableColumn<CourseTM, String> CName;
    @FXML
    private TableColumn<CourseTM, Date> Dates;
    @FXML
    private TableColumn<CourseTM, Integer> StuID;
    @FXML
    private TableColumn<CourseTM, Integer> TeaID;
    @FXML
    private Button btnClaer;


    CourseBO courseBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Course_BO);

  @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();


    }

    private void setCellValueFactory() {
        CIDD.setCellValueFactory(new PropertyValueFactory<CourseTM,Integer>("Coure_Id"));
        TeaID.setCellValueFactory(new PropertyValueFactory<CourseTM,Integer>("TId"));
        CName.setCellValueFactory(new PropertyValueFactory<CourseTM,String>("Coure_Name"));
        StuID.setCellValueFactory(new PropertyValueFactory<CourseTM,Integer>("SId"));
        Dates.setCellValueFactory(new PropertyValueFactory<CourseTM,Date>("Coure_Date"));

    }

    private void getAll() {
        try {
//            ObservableList<CourseTM> obList = FXCollections.observableArrayList();
//            List<CourseDto> cusList = CourseModel.getAll();
            ArrayList<CourseDto> courseDtos = courseBO.getAllCourse();


            for (CourseDto course : courseDtos) {
                tableViewCourse.getItems().add(new CourseTM(
                        course.getCoure_Id(),
                        course.getTId(),
                        course.getCoure_Name(),
                        course.getSId(),
                        course.getCoure_Date()
                ));
            }
         //   tableViewCourse.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();


    }

    public void btnSaveAction(ActionEvent actionEvent) {

        int Coure_Id = Integer.parseInt(String.valueOf(txtCourseID.getText()));
        int TId = Integer.parseInt(txtTeacherID.getText());
        String txtCourseName = txtCoureName.getText();
        int SId = Integer.parseInt(txtStudentId.getText());
        String Coure_Date = TxtDate.getText();



        try {
            boolean b =courseBO.saveCourse(new CourseDto(Coure_Id,TId,txtCourseName,SId,Coure_Date));
            tableViewCourse.getItems().add(new CourseTM(Coure_Id,TId,txtCourseName,SId,Coure_Date));


            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                getAll();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }


    public void btnDeleteAction(ActionEvent actionEvent) {

        Integer Coure_Id = Integer.valueOf((String) txtCourseID.getText());
        try {
            boolean b = courseBO.deleteCourse(Coure_Id);
            tableViewCourse.getItems().remove(tableViewCourse.getSelectionModel().getSelectedItem());
            tableViewCourse.getSelectionModel().clearSelection();

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                getAll();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }


    public void btnUpdateAction(ActionEvent actionEvent) {

    }

    public void btnSeartchAction(ActionEvent actionEvent) {

        try {

            CourseDto dto = courseBO.searchCourse(Integer.valueOf(txtCourseID.getText()));
            if (dto != null) {
                txtCourseID.setText(String.valueOf(dto.getCoure_Id()));
                txtTeacherID.setText(String.valueOf(dto.getTId()));
                txtCoureName.setText(String.valueOf(dto.getCoure_Name()));
                txtStudentId.setText(String.valueOf(dto.getSId()));
                TxtDate.setText(String.valueOf(dto.getCoure_Date()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    public void btnClearAction(ActionEvent actionEvent) {

        txtCourseID.setText("");
        txtTeacherID.setText("");
        txtCoureName.setText("");
        txtStudentId.setText("");
        TxtDate.setText("");
    }
}
