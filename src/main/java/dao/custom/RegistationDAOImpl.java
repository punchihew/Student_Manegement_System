package dao.custom;

import dao.custom.impl.RegistationDAO;
import dao.custom.impl.util.SQLUtil;
import dto.RegistrationDto;
import entity.Registation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistationDAOImpl implements RegistationDAO {
    @Override
    public boolean add(Registation registration) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO registation (Reg_Id,Reg_Name ,Reg_Contcat,Reg_Date ,Reg_Address) \" +\n" +
                "\"VALUES(?, ?, ?, ?,?)", registration.getReg_Id(),registration.getReg_Name(),registration.getReg_Contact(),registration.getReg_Address());

    }

    @Override
    public boolean delete(Integer Reg_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM registation WHERE Reg_id = ?", Reg_id);
    }

    @Override
    public ArrayList<Registation> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Registation> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM registation");
        while (rst.next()) {
            allCustomers.add(new Registation(rst.getInt(1), rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Registation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Registation search(Integer Reg_Id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM registation WHERE Reg_Id = ?" ,Reg_Id);

        if (rst.next()) {
            return new Registation(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5));
        }
        return null;
    }
    }

