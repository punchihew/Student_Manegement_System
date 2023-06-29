package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.TeacherAttendceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherAttendceBO extends SuperBO {

    ArrayList<TeacherAttendceDto> getAllTeacherAttendce() throws SQLException, ClassNotFoundException;

    boolean saveTeacherAttendce(TeacherAttendceDto dto) throws SQLException, ClassNotFoundException;

    boolean updateTeacherAttendce(TeacherAttendceDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTeacherAttendce(Integer id) throws SQLException, ClassNotFoundException;

    TeacherAttendceDto searchTeacherAttendce( Integer id) throws SQLException, ClassNotFoundException ;
}
