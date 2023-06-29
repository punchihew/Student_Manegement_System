package dao;

import dao.custom.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT, TEACHER, COURSE, EXAM, PAYMENT , REGISTATION ,SALARY,TEACHER_ATTENDECE,STUDENT_ATTENDECE
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case TEACHER:
                return (T) new TeacherDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case EXAM:
                return (T) new ExamDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case REGISTATION:
                return (T) new RegistationDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            case TEACHER_ATTENDECE:
                return (T) new TeacherAttendenceDAOImpl();
            case STUDENT_ATTENDECE:
                return (T) new StudentAttendenceDAIImpl();
            default:
                return null;
        }
    }
}
