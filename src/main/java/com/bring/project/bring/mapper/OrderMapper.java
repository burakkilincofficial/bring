package com.bring.project.bring.mapper;

import com.bring.project.bring.model.dto.BuyBookDTO;
import com.bring.project.bring.model.dto.OrderDTO;
import com.bring.project.bring.model.entity.Order;
import com.bring.project.bring.model.response.OrderResponse;
import com.bring.project.bring.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OrderMapper {
    private final CustomerService customerService;

    public OrderMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Order saveDtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setValid(orderDTO.getIsValid());

        order.setCustomer(customerService.findById(orderDTO.getCustomerId()));
        return order;
    }

    public OrderResponse entityToDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .books(order.getBooksOfOrder())
                .customer(order.getCustomer())
                .isValid(order.getValid())
                .createdDate(order.getCreatedDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .build();
    }
}
