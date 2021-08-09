package com.bring.project.bring.model.dto;

import lombok.Data;

@Data
public class BookSaveDTO {
    private String bookName;
    private String writer;
    private String category;
    private Integer quantityInStock;
    private Double unitPrice;
}
