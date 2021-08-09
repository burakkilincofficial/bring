package com.bring.project.bring.model.response;

import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.entity.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class AllOrderResponse {
    private Integer id;
    private Customer customer;
    private Date lastModifiedDate;
    private Date createdDate;
    private List<Book> bookList;
}
