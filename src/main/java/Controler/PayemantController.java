package Controler;

import bo.custom.BoFactory;
import bo.custom.impl.CourseBO;
import bo.custom.impl.PaymentBO;
import db.DBConnection;
import dto.ExamDto;
import dto.PaymentDto;
import dto.TM.CourseTM;
import dto.TM.PayemntTM;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayemantController implements Initializable {

    
    /*@FXML
    public Label payID;

    @FXML
    public Label StudentID;

    @FXML
    public Label courseId;

    @FXML
    public Label feeId;


    @FXML
    public Label date; */


    @FXML
    public Button btnSearch;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnPay;

    @FXML
    public Button btnBack;

    @FXML
    public TextField txtDate;

    @FXML
    public TextField StudentId;

    @FXML
    public TextField txtCourseID;

    @FXML
    public TextField txtFee;

    @FXML
    public TextField txtPayId;
    @FXML
    public Button btnClear;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PayemntTM> TableViewPayemnt;

    @FXML
    private TableColumn<PayemntTM, Integer> CourseID;

    @FXML
    private TableColumn<PayemntTM, Date> Dates;

    @FXML
    private TableColumn<PayemntTM, Integer> Fee;

    @FXML
    private TableColumn<PayemntTM, Integer> PayID;


    @FXML
    private TableColumn<PayemntTM, Integer> StudentIdd;


    @FXML
    private Label lblDate;


    PaymentBO paymentBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Payment_BO);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        setOrderDate();
    }
    private void setOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        PayID.setCellValueFactory(new PropertyValueFactory<PayemntTM,Integer>("Pay_Id"));
        StudentIdd.setCellValueFactory(new PropertyValueFactory<PayemntTM,Integer>("SId"));
        CourseID.setCellValueFactory(new PropertyValueFactory<PayemntTM,Integer>("Cours_Id"));
        Fee.setCellValueFactory(new PropertyValueFactory<PayemntTM,Integer>("Fee"));
        Dates.setCellValueFactory(new PropertyValueFactory<PayemntTM,Date>("Pay_Date"));

    }

    private void getAll() {
        try {
//            ObservableList<PayemntTM> obList = FXCollections.observableArrayList();
//            List<PaymentDto> cusList = PaymentMOdel.getAll();

            ArrayList<PaymentDto> cusList = paymentBO.getAllPayment();


            for (PaymentDto payment : cusList) {
                TableViewPayemnt.getItems().add(new PayemntTM(
                        payment.getPay_Id(),
                        payment.getSId(),
                        payment.getCours_Id(),
                        payment.getFee(),
                        payment.getPay_Date()
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

        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    @FXML
    public void btnPayemntAction(ActionEvent actionEvent) {

        int Pay_Id = Integer.parseInt(txtPayId.getText());
        int SId = Integer.parseInt(StudentId.getText());
        int Coure_Id = Integer.parseInt(txtCourseID.getText());
        int Fee = Integer.parseInt(txtFee.getText());
        Date Pay_Date = Date.valueOf(txtDate.getText());



        try {
            boolean b = paymentBO.savePayment(new PaymentDto(Pay_Id,SId,Coure_Id,Fee,Pay_Date));
            TableViewPayemnt.getItems().add(new PayemntTM(Pay_Id,SId,Coure_Id,Fee,Pay_Date));

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
    public void btnSeatchAction(ActionEvent actionEvent) {

         try {

              PaymentDto dto = paymentBO.searchPayment(Integer.valueOf(StudentId.getText()));

             if (dto != null) {
                 txtPayId.setText(String.valueOf(dto.getPay_Id()));
                 StudentId.setText(String.valueOf(dto.getSId()));
                 txtCourseID.setText(String.valueOf(dto.getCours_Id()));
                 txtFee.setText(String.valueOf(dto.getFee()));
                 txtDate.setText(String.valueOf(dto.getPay_Date()));
             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "something happened!").show();
         }
    }

     @FXML
    public void btnDeleteAction(ActionEvent actionEvent) {

         Integer Pay_Id = Integer.valueOf(txtPayId.getText());

         try {
             boolean b = paymentBO.deletePayment(Pay_Id);
             TableViewPayemnt.getItems().remove(TableViewPayemnt.getSelectionModel().getSelectedItem());
             TableViewPayemnt.getSelectionModel().clearSelection();

             if (b) {
                 new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                 getAll();
             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
         }
    }

    public void btnClaerAction(ActionEvent actionEvent) {

        txtPayId.setText("");
        StudentId.setText("");
        txtCourseID.setText("");
        txtFee.setText("");
        txtDate.setText("");
    }

    public void tblOnAction(MouseEvent mouseEvent) {

        TableView.TableViewFocusModel<PayemntTM> model =  TableViewPayemnt.getFocusModel();
        TableView.TableViewSelectionModel<PayemntTM> i = TableViewPayemnt.getSelectionModel();

        try{
            txtPayId.setText(String.valueOf(model.getFocusedItem().getPay_Id()));
            StudentId.setText(String.valueOf(model.getFocusedItem().getSId()));
            txtCourseID.setText(String.valueOf(model.getFocusedItem().getCours_Id()));
            txtFee.setText(String.valueOf(model.getFocusedItem().getFee()));
            txtDate.setText(String.valueOf(model.getFocusedItem().getPay_Date()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnReportAction(ActionEvent actionEvent) {

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("file/rrce.jrxml");
            JasperReport report= JasperCompileManager.compileReport(is);
            Map<String,Object> par=new HashMap<>();
            par.put("pay",2);
            JasperPrint print= JasperFillManager.fillReport(report,null,connection);
            JasperViewer.viewReport(print,false);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            Logger.getLogger(PayemantController.class.getName()).log(Level.SEVERE,null,connection);
        }

    }
}
