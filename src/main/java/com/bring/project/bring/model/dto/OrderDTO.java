package com.bring.project.bring.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private Boolean isValid;
    private Integer customerId;
    private List<BuyBookDTO> bookIds;
}
