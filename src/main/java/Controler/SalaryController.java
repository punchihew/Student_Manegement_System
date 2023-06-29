package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.SalaryBO;
import dto.ExamDto;
import dto.SalaryDto;
import dto.TM.SalaryTM;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    public Button btnBackAction;

    @FXML
    public Button btndelete;

    @FXML
    public Button btnSerath;

    @FXML
    public Button btnPay;

    @FXML
    public TextField txtPayId;

    @FXML
    public TextField txtTeacherId;

    @FXML
    public TextField txtFee;

    @FXML
    public TextField txtDate;
    @FXML
    public Button btnClear;

    @FXML
    private TableColumn<SalaryTM,Date> Dates;

    @FXML
    private TableColumn<SalaryTM, Double> Fee;

    @FXML
    private TableColumn<SalaryTM, Integer> SalaryIDD;

    @FXML
    private TableView<SalaryTM> TableViewSalary;

    @FXML
    private TableColumn<SalaryTM, Integer> TeacID;


    @FXML
    private Label tblDate;

    SalaryBO salaryBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Salary_BO);


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        setOrderDate();
    }
    private void setOrderDate() {
        tblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        TeacID.setCellValueFactory(new PropertyValueFactory<SalaryTM,Integer>("TId"));
        SalaryIDD.setCellValueFactory(new PropertyValueFactory<SalaryTM,Integer>("Salary_Id"));
        Fee.setCellValueFactory(new PropertyValueFactory<SalaryTM,Double>("Fee"));
        Dates.setCellValueFactory(new PropertyValueFactory<SalaryTM,Date>("SDate"));

    }

    private void getAll() {
        try {
//            ObservableList<SalaryTM> obList = FXCollections.observableArrayList();
//            List<SalaryDto> cusList = salaryModel.getAll();
            ArrayList<SalaryDto> cusList = salaryBO.getAllSalary();

            for (SalaryDto salary : cusList) {
                TableViewSalary.getItems().add(new SalaryTM(
                        salary.getTId(),
                        salary.getSalary_Id(),
                        salary.getFee(),
                        salary.getSDate()

                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    @FXML
    public void btnPayAction(ActionEvent actionEvent) throws ClassNotFoundException {


        int TId = Integer.parseInt(txtTeacherId.getText());
        int Salary_id  = Integer.parseInt(txtPayId.getText());
        Double Fee = Double.parseDouble(txtFee.getText());
        Date Pay_Date = Date.valueOf(txtDate.getText());




        try {
            boolean b =salaryBO.saveSalary(new SalaryDto(TId,Salary_id,Fee,Pay_Date));
            TableViewSalary.getItems().add(new SalaryTM(TId,Salary_id,Fee,Pay_Date));

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

    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)btnBackAction.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

        Integer Salary_Id = Integer.valueOf(txtPayId.getText());
        try {
            boolean b = salaryBO.deleteSalary(Salary_Id);
            TableViewSalary.getItems().remove(TableViewSalary.getSelectionModel().getSelectedItem());
            TableViewSalary.getSelectionModel().clearSelection();

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    public void btnsearthAction(ActionEvent actionEvent) {

        try {


            SalaryDto dto = salaryBO.searchSalary(Integer.valueOf(txtTeacherId.getText()));

            if (dto != null) {
                txtPayId.setText(String.valueOf(dto.getSalary_Id()));
                txtTeacherId.setText(String.valueOf(dto.getTId()));
                txtFee.setText(String.valueOf(dto.getFee()));
                txtDate.setText(String.valueOf(dto.getSDate()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    public void btnClearAction(ActionEvent actionEvent) {

        txtTeacherId.setText("");
        txtPayId.setText("");
        txtFee.setText("");
        txtDate.setText("");

    }

    public void tblOnAction(MouseEvent mouseEvent) {


        TableView.TableViewFocusModel<SalaryTM> model =  TableViewSalary.getFocusModel();
        TableView.TableViewSelectionModel<SalaryTM> i = TableViewSalary.getSelectionModel();

        try{
            txtTeacherId.setText(String.valueOf(model.getFocusedItem().getTId()));
            txtPayId.setText(String.valueOf(model.getFocusedItem().getSalary_Id()));
            txtFee.setText(String.valueOf(model.getFocusedItem().getFee()));
            txtDate.setText(String.valueOf(model.getFocusedItem().getSDate()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
