package dao.custom;

import dao.CrudDAO;
import dao.custom.impl.TeacherDAO;
import dao.custom.impl.util.SQLUtil;
import dto.TeacherManageDto;
import entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAOImpl implements TeacherDAO {

    @Override
    public boolean add(Teacher teacherManage) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO student (SId,SName,SNddress,SContcat,SEmail) " +
                "VALUES(?, ?, ?, ?,?)", teacherManage.getTId(), teacherManage.getTName(), teacherManage.getTAddress(), teacherManage.getTContact(), teacherManage.getTEmail());

    }

    @Override
    public boolean delete(Integer TId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM teacher WHERE TId = ?", TId);
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM teacher");
        while (rst.next()) {
            allCustomers.add(new Teacher(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Teacher entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE teacher SET TName = ?, TNddress = ?, \" +\n" +
                "\"TContcat = ?, TEmail = ? WHERE TId = ?", entity.getTId(), entity.getTName(), entity.getTAddress(), entity.getTContact(), entity.getTEmail());

    }

    @Override
    public Teacher search(Integer TId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM teacher WHERE TId = ?", TId);

        if (rst.next()) {
            return new Teacher(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5));
        }
        return null;
    }

}