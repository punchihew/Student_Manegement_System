package dao.custom;

import dao.custom.impl.StudentAttendenceDAO;
import dao.custom.impl.util.SQLUtil;
import dto.StudentAttendeDto;
import entity.Student_Attend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAttendenceDAIImpl implements StudentAttendenceDAO {
    @Override
    public boolean add(Student_Attend studentAttende) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO student_attendence (SId,Date,Time) \" +\n" +
                " \"VALUES(?, ?, ?)", studentAttende.getTId(),studentAttende.getDate(),studentAttende.getTime());

    }

    @Override
    public boolean delete(Integer SId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM student_attendence WHERE SId = ?", SId);
    }

    @Override
    public ArrayList<Student_Attend> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student_Attend> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM student_attendence");
        while (rst.next()) {
            allCustomers.add(new Student_Attend(rst.getInt(1), rst.getDate(2),rst.getDouble(3)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Student_Attend dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Student_Attend search(Integer SId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM student_attendence WHERE SId = ?" ,SId);

        if (rst.next()) {
            return new Student_Attend(rst.getInt(1), rst.getDate(2), rst.getDouble(3));
        }
        return null;
    }


}
