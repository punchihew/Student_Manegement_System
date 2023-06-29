package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDetailDto;
import dto.CourseDto;
import dto.StudentManageDto;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {

    ArrayList<StudentManageDto> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean saveStudent(StudentManageDto dto) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentManageDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(Integer id) throws SQLException, ClassNotFoundException;

    StudentManageDto searchStudent( Integer id) throws SQLException, ClassNotFoundException ;

    boolean saveStudentWithCourse(Student studentManage, CourseDetailDto courseDetailDto) throws SQLException;

}
