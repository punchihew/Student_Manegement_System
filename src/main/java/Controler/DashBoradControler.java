package Controler;


import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoradControler {

    public AnchorPane root;
    @FXML
    private ImageView UserName;

    @FXML
    private Button btn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label worng;

    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        chekLogin();

    }
    private void chekLogin()throws IOException{

        if(username.getText().toString().equals("navishka")&&password.getText().toString().equals("1234")){
            worng.setText("Success !");

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage)root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Customer Manage");
            stage.centerOnScreen();

        }else if(username.getText().isEmpty()&& password.getText().isEmpty()){
            worng.setText("Please enter your data ");
        }else{
            worng.setText("Worng Username or password !");
        }
    }

}
