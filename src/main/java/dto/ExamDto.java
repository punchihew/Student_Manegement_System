package dto;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExamDto {

        private Integer SId;
        private Integer Coure_Id;
        private String Name;
        private String Subject;
        private Integer Marks;

    }
