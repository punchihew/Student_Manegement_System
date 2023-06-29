package dto;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class RegistrationDto {

    private Integer Reg_Id;
    private String Reg_Name;
    private String Reg_Contact;
    private Date Reg_Date;
    private String Reg_Address;
}
