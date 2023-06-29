package entity;

import java.util.Date;

public class Registation {


    private Integer Reg_Id;
    private String Reg_Name;
    private String Reg_Contact;
    private Date Reg_Date;
    private String Reg_Address;

    public Registation(){}

    public Registation(Integer reg_Id, String reg_Name, String reg_Contact, Date reg_Date, String reg_Address) {
        Reg_Id = reg_Id;
        Reg_Name = reg_Name;
        Reg_Contact = reg_Contact;
        Reg_Date = reg_Date;
        Reg_Address = reg_Address;
    }

    public Integer getReg_Id() {
        return Reg_Id;
    }

    public void setReg_Id(Integer reg_Id) {
        Reg_Id = reg_Id;
    }

    public String getReg_Name() {
        return Reg_Name;
    }

    public void setReg_Name(String reg_Name) {
        Reg_Name = reg_Name;
    }

    public String getReg_Contact() {
        return Reg_Contact;
    }

    public void setReg_Contact(String reg_Contact) {
        Reg_Contact = reg_Contact;
    }

    public Date getReg_Date() {
        return Reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        Reg_Date = reg_Date;
    }

    public String getReg_Address() {
        return Reg_Address;
    }

    public void setReg_Address(String reg_Address) {
        Reg_Address = reg_Address;
    }


}
