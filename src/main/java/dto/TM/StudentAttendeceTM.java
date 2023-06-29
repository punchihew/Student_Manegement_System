package dto.TM;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentAttendeceTM {

        private Integer SId;
        private Date Date;
        private Double Time;

}
