package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.StudentAttendceBO;
import dto.ExamDto;
import dto.StudentAttendeDto;
import dto.TM.StudentAttendeceTM;
import dto.TM.TeacherTM;
import dto.TeacherManageDto;
import javafx.application.Platform;
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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class StudentAttendceController implements Initializable {

    @FXML
    public Button btn1;

    @FXML
    public Button btnSearth;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnCame;

    @FXML
    public TextField StudentId;

    @FXML
    public TextField StudentDate;

    @FXML
    public TextField StudentTime;
    @FXML
    public Button btnClear;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<StudentAttendeceTM, Integer> tbleStudentId;

    @FXML
    private TableColumn<StudentAttendeceTM, Double> Time;

    @FXML
    private TableColumn<StudentAttendeceTM, Date> studentDate;

    @FXML
    private TableView<StudentAttendeceTM> tblStudentAt;


    @FXML
    private Label lblTime;


    StudentAttendceBO studentAttendceBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Student_Attendce_BO);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        RunningTime();
    }

    private void RunningTime() {
        final   Thread thread = new Thread(()->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true){
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new java.util.Date());
                Platform.runLater(()->{

                    lblTime.setText(time);
                });
            }

        });
        thread.start();
    }
    private void setCellValueFactory() {
        tbleStudentId.setCellValueFactory(new PropertyValueFactory<StudentAttendeceTM,Integer>("SId"));
        studentDate.setCellValueFactory(new PropertyValueFactory<StudentAttendeceTM,Date>("Date"));
        Time.setCellValueFactory(new PropertyValueFactory<StudentAttendeceTM,Double>("Time"));

    }

    private void getAll() {
        try {
            ArrayList<StudentAttendeDto> cusList = studentAttendceBO.getAllStudentAttendc();

            for (StudentAttendeDto studentAttende : cusList) {
                tblStudentAt.getItems().add(new StudentAttendeceTM(
                        studentAttende.getSId(),
                        studentAttende.getDate(),
                        studentAttende.getTime()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    @FXML
    public void btnBckAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Attendence.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    @FXML
    public void btnSearthAction(ActionEvent actionEvent) {

        try {

            StudentAttendeDto dto = studentAttendceBO.searchStudentAttendc(Integer.valueOf(StudentId.getText()));

            if (dto != null) {
                StudentId.setText(String.valueOf(dto.getSId()));
                StudentDate.setText(String.valueOf(dto.getDate()));
                StudentTime.setText(String.valueOf(dto.getTime()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

        Integer SId = Integer.valueOf(StudentId.getText());

        try {
            boolean b = studentAttendceBO.deleteStudentAttendc(SId);
            tblStudentAt.getItems().remove(tblStudentAt.getSelectionModel().getSelectedItem());
            tblStudentAt.getSelectionModel().clearSelection();

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    public void btnCameAction(ActionEvent actionEvent) throws ClassNotFoundException {


        int SId = Integer.parseInt(StudentId.getText());
        Date Date = java.sql.Date.valueOf(StudentDate.getText());
        Double Time = parseDouble(StudentTime.getText());


//        StudentAttendeDto studentAttende = new StudentAttendeDto(SId,Date, Time);

        try {
            boolean b = studentAttendceBO.saveStudentAttendc(new StudentAttendeDto(SId,Date,Time));
            tblStudentAt.getItems().add(new StudentAttendeceTM(SId,Date,Time));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        getAll();
    }

    public void btnClearsAction(ActionEvent actionEvent) {
        StudentId.setText("");
        StudentDate.setText("");
        StudentTime.setText("");

            }

    public void tblOnAction(MouseEvent mouseEvent) {


        TableView.TableViewFocusModel<StudentAttendeceTM> model =  tblStudentAt.getFocusModel();
        TableView.TableViewSelectionModel<StudentAttendeceTM> i = tblStudentAt.getSelectionModel();

        try{
            StudentId.setText(String.valueOf(model.getFocusedItem().getSId()));
            StudentDate.setText(String.valueOf(model.getFocusedItem().getDate()));
            StudentTime.setText(String.valueOf(model.getFocusedItem().getTime()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


