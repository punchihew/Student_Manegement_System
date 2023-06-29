package Controler;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;


public class ReportController implements Initializable {


      private static final String URL = "jdbc:mysql://localhost:3306/Student";
      private static final Properties props = new Properties();

      static {
          props.setProperty("user", "root");
          props.setProperty("password", "1234");
      }

      @FXML
      public static Label Count;

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

        connect();
    }

      public static int connect() throws SQLException {

          PreparedStatement pstm = null;

          System.out.println("11");
          try {
              Connection con = DriverManager.getConnection(URL, props);
              String sql = "SELECT COUNT(*) FROM student";

              pstm = con.prepareStatement(String.valueOf(sql));

              ResultSet resultSet = pstm.executeQuery();

              //int count = 1;
              while (resultSet.next()){
                  System.out.println("1");

                   int count = resultSet.getInt("SId");

                  System.out.println("2");

                  Count.setText(String.valueOf(count));

              }


          } catch (SQLException e) {
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR, "SQL Error!").show();

          }


          return pstm.getUpdateCount();
      }

}
