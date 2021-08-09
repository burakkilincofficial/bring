package com.bring.project.bring.model.response;

import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.entity.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderResponse {
    private Integer id;
    private Boolean isValid;
    private List<Book> books = new ArrayList<>();
    private Customer customer;
    private Date createdDate;
    private Date lastModifiedDate;
}
