package entity;

public class Course {

    private Integer Coure_Id;
    private Integer TId;
    private String Coure_Name;
    private Integer SId;
    private String Coure_Date;

    public Course() {

    }

    public Course(Integer coure_Id, Integer TId, String coure_Name, Integer SId, String coure_Date) {
        Coure_Id = coure_Id;
        this.TId = TId;
        Coure_Name = coure_Name;
        this.SId = SId;
        Coure_Date = coure_Date;
    }

    public String getCoure_Name() {
        return Coure_Name;
    }

    public void setCoure_Name(String coure_Name) {
        Coure_Name = coure_Name;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public String getCoure_Date() {
        return Coure_Date;
    }

    public void setCoure_Date(String coure_Date) {
        Coure_Date = coure_Date;
    }


    public Integer getTId() {
        return TId;
    }

    public void setTId(Integer TId) {
        this.TId = TId;
    }

    public Integer getCoure_Id() {
        return Coure_Id;
    }

    public void setCoure_Id(Integer coure_Id) {
        Coure_Id = coure_Id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Coure_Id=" + Coure_Id +
                ", TId=" + TId +
                ", Coure_Name='" + Coure_Name + '\'' +
                ", SId=" + SId +
                ", Coure_Date='" + Coure_Date + '\'' +
                '}';
    }
}
