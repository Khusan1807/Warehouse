package uz.pdp.dto;

import lombok.Data;

@Data
public class InputDto {

    private Long id;
    private Long warehouseId;
    private Long supplierId;
    private Long currencyId;
    private String factureNumber;
    private String code;
}
