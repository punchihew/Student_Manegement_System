package bo.custom;

import bo.custom.impl.TeacherBO;
import dao.DAOFactory;
import dao.custom.impl.StudentAttendenceDAO;
import dao.custom.impl.TeacherDAO;
import dto.CourseDto;
import dto.TeacherManageDto;
import entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherBOImpl implements TeacherBO {

    TeacherDAO teacherDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);

    @Override
    public ArrayList<TeacherManageDto> getAllTeacher() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> all = teacherDAO.getAll();
        ArrayList<TeacherManageDto> arrayList= new ArrayList<>();
        for (Teacher c : all) {
            arrayList.add(new TeacherManageDto(c.getTId(),c.getTName(),c.getTAddress(),c.getTContact(),c.getTEmail()));
        }
        return arrayList;
    }

    @Override
    public boolean saveTeacher(TeacherManageDto dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.add( new Teacher(dto.getTId(),dto.getTName(),dto.getTAddress(),dto.getTContact(),dto.getTEmail()));

    }

    @Override
    public boolean updateTeacher(TeacherManageDto dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.update( new Teacher(dto.getTId(), dto.getTName(),dto.getTAddress(),dto.getTContact(),dto.getTEmail()));

    }

    @Override
    public boolean deleteTeacher(Integer id) throws SQLException, ClassNotFoundException {
        return teacherDAO.delete(id);
    }

    @Override
    public TeacherManageDto searchTeacher(Integer id) throws SQLException, ClassNotFoundException {
        Teacher i = teacherDAO.search(id);
        return new TeacherManageDto(i.getTId(), i.getTName(), i.getTAddress(), i.getTContact(),i.getTEmail());

    }
}
