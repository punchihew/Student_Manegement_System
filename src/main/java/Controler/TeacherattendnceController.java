package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.TeacherAttendceBO;
import dto.ExamDto;
import dto.TM.TeacherAttendenceTM;
import dto.TM.TeacherTM;
import dto.TeacherAttendceDto;
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

public class TeacherattendnceController implements Initializable {

    @FXML
    public Button btnCome;

    @FXML
    public Button btnSerhat;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnBack;

    @FXML
    public TextField TeacherId;

    @FXML
    public TextField TeacherDate;

    @FXML
    public TextField TeacherTime;

    @FXML
    private Label lblTime;

    public Button btnClean;

    @FXML
    private AnchorPane root;


    @FXML
    private TableColumn<TeacherAttendenceTM, Date> Date;

    @FXML
    private TableColumn<TeacherAttendenceTM, Integer> TeacherID;

    @FXML
    private TableView<TeacherAttendenceTM> tblTeacherAttencde;

    @FXML
    private TableColumn<TeacherAttendenceTM, Double> time;


    TeacherAttendceBO teacherAttendceBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Teacher_Attendce_BO);

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
        TeacherID.setCellValueFactory(new PropertyValueFactory<TeacherAttendenceTM,Integer>("TId"));
        Date.setCellValueFactory(new PropertyValueFactory<TeacherAttendenceTM,Date>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<TeacherAttendenceTM,Double>("Time"));

    }

    private void getAll() {
        try {
            ArrayList<TeacherAttendceDto> cusList = teacherAttendceBO.getAllTeacherAttendce();


            for (TeacherAttendceDto teacherAttendce : cusList) {
                tblTeacherAttencde.getItems().add(new TeacherAttendenceTM(
                        teacherAttendce.getTId(),
                        teacherAttendce.getDate(),
                        teacherAttendce.getTime()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }




    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Attendence.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

     @FXML
    public void btnDeleteAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

         Integer TId = Integer.valueOf(TeacherId.getText());


         try {
             boolean b = teacherAttendceBO.deleteTeacherAttendce(TId);
             tblTeacherAttencde.getItems().remove(tblTeacherAttencde.getSelectionModel().getSelectedItem());
             tblTeacherAttencde.getSelectionModel().clearSelection();

             if (b) {
                 new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
         }
    }

     @FXML
    public void btnSerathAction(ActionEvent actionEvent) {

         try {
             TeacherAttendceDto dto = teacherAttendceBO.searchTeacherAttendce(Integer.valueOf(TeacherId.getText()));

             if (dto != null) {
                 TeacherId.setText(String.valueOf(dto.getTId()));
                 TeacherDate.setText(String.valueOf(dto.getDate()));
                 TeacherTime.setText(String.valueOf(dto.getTime()));
             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "something happened!").show();
         }
    }

    @FXML
    public void btnComeAction(ActionEvent actionEvent) {

        int TId = Integer.parseInt(TeacherId.getText());
        Date Date = java.sql.Date.valueOf(TeacherDate.getText());
        Double Time = Double.parseDouble(TeacherTime.getText());



        try {
            boolean b = teacherAttendceBO.saveTeacherAttendce(new TeacherAttendceDto(TId,Date,Time));
            tblTeacherAttencde.getItems().add(new TeacherAttendenceTM(TId,Date,Time));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (
                SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        getAll();
    }

    public void btnClearAction(ActionEvent actionEvent) {

        TeacherID.setText("");
        TeacherTime.setText("");
        TeacherDate.setText("");
    }

    public void tblOnAction(MouseEvent mouseEvent) {


        TableView.TableViewFocusModel<TeacherAttendenceTM> model =  tblTeacherAttencde.getFocusModel();
        TableView.TableViewSelectionModel<TeacherAttendenceTM> i = tblTeacherAttencde.getSelectionModel();

        try{
            TeacherID.setText(String.valueOf(model.getFocusedItem().getTId()));
            TeacherDate.setText(String.valueOf(model.getFocusedItem().getDate()));
            TeacherTime.setText(String.valueOf(model.getFocusedItem().getTime()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

