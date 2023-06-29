package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.RegistationBO;
import dto.RegistrationDto;
import dto.TM.AddmissionTM;
import entity.Registation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddimisionControoler implements Initializable {

    @FXML
    public TextField txtId;
    @FXML
    public TextField txtMobile;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtAddress;
    @FXML
    public TextField txtDate;
    @FXML
    public Button btnBack;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnSearth;
    @FXML
    public Label labeltoday;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<AddmissionTM> tbleView;
    @FXML
    private TableColumn<AddmissionTM, String> StuAddress;
    @FXML
    private TableColumn<AddmissionTM, Date> StuDate;
    @FXML
    private TableColumn<AddmissionTM, Integer> StuId;
    @FXML
    private TableColumn<AddmissionTM, Integer> StuMobile;
    @FXML
    private TableColumn<AddmissionTM, String> StuName;



    RegistationBO registationBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Registation_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setOrderDate();
        setCellValueFactory();
        getAll();

    }

    private void setCellValueFactory() {
        StuId.setCellValueFactory(new PropertyValueFactory<AddmissionTM,Integer>("Reg_Id"));
        StuName.setCellValueFactory(new PropertyValueFactory<AddmissionTM,String>("Reg_Name"));
        StuMobile.setCellValueFactory(new PropertyValueFactory<AddmissionTM,Integer>("Reg_Contact"));
        StuDate.setCellValueFactory(new PropertyValueFactory<AddmissionTM,Date>("Reg_Date"));
        StuAddress.setCellValueFactory(new PropertyValueFactory<AddmissionTM,String>("Reg_Address"));

    }

    private void getAll() {
        try {
//            ObservableList<AddmissionTM> obList = FXCollections.observableArrayList();
//            List<RegistrationDto> cusList = AddmisinModel.getAll();

            ArrayList<RegistrationDto> registrationDtos = registationBO.getAllRegistatio();

            for (RegistrationDto registration : registrationDtos) {
               tbleView.getItems().add(new AddmissionTM(registration.getReg_Id(),registration.getReg_Name(),registration.getReg_Contact(),registration.getReg_Date(),registration.getReg_Address()));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setOrderDate() {
        labeltoday.setText(String.valueOf(LocalDate.now()));
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
     @FXML
    public void btnSaveAction(ActionEvent actionEvent) {
         int Reg_Id = Integer.parseInt(txtId.getText());
         String Reg_Name = txtName.getText();
         String Reg_Contact = txtMobile.getText();
         Date Reg_Date = Date.valueOf(txtDate.getText());
         String Reg_Address = txtAddress.getText();

       //  RegistrationDto registration = new RegistrationDto(Reg_Id,Reg_Name, Reg_Contact, Reg_Date,Reg_Address);

        try {
           // boolean isSaved = AddmisinModel.save(registration);
            boolean b = registationBO.saveRegistatio(new RegistrationDto(Reg_Id, Reg_Name, Reg_Contact, Reg_Date, Reg_Address));

            tbleView.getItems().add(new AddmissionTM(Reg_Id,Reg_Name,Reg_Contact,Reg_Date,Reg_Address));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

        getAll();
    }
    @FXML
    public void btnDeleteAction(ActionEvent actionEvent) throws ClassNotFoundException {

        Integer Reg_id = Integer.valueOf(txtId.getText());

        try {

            boolean b = registationBO.deleteRegistatio(Reg_id);
            tbleView.getItems().remove(tbleView.getSelectionModel().getSelectedItem());
            tbleView.getSelectionModel().clearSelection();

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
             e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }
     @FXML
    public void btnSearchAction(ActionEvent actionEvent) {
         try {
             RegistrationDto dto = registationBO.searchRegistatio(Integer.valueOf(txtId.getText()));


             if (dto != null) {
                 txtId.setText(String.valueOf(dto.getReg_Id()));
                 txtName.setText(dto.getReg_Name());
                 txtMobile.setText(String.valueOf(dto.getReg_Contact()));
                 txtDate.setText(String.valueOf(dto.getReg_Date()));
                 txtAddress.setText(String.valueOf(dto.getReg_Address()));
             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "something happened!").show();
         }
     }


    public void btnClearAction(ActionEvent actionEvent) {

        txtId.setText("");
        txtName.setText("");
        txtMobile.setText("");
        txtDate.setText("");
        txtAddress.setText("");

    }

    public void tblOnAction(MouseEvent mouseEvent) {


        TableView.TableViewFocusModel<AddmissionTM> model =  tbleView.getFocusModel();
        TableView.TableViewSelectionModel<AddmissionTM> i = tbleView.getSelectionModel();

        try{
            txtId.setText(String.valueOf(model.getFocusedItem().getReg_Id()));
            txtName.setText(model.getFocusedItem().getReg_Name());
            txtMobile.setText(String.valueOf(model.getFocusedItem().getReg_Contact()));
            txtAddress.setText(model.getFocusedItem().getReg_Address());
            txtDate.setText(String.valueOf(model.getFocusedItem().getReg_Date()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


