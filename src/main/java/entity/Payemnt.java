package entity;

import java.util.Date;

public class Payemnt {

    private Integer Pay_Id;
    private Integer SId;
    private Integer Cours_Id;
    private Integer Fee;
    private Date Pay_Date;

    public Payemnt(){}

    public Payemnt(Integer pay_Id, Integer SId, Integer cours_Id, Integer fee, Date pay_Date) {
        Pay_Id = pay_Id;
        this.SId = SId;
        Cours_Id = cours_Id;
        Fee = fee;
        Pay_Date = pay_Date;
    }

    public Integer getPay_Id() {
        return Pay_Id;
    }

    public void setPay_Id(Integer pay_Id) {
        Pay_Id = pay_Id;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public Integer getCours_Id() {
        return Cours_Id;
    }

    public void setCours_Id(Integer cours_Id) {
        Cours_Id = cours_Id;
    }

    public Integer getFee() {
        return Fee;
    }

    public void setFee(Integer fee) {
        Fee = fee;
    }

    public Date getPay_Date() {
        return Pay_Date;
    }

    public void setPay_Date(Date pay_Date) {
        Pay_Date = pay_Date;
    }





}
