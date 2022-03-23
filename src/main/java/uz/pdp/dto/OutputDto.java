package uz.pdp.dto;

import lombok.Data;

@Data
public class OutputDto {

    private Long id;
    private Long  warehouseId;
    private Long  clientId;
    private Long  currencyId;
    private String factureNumber;
    private String code;
}
