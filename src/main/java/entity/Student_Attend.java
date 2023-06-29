package entity;

import java.util.Date;

public class Student_Attend {

    private Integer SId;
    private java.util.Date Date;
    private Double Time;

    public Student_Attend(){}
    public Student_Attend(Integer TId, java.util.Date date, Double time) {
        this.SId = TId;
        Date = date;
        Time = time;
    }

    public Integer getTId() {
        return SId;
    }

    public void setTId(Integer SId) {
        this.SId = SId;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Double getTime() {
        return Time;
    }

    public void setTime(Double time) {
        Time = time;
    }
}
