package dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentAttendeDto {


    private Integer SId;
    private Date Date;
    private Double Time;

}
