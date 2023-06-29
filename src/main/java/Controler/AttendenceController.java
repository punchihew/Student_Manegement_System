package Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendenceController {

    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public Button btnBack;


    @FXML
    public void btnStudentAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentAttendce.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }


     @FXML
    public void btnTeacherAction(ActionEvent actionEvent) throws IOException {

         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/teacherattendnce.fxml"));

         Scene scene = new Scene(anchorPane);

         Stage stage = (Stage)btn2.getScene().getWindow();
         stage.setScene(scene);
         stage.setTitle("Home");
         stage.centerOnScreen();
    }


    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }
}
