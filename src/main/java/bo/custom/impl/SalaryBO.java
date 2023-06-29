package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {

    ArrayList<SalaryDto> getAllSalary() throws SQLException, ClassNotFoundException;

    boolean saveSalary(SalaryDto dto) throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSalary(Integer id) throws SQLException, ClassNotFoundException;

    SalaryDto searchSalary( Integer id) throws SQLException, ClassNotFoundException ;
}
