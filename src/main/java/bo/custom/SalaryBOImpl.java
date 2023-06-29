package bo.custom;

import bo.custom.impl.SalaryBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.SalaryDAO;
import dto.CourseDto;
import dto.SalaryDto;
import entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryBO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);


    @Override
    public ArrayList<SalaryDto> getAllSalary() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> all = salaryBO.getAll();
        ArrayList<SalaryDto> arrayList= new ArrayList<>();
        for (Salary c : all) {
            arrayList.add(new SalaryDto(c.getTId(),c.getSalary_Id(),c.getFee(),c.getSDate()));
        }
        return arrayList;
    }

    @Override
    public boolean saveSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryBO.add( new Salary(dto.getTId(), dto.getSalary_Id(),dto.getFee(),dto.getSDate()));

    }

    @Override
    public boolean updateSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryBO.update( new Salary(dto.getTId(), dto.getSalary_Id(),dto.getFee(),dto.getSDate()));

    }

    @Override
    public boolean deleteSalary(Integer id) throws SQLException, ClassNotFoundException {
        return salaryBO.delete(id);
    }

    @Override
    public SalaryDto searchSalary(Integer id) throws SQLException, ClassNotFoundException {
        Salary i = salaryBO.search(id);
        return new SalaryDto(i.getTId(), i.getSalary_Id(), i.getFee(), i.getSDate());

    }
}
