package entity;

public class Teacher {

    private Integer TId;
    private String TName;
    private String TAddress;
    private Integer TContact;
    private String TEmail;

    public Teacher(){}
    public Teacher(Integer TId, String TName, String TAddress, Integer TContact, String TEmail) {
        this.TId = TId;
        this.TName = TName;
        this.TAddress = TAddress;
        this.TContact = TContact;
        this.TEmail = TEmail;
    }

    public Integer getTId() {
        return TId;
    }

    public void setTId(Integer TId) {
        this.TId = TId;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getTAddress() {
        return TAddress;
    }

    public void setTAddress(String TAddress) {
        this.TAddress = TAddress;
    }

    public Integer getTContact() {
        return TContact;
    }

    public void setTContact(Integer TContact) {
        this.TContact = TContact;
    }

    public String getTEmail() {
        return TEmail;
    }

    public void setTEmail(String TEmail) {
        this.TEmail = TEmail;
    }
}
