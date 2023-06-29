package bo.custom;

import bo.custom.impl.RegistationBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.RegistationDAO;
import dto.CourseDto;
import dto.RegistrationDto;
import entity.Registation;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegistationBOImpl implements RegistationBO {

    RegistationDAO registationDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTATION);

    @Override
    public ArrayList<RegistrationDto> getAllRegistatio() throws SQLException, ClassNotFoundException {
        ArrayList<Registation> all = registationDAO.getAll();
        ArrayList<RegistrationDto> arrayList= new ArrayList<>();
        for (Registation c : all) {
            arrayList.add(new RegistrationDto(c.getReg_Id(),c.getReg_Name(),c.getReg_Contact(),c.getReg_Date(),c.getReg_Address()));
        }
        return arrayList;
    }

    @Override
    public boolean saveRegistatio(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return registationDAO.add( new Registation(dto.getReg_Id(), dto.getReg_Name(),dto.getReg_Contact(),dto.getReg_Date(),dto.getReg_Address()));

    }

    @Override
    public boolean updateRegistatio(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return registationDAO.update( new Registation(dto.getReg_Id(), dto.getReg_Name(),dto.getReg_Contact(),dto.getReg_Date(),dto.getReg_Address()));

    }

    @Override
    public boolean deleteRegistatio(Integer id) throws SQLException, ClassNotFoundException {
        return registationDAO.delete(id);
    }

    @Override
    public RegistrationDto searchRegistatio(Integer id) throws SQLException, ClassNotFoundException {
        Registation i = registationDAO.search(id);
        return new RegistrationDto(i.getReg_Id(), i.getReg_Name(), i.getReg_Contact(), i.getReg_Date(),i.getReg_Address());

    }
}
