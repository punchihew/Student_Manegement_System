package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDetailDto {
    private int course_Id;
    private String sId;
    private String name;
    private String course_Name;
    private String date;

}
