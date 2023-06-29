package dao.custom;

import dao.custom.impl.CourseDAO;
import dao.custom.impl.util.SQLUtil;
import db.DBConnection;
import dto.CourseDetailDto;
import dto.CourseDto;
import dto.PaymentDto;
import entity.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {


    @Override
    public boolean add(Course courseDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO course (Coure_Id, TId ,Coure_Name,SId,Coure_Date) \\\" +\\n\" +\n" +
                "\\\"VALUES(?, ?, ?, ?,?)", courseDto.getCoure_Id(),courseDto.getTId(),courseDto.getCoure_Name(),courseDto.getSId(),courseDto.getCoure_Date());

    }

    @Override
    public boolean delete(Integer Coure_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM course WHERE Coure_Id = ?", Coure_Id);
    }

    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Course> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM course");
        while (rst.next()) {
            allCustomers.add(new Course(rst.getInt(1), rst.getInt(2),rst.getString(3),rst.getInt(4),rst.getString(5)));


        }
        return allCustomers;
    }

    @Override
    public boolean update(Course dto) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public Course search(Integer Coure_Id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM course WHERE Coure_Id = ?" ,Coure_Id);

        if (rst.next()) {
            return new Course(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getInt(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean addCourseDetail(CourseDetailDto courseDetailDto) throws SQLException {

        Connection con =DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO course_student_dis VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, courseDetailDto.getCourse_Id());
        pstm.setInt(2, Integer.parseInt(courseDetailDto.getSId()));
        pstm.setString(3, courseDetailDto.getCourse_Name());
        pstm.setString(4, courseDetailDto.getDate());

        return pstm.executeUpdate() > 0;
    }
}
