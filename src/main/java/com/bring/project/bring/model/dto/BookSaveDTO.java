package com.bring.project.bring.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookSaveDTO {
    private String bookName;
    private String writer;
    private String category;
    @NotNull
    private Integer quantityInStock;
    @NotNull
    private Double unitPrice;
}
