package entity;

public class Student {


    private Integer SId;
    private String SName;
    private String SNddress;
    private Integer SContact;
    private String SEmail;

    public Student(){}

    public Student(Integer SId, String SName, String SNddress, Integer SContact, String SEmail) {
        this.SId = SId;
        this.SName = SName;
        this.SNddress = SNddress;
        this.SContact = SContact;
        this.SEmail = SEmail;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSNddress() {
        return SNddress;
    }

    public void setSNddress(String SNddress) {
        this.SNddress = SNddress;
    }

    public Integer getSContact() {
        return SContact;
    }

    public void setSContact(Integer SContact) {
        this.SContact = SContact;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }
}
