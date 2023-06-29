package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.ExamBO;
import dto.CourseDto;
import dto.ExamDto;
import dto.TM.AddmissionTM;
import dto.TM.CourseTM;
import dto.TM.ExamTM;
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

public class ExamController implements Initializable {

    @FXML
    public Button btnSearch;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnBack;

    @FXML
    public Button btnAdds;

    @FXML
    public TextField txtCourseId;

    @FXML
    public TextField txtstudentId;

    @FXML
    public TextField txtCoursename;

    @FXML
    public TextField txtSubject;

    @FXML
    public TextField txtMarks;

    @FXML
    public Button btnClear;

    @FXML
    private TableColumn<ExamTM, Integer> courseId;

    @FXML
    private TableColumn<ExamTM, Integer> marks;

    @FXML
    private TableColumn<ExamTM, String> name;

    @FXML
    private TableColumn<ExamTM, Integer> studentID;

    @FXML
    private TableColumn<ExamTM, String> subject;

    @FXML
    private TableView<ExamTM> tblExam;



    ExamBO examBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Exam_BO);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        studentID.setCellValueFactory(new PropertyValueFactory<ExamTM,Integer>("SId"));
        courseId.setCellValueFactory(new PropertyValueFactory<ExamTM,Integer>("Coure_Id"));
        name.setCellValueFactory(new PropertyValueFactory<ExamTM,String>("Name"));
        subject.setCellValueFactory(new PropertyValueFactory<ExamTM,String>("Subject"));
        marks.setCellValueFactory(new PropertyValueFactory<ExamTM,Integer>("Marks"));

    }

    private void getAll() {
        try {
//            ObservableList<ExamTM> obList = FXCollections.observableArrayList();
//            List<ExamDto> cusList = ExamModel.getAll();
//
            ArrayList<ExamDto> courseDtos = examBO.getAllExam();

            for (ExamDto exam: courseDtos) {
               tblExam.getItems() .add(new ExamTM(
                        exam.getSId(),
                        exam.getCoure_Id(),
                        exam.getName(),
                        exam.getSubject(),
                        exam.getMarks()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    public void btnSearchAction(ActionEvent actionEvent) {

        try {


            ExamDto dto = examBO.searchExam(Integer.valueOf(txtstudentId.getText()));

            if (dto != null) {
                txtstudentId.setText(String.valueOf(dto.getSId()));
                txtCourseId.setText(String.valueOf(dto.getCoure_Id()));
                txtCoursename.setText(dto.getName());
                txtSubject.setText(String.valueOf(dto.getSubject()));
                txtMarks.setText(String.valueOf(dto.getMarks()));


            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

        /*Integer SId = Integer.valueOf(txtstudentId.getText());
        try {
            boolean isDeleted = ExamModel.delete(SId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
     */}

    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    @FXML
    public void btnAddActions(ActionEvent actionEvent) throws ClassNotFoundException {

        int SId = Integer.parseInt(txtstudentId.getText());
        int Coure_Id = Integer.parseInt(txtCourseId.getText());
        String Name = txtCoursename.getText();
        String Subject = txtSubject.getText();
        int Marks = Integer.valueOf(txtMarks.getText());




        try {
            boolean b = examBO.saveExam(new ExamDto(SId,Coure_Id,Name,Subject,Marks));
            tblExam.getItems().add(new ExamTM(SId,Coure_Id,Name,Subject,Marks));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        getAll();
    }

    public void btnClearAction(ActionEvent actionEvent) {

        txtstudentId.setText("");
        txtCourseId.setText("");
        txtCoursename.setText("");
        txtSubject.setText("");
        txtMarks.setText("");
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        TableView.TableViewFocusModel<ExamTM> model =  tblExam.getFocusModel();
        TableView.TableViewSelectionModel<ExamTM> i = tblExam.getSelectionModel();

        try{
            txtstudentId.setText(String.valueOf(model.getFocusedItem().getSId()));
            txtCourseId.setText(String.valueOf(model.getFocusedItem().getCoure_Id()));
            txtCoursename.setText(model.getFocusedItem().getName());
            txtSubject.setText(model.getFocusedItem().getSubject());
            txtMarks.setText(String.valueOf(model.getFocusedItem().getMarks()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
