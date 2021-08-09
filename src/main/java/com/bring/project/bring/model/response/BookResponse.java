package com.bring.project.bring.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookResponse {
    private Integer id;
    private String bookName;
    private String writer;
    private String category;
    private Date createdDate;
    private Date lastModifiedDate;
    private Integer quantityInStock;
}
