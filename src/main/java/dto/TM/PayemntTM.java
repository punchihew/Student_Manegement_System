package dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PayemntTM {

    private Integer Pay_Id;
    private Integer SId;
    private Integer Cours_Id;
    private Integer Fee;
    private Date Pay_Date;

 }




