package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.RegistrationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegistationBO extends SuperBO {

    ArrayList<RegistrationDto> getAllRegistatio() throws SQLException, ClassNotFoundException;

    boolean saveRegistatio(RegistrationDto dto) throws SQLException, ClassNotFoundException;

    boolean updateRegistatio(RegistrationDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteRegistatio(Integer id) throws SQLException, ClassNotFoundException;

    RegistrationDto searchRegistatio( Integer id) throws SQLException, ClassNotFoundException ;
}
