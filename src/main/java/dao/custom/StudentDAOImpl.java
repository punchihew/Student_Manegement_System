package dao.custom;

import dao.custom.impl.StudentDAO;
import dao.custom.impl.util.SQLUtil;
import dto.StudentManageDto;
import entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student studentManage) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO student (SId,SName,SNddress,SContcat,SEmail) " +
                "VALUES(?, ?, ?, ?,?)", studentManage.getSId(),studentManage.getSName(),studentManage.getSNddress(),studentManage.getSContact(),studentManage.getSEmail());

    }

    @Override
    public boolean delete(Integer SId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM student WHERE SId = ?", SId);
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM student");
        while (rst.next()) {
            allCustomers.add(new Student(rst.getInt(1), rst.getString(2),rst.getString(3),rst.getInt(4),rst.getString(5)));


        }
        return allCustomers;
    }


    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE student SET SName = ?, SNddress = ?, \" +\n" +
                "                    \"SContcat = ?, SEmail = ?  WHERE SId = ?", entity.getSId(), entity.getSName(), entity.getSNddress(),entity.getSContact(),entity.getSEmail());

    }

    @Override
    public Student search(Integer SId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM student WHERE SId = ?" ,SId);

        if (rst.next()) {
            return new Student(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5));
        }
        return null;
    }
    }

