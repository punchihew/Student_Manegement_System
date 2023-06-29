package dto;

import lombok.*;

import java.util.Date;

import lombok.*;
@Data
@AllArgsConstructor
public class PaymentDto {
        private Integer Pay_Id;
        private Integer SId;
        private Integer Cours_Id;
        private Integer Fee;
        private Date Pay_Date;

    }


