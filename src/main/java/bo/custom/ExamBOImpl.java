package bo.custom;

import bo.custom.impl.ExamBO;
import dao.DAOFactory;
import dao.custom.impl.CourseDAO;
import dao.custom.impl.ExamDAO;
import dto.CourseDto;
import dto.ExamDto;
import entity.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamBOImpl implements ExamBO {

    ExamDAO examDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EXAM);

    @Override
    public ArrayList<ExamDto> getAllExam() throws SQLException, ClassNotFoundException {

        ArrayList<Exam> all = examDAO.getAll();
        ArrayList<ExamDto> arrayList= new ArrayList<>();
        for (Exam c : all) {
            arrayList.add(new ExamDto(c.getSId(),c.getCoure_Id(),c.getName(),c.getSubject(),c.getMarks()));
        }
        return arrayList;
    }

    @Override
    public boolean saveExam(ExamDto dto) throws SQLException, ClassNotFoundException {
        return examDAO.add( new Exam(dto.getSId(), dto.getCoure_Id(),dto.getName(),dto.getSubject(),dto.getMarks()));

    }

    @Override
    public boolean updateExam(ExamDto dto) throws SQLException, ClassNotFoundException {
        return examDAO.update( new Exam(dto.getSId(), dto.getCoure_Id(),dto.getName(),dto.getSubject(),dto.getMarks()));

    }

    @Override
    public boolean deleteExam(Integer id) throws SQLException, ClassNotFoundException {
        return examDAO.delete(id);
    }

    @Override
    public ExamDto searchExam(Integer id) throws SQLException, ClassNotFoundException {
        Exam i = examDAO.search(id);
        return new ExamDto(i.getSId(), i.getCoure_Id(), i.getName(), i.getSubject(),i.getMarks());

    }
}
