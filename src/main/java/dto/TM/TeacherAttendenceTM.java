package dto.TM;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherAttendenceTM {

    private Integer TId;
    private Date Date;
    private Double Time;

    }
