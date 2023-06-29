package bo.custom.impl;

import bo.custom.SuperBO;
import dto.CourseDto;
import dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    ArrayList<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(Integer id) throws SQLException, ClassNotFoundException;

    PaymentDto searchPayment( Integer id) throws SQLException, ClassNotFoundException ;
}
