package bo.custom;

import bo.custom.impl.PaymentBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.PayemntDAO;
import dto.CourseDto;
import dto.PaymentDto;
import entity.Payemnt;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymntBOImpl implements PaymentBO {

    PayemntDAO payemntDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException {

        ArrayList<Payemnt> all = payemntDAO.getAll();
        ArrayList<PaymentDto> arrayList= new ArrayList<>();
        for (Payemnt c : all) {
            arrayList.add(new PaymentDto(c.getPay_Id(),c.getSId(),c.getCours_Id(),c.getFee(),c.getPay_Date()));
        }
        return arrayList;
    }

    @Override
    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return payemntDAO.add( new Payemnt(dto.getPay_Id(), dto.getSId(),dto.getCours_Id(),dto.getFee(),dto.getPay_Date()));

    }

    @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return payemntDAO.update( new Payemnt(dto.getPay_Id(), dto.getSId(),dto.getCours_Id(),dto.getFee(),dto.getPay_Date()));

    }

    @Override
    public boolean deletePayment(Integer id) throws SQLException, ClassNotFoundException {
        return payemntDAO.delete(id);
    }

    @Override
    public PaymentDto searchPayment(Integer id) throws SQLException, ClassNotFoundException {
        Payemnt i = payemntDAO.search(id);
        return new PaymentDto(i.getPay_Id(), i.getSId(), i.getCours_Id(), i.getFee(),i.getPay_Date());

    }
}
