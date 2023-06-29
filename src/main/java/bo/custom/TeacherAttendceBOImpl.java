package bo.custom;

import bo.custom.impl.TeacherAttendceBO;
import dao.DAOFactory;
import dao.custom.impl.StudentAttendenceDAO;
import dao.custom.impl.TeacherAttendenceDAO;
import dto.CourseDto;
import dto.TeacherAttendceDto;
import entity.Teacher_Attend;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherAttendceBOImpl implements TeacherAttendceBO {

    TeacherAttendenceDAO teacherAttendenceDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER_ATTENDECE);

    @Override
    public ArrayList<TeacherAttendceDto> getAllTeacherAttendce() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher_Attend> all = teacherAttendenceDAO.getAll();
        ArrayList<TeacherAttendceDto> arrayList= new ArrayList<>();
        for (Teacher_Attend c : all) {
            arrayList.add(new TeacherAttendceDto(c.getTId(),c.getDate(),c.getTime()));
        }
        return arrayList;
    }

    @Override
    public boolean saveTeacherAttendce(TeacherAttendceDto dto) throws SQLException, ClassNotFoundException {
        return teacherAttendenceDAO.add( new Teacher_Attend(dto.getTId(), dto.getDate(),dto.getTime()));

    }

    @Override
    public boolean updateTeacherAttendce(TeacherAttendceDto dto) throws SQLException, ClassNotFoundException {
        return teacherAttendenceDAO.update( new Teacher_Attend(dto.getTId(), dto.getDate(),dto.getTime()));

    }

    @Override
    public boolean deleteTeacherAttendce(Integer id) throws SQLException, ClassNotFoundException {
        return teacherAttendenceDAO.delete(id);
    }

    @Override
    public TeacherAttendceDto searchTeacherAttendce(Integer id) throws SQLException, ClassNotFoundException {
        Teacher_Attend i = teacherAttendenceDAO.search(id);
        return new TeacherAttendceDto(i.getTId(), i.getDate(), i.getTime());

    }
}
