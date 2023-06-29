package dao.custom;

import dao.custom.impl.SalaryDAO;
import dao.custom.impl.util.SQLUtil;
import dto.ExamDto;
import dto.SalaryDto;
import entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean add(Salary exam) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary (TId, Salary_Id ,Fee ,SDate) \" +\n" +
                "  \"VALUES(?, ?, ?, ?)", exam.getTId(),exam.getSalary_Id(),exam.getFee(),exam.getSDate());

    }

    @Override
    public boolean delete(Integer Salary_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salary WHERE Salary_Id = ?", Salary_Id);
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        while (rst.next()) {
            allCustomers.add(new Salary(rst.getInt(1),rst.getInt(2),rst.getDouble(3),rst.getDate(4)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Salary search(Integer TId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM exam  WHERE SId = ?" ,TId);

        if (rst.next()) {
            return new Salary(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getDate(4));
        }
        return null;
    }


}
