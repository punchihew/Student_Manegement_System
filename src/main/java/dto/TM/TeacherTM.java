package dto.TM;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TeacherTM {

    private Integer TId;
    private String TName;
    private String TAddress;
    private Integer TContact;
    private String TEmail;

    }
