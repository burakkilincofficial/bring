package com.bring.project.bring.model.dto;

import lombok.Data;

@Data
public class UpdateOrderQuantityInStock {
    private Integer id;
    private Integer newQuantityStockRecord;
}
