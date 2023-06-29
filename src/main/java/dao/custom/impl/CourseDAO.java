package dao.custom.impl;

import dao.CrudDAO;
import dto.CourseDetailDto;
import dto.CourseDto;
import entity.Course;

import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course,Integer> {

    boolean addCourseDetail(CourseDetailDto courseDetailDto) throws SQLException;
}
