package dao.custom;

import dao.custom.impl.ExamDAO;
import dao.custom.impl.util.SQLUtil;
import dto.ExamDto;
import entity.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDAOImpl implements ExamDAO {

   @Override
    public boolean add(Exam exam) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO exam (SId,Coure_Id,Name,Subject,Marks) \" +\n" +
                "\"VALUES(?, ?, ?, ?,?)", exam.getSId(),exam.getCoure_Id(),exam.getName(),exam.getSubject(),exam.getMarks());

    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Exam> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Exam> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM exam");
        while (rst.next()) {
            allCustomers.add(new Exam(rst.getInt(1), rst.getInt(2),rst.getString(3),rst.getString(4),rst.getInt(5)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Exam dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Exam search(Integer SID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM exam  WHERE SId = ?" ,SID);

        if (rst.next()) {
            return new Exam(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getString(4), rst.getInt(5));
        }
        return null;
    }


}
