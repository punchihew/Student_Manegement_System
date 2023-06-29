package dto.TM;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalaryTM {

    private Integer TId;
    private Integer Salary_Id;
    private Double Fee;
    private Date SDate;

}
