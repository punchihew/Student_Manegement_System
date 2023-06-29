package dto.TM;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseTM {

    private Integer Coure_Id;
    private Integer TId;
    private String Coure_Name;
    private Integer SId;
    private String Coure_Date;

    }
