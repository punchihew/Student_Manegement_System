package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseBO extends SuperBO {

    ArrayList<CourseDto> getAllCourse() throws SQLException, ClassNotFoundException;

    boolean saveCourse(CourseDto dto) throws SQLException, ClassNotFoundException;

    boolean updateCourse(CourseDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCourse(Integer id) throws SQLException, ClassNotFoundException;

    CourseDto searchCourse( Integer id) throws SQLException, ClassNotFoundException ;
}
