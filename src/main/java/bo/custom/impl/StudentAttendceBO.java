package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.StudentAttendeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentAttendceBO extends SuperBO {

    ArrayList<StudentAttendeDto> getAllStudentAttendc() throws SQLException, ClassNotFoundException;

    boolean saveStudentAttendc(StudentAttendeDto dto) throws SQLException, ClassNotFoundException;

    boolean updateStudentAttendc(StudentAttendeDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudentAttendc(Integer id) throws SQLException, ClassNotFoundException;

    StudentAttendeDto searchStudentAttendc( Integer id) throws SQLException, ClassNotFoundException ;
}
