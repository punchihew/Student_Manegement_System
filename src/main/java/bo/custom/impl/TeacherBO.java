package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.TeacherManageDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherBO extends SuperBO {

    ArrayList<TeacherManageDto> getAllTeacher() throws SQLException, ClassNotFoundException;

    boolean saveTeacher(TeacherManageDto dto) throws SQLException, ClassNotFoundException;

    boolean updateTeacher(TeacherManageDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTeacher(Integer id) throws SQLException, ClassNotFoundException;

    TeacherManageDto searchTeacher( Integer id) throws SQLException, ClassNotFoundException ;
}
