package uz.pdp.dto;

import lombok.Data;

@Data
public class OutputProductDto {

    private Long id;
    private Double price;
    private Double amount;
    private Long productId;
    private Long outputId;

}
