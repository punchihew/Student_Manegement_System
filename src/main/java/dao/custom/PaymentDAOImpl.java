package dao.custom;

import dao.custom.impl.PayemntDAO;
import dao.custom.impl.util.SQLUtil;
import dto.PaymentDto;
import entity.Payemnt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PayemntDAO {

    @Override
    public boolean add(Payemnt payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment (Pay_Id,SId ,Coure_ID,Fee,Pay_Date) \" +\n" +
                "\"VALUES(?, ?, ?, ?,?)", payment.getPay_Id(),payment.getSId(),payment.getCours_Id(),payment.getFee(),payment.getPay_Date());

    }

    @Override
    public boolean delete(Integer Pay_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE Pay_Id = ?", Pay_Id);
    }

  @Override
    public ArrayList<Payemnt> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payemnt> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        while (rst.next()) {
            allCustomers.add(new Payemnt(rst.getInt(1), rst.getInt(2),rst.getInt(3),rst.getInt(4),rst.getDate(5)));


        }
        return allCustomers;
    }
   @Override
    public  boolean update(Payemnt entity) throws SQLException, ClassNotFoundException {

        return false;
    }

    @Override
    public Payemnt search(Integer SId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE SId = ?" ,SId);

        if (rst.next()) {
            return new Payemnt(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getInt(4), rst.getDate(5));
        }
        return null;
    }

}

