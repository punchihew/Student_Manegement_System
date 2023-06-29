package entity;

import java.util.Date;

public class Teacher_Attend {

    private Integer TId;
    private java.util.Date Date;
    private Double Time;

    public Teacher_Attend(){}
    public Teacher_Attend(Integer TId, java.util.Date date, Double time) {
        this.TId = TId;
        Date = date;
        Time = time;
    }

    public Integer getTId() {
        return TId;
    }

    public void setTId(Integer TId) {
        this.TId = TId;
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
