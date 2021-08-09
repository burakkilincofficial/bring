package com.bring.project.bring.model.dto;

import lombok.Data;

@Data
public class UpdateBookQuantityInStock {
    private Integer id;
    private Integer newQuantityInStock;
}
