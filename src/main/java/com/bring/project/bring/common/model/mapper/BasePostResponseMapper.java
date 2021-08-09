package com.bring.project.bring.common.model.mapper;

import com.bring.project.bring.common.model.response.BasePostResponse;
import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.entity.Customer;
import com.bring.project.bring.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class BasePostResponseMapper {
    public BasePostResponse toBaseResponse(Customer customer) {
        BasePostResponse basePostResponse = new BasePostResponse();
        basePostResponse.setName(customer.getName());
        basePostResponse.setId(String.valueOf(customer.getId()));
        return basePostResponse;
    }

    public BasePostResponse toBaseResponse(Book book) {
        BasePostResponse basePostResponse = new BasePostResponse();
        basePostResponse.setName(book.getBookName());
        basePostResponse.setId(String.valueOf(book.getId()));
        return basePostResponse;
    }

    public BasePostResponse toBaseResponse(Order order) {
        BasePostResponse basePostResponse = new BasePostResponse();
        basePostResponse.setId(String.valueOf(order.getId()));
        return basePostResponse;
    }

}
