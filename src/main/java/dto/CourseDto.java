package dto;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDto {

    private Integer Coure_Id;
    private Integer TId;
    private String Coure_Name;
    private Integer SId;
    private String Coure_Date;

}

