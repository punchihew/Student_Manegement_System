package dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalaryDto {

    private Integer TId;
    private Integer Salary_Id;
    private Double Fee;
    private Date SDate;

}
