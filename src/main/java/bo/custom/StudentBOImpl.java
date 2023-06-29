package bo.custom;

import bo.custom.impl.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAOImpl;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.StudentAttendenceDAO;
import dao.custom.impl.StudentDAO;
import db.DBConnection;
import dto.CourseDetailDto;
import dto.CourseDto;
import dto.StudentManageDto;
import entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {


    StudentDAO studentDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public ArrayList<StudentManageDto> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentManageDto> arrayList= new ArrayList<>();
        for (Student c : all) {
            arrayList.add(new StudentManageDto(c.getSId(),c.getSName(),c.getSNddress(),c.getSContact(),c.getSEmail()));
        }
        return arrayList;
    }

    @Override
    public boolean saveStudent(StudentManageDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.add( new Student(dto.getSId(), dto.getSName(),dto.getSNddress(),dto.getSContact(),dto.getSEmail()));

    }

    @Override
    public boolean updateStudent(StudentManageDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update( new Student(dto.getSId(), dto.getSName(),dto.getSNddress(),dto.getSContact(),dto.getSEmail()));

    }

    @Override
    public boolean deleteStudent(Integer id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public StudentManageDto searchStudent(Integer id) throws SQLException, ClassNotFoundException {
        Student i = studentDAO.search(id);
        return new StudentManageDto(i.getSId(), i.getSName(), i.getSNddress(), i.getSContact(),i.getSEmail());
    }

    @Override
    public boolean saveStudentWithCourse(Student studentManage, CourseDetailDto courseDetailDto) throws SQLException {
        Connection connection = null;
        try {
             connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if(!studentDAO.add(studentManage)){
                connection.rollback();
                connection.setAutoCommit(true);
                return  false;
            }

            if (!courseDAO.addCourseDetail(courseDetailDto)){
                connection.rollback();
                connection.setAutoCommit(true);
                return  false;
            }
            connection.commit();
            connection.setAutoCommit(true);

            return true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
             connection.setAutoCommit(true);
        }
        return false;
    }


}

