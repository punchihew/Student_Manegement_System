package bo.custom;

import bo.custom.impl.StudentAttendceBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.StudentAttendenceDAO;
import dto.CourseDto;
import dto.StudentAttendeDto;
import entity.Student_Attend;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAttendceBOImpl implements StudentAttendceBO {

    StudentAttendenceDAO studentAttendenceDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT_ATTENDECE);

    @Override
    public ArrayList<StudentAttendeDto> getAllStudentAttendc() throws SQLException, ClassNotFoundException {
        ArrayList<Student_Attend> all = studentAttendenceDAO.getAll();
        ArrayList<StudentAttendeDto> arrayList= new ArrayList<>();
        for (Student_Attend c : all) {
            arrayList.add(new StudentAttendeDto(c.getTId(),c.getDate(),c.getTime()));
        }
        return arrayList;
    }

    @Override
    public boolean saveStudentAttendc(StudentAttendeDto dto) throws SQLException, ClassNotFoundException {
        return studentAttendenceDAO.add( new Student_Attend(dto.getSId(), dto.getDate(),dto.getTime()));

    }

    @Override
    public boolean updateStudentAttendc(StudentAttendeDto dto) throws SQLException, ClassNotFoundException {
        return studentAttendenceDAO.update( new Student_Attend(dto.getSId(), dto.getDate(),dto.getTime()));

    }

    @Override
    public boolean deleteStudentAttendc(Integer id) throws SQLException, ClassNotFoundException {
        return studentAttendenceDAO.delete(id);
    }

    @Override
    public StudentAttendeDto searchStudentAttendc(Integer id) throws SQLException, ClassNotFoundException {
        Student_Attend i = studentAttendenceDAO.search(id);
        return new StudentAttendeDto(i.getTId(), i.getDate(), i.getTime());

    }
}
