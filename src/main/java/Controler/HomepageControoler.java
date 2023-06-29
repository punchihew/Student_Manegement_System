package Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomepageControoler  {

    @FXML
    public Button btn1;

    @FXML
    public Button btn2;

    @FXML
    public Button btn3;

    @FXML
    public Button btn9;

    @FXML
    public Button btn8;

    @FXML
    public Button btn7;

    @FXML
    public Button btn5;

    @FXML
    public Button btn4;

    @FXML
    public Button back;

    @FXML
    private AnchorPane root;

    public void btnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ManageStudent.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    public void btnAddMissionAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Addimision.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }


    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBorad.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)back.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    public void btnTeacherAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/manageTeacher.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn3.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    public void btnExamAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Exam.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn9.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    public void btnAttendceAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Attendence.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn5.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }


    public void btnPayemntActioon(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Payemant.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn7.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();


    }

    public void btnSalaryAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/salary.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn8.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    public void btnCourseaction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Coure.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btn4.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }
}

