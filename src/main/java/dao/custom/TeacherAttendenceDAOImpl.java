package dao.custom;

import dao.CrudDAO;
import dao.custom.impl.TeacherAttendenceDAO;
import dao.custom.impl.util.SQLUtil;
import dto.StudentAttendeDto;
import dto.TeacherAttendceDto;
import entity.Teacher_Attend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherAttendenceDAOImpl implements TeacherAttendenceDAO {

    @Override
    public boolean add(Teacher_Attend studentAttende) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO teacher_attendence (SId,Date,Time) " +
                "VALUES(?, ?, ?)", studentAttende.getTId(), studentAttende.getDate(), studentAttende.getTime());

    }

    @Override
    public boolean delete(Integer TId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM teacher_attendence WHERE SId = ?", TId);
    }

    @Override
    public ArrayList<Teacher_Attend> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher_Attend> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM teacher_attendence");
        while (rst.next()) {
            allCustomers.add(new Teacher_Attend(rst.getInt(1), rst.getDate(2), rst.getDouble(3)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Teacher_Attend dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Teacher_Attend search(Integer TId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM teacher_attendence WHERE TId = ?", TId);

        if (rst.next()) {
            return new Teacher_Attend(rst.getInt(1), rst.getDate(2), rst.getDouble(3));
        }
        return null;
    }
}