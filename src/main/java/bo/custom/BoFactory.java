package bo.custom;

import bo.custom.impl.StudentBO;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        Course_BO, Exam_BO, Payment_BO,Registation_BO,Salary_BO,Student_Attendce_BO,Student_BO,Teacher_Attendce_BO,Teacher_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case Course_BO:
                return (T) new CourseBOImpl();
            case Exam_BO:
                return (T) new ExamBOImpl();
            case Payment_BO:
                return (T) new PaymntBOImpl();
            case Registation_BO:
                return (T) new RegistationBOImpl();
            case Salary_BO:
                return (T) new SalaryBOImpl();
            case Student_Attendce_BO:
                return (T) new StudentAttendceBOImpl();
            case Student_BO:
                return (T) new StudentBOImpl();
            case Teacher_Attendce_BO:
                return (T) new TeacherAttendceBOImpl();
            case Teacher_BO:
                return (T) new TeacherBOImpl();
            default:
                return null;
        }
    }
}
