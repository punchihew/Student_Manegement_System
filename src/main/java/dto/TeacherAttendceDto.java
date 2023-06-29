package dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherAttendceDto {
        private Integer TId;
        private Date Date;
        private Double Time;

    }


