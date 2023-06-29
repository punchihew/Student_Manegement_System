package dto;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TeacherManageDto {

        private Integer TId;
        private String TName;
        private String TAddress;
        private Integer TContact;
        private String TEmail;

    }
