package uz.pdp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {

    private Long id;
    private Double price;
    private Double amount;
    private Date expireDate;
    private Long productId;
    private Long inputId;

}
