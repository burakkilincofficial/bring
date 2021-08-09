package com.bring.project.bring.model.dto;

import lombok.Data;

@Data
public class BookUpdateDTO {
    private Integer id;
    private String bookName;
    private String writer;
    private String category;
    private Double unitPrice;
}
