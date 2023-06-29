package entity;

import java.util.Date;

public class Salary {

    private Integer TId;
    private Integer Salary_Id;
    private Double Fee;
    private Date SDate;

    public Salary(){}

    public Salary(Integer TId, Integer salary_Id, Double fee, Date SDate) {
        this.TId = TId;
        Salary_Id = salary_Id;
        Fee = fee;
        this.SDate = SDate;
    }

    public Integer getTId() {
        return TId;
    }

    public void setTId(Integer TId) {
        this.TId = TId;
    }

    public Integer getSalary_Id() {
        return Salary_Id;
    }

    public void setSalary_Id(Integer salary_Id) {
        Salary_Id = salary_Id;
    }

    public Double getFee() {
        return Fee;
    }

    public void setFee(Double fee) {
        Fee = fee;
    }

    public Date getSDate() {
        return SDate;
    }

    public void setSDate(Date SDate) {
        this.SDate = SDate;
    }
}
