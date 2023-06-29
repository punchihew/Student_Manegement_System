package bo.custom;

import bo.custom.impl.CourseBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dto.CourseDto;
import entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {


    CourseDAO courseDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public ArrayList<CourseDto> getAllCourse() throws SQLException, ClassNotFoundException {
        ArrayList<Course> all = courseDAO.getAll();
        ArrayList<CourseDto> arrayList= new ArrayList<>();
        for (Course c : all) {
            arrayList.add(new CourseDto(c.getCoure_Id(),c.getTId(),c.getCoure_Name(),c.getSId(),c.getCoure_Date()));
        }
        return arrayList;
    }

    @Override
    public boolean saveCourse(CourseDto dto) throws SQLException, ClassNotFoundException {
        return courseDAO.add( new Course(dto.getCoure_Id(), dto.getTId(),dto.getCoure_Name(),dto.getSId(),dto.getCoure_Date()));
    }

    @Override
    public boolean updateCourse(CourseDto dto) throws SQLException, ClassNotFoundException {
        return courseDAO.update( new Course(dto.getCoure_Id(), dto.getTId(),dto.getCoure_Name(),dto.getSId(),dto.getCoure_Date()));
    }

    @Override
    public boolean deleteCourse(Integer id) throws SQLException, ClassNotFoundException {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDto searchCourse(Integer id) throws SQLException, ClassNotFoundException {
        Course i = courseDAO.search(id);
        return new CourseDto(i.getCoure_Id(), i.getTId(), i.getCoure_Name(), i.getSId(),i.getCoure_Date());
    }
}
