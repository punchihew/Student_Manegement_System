package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.ExamDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamBO extends SuperBO {

    ArrayList<ExamDto> getAllExam() throws SQLException, ClassNotFoundException;

    boolean saveExam(ExamDto dto) throws SQLException, ClassNotFoundException;

    boolean updateExam(ExamDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteExam(Integer id) throws SQLException, ClassNotFoundException;

    ExamDto searchExam( Integer id) throws SQLException, ClassNotFoundException ;
}
