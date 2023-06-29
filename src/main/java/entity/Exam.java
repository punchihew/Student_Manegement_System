package entity;

public class Exam {

    private Integer SId;
    private Integer Coure_Id;
    private String Name;
    private String Subject;
    private Integer Marks;

    public Exam(){


     }
    public Exam(Integer SId, Integer coure_Id, String name, String subject, Integer marks) {
        this.SId = SId;
        Coure_Id = coure_Id;
        Name = name;
        Subject = subject;
        Marks = marks;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public Integer getCoure_Id() {
        return Coure_Id;
    }

    public void setCoure_Id(Integer coure_Id) {
        Coure_Id = coure_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Integer getMarks() {
        return Marks;
    }

    public void setMarks(Integer marks) {
        Marks = marks;
    }


}
