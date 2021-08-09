package com.bring.project.bring.service;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.NotEnoughAmountBookInEntityException;
import com.bring.project.bring.error.exception.WrongAmountException;
import com.bring.project.bring.model.dto.OrderDTO;
import com.bring.project.bring.model.dto.UpdateOrderQuantityInStock;
import com.bring.project.bring.model.entity.Order;
import com.bring.project.bring.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    PaginatedApiResponse<List<OrderResponse>> getAllPageableOrder(AppPage appPage);

    List<OrderResponse> getAllOrders();

    Order findById(Integer id);

    Order save(OrderDTO orderDTO) throws NotEnoughAmountBookInEntityException, WrongAmountException;

    void delete(Integer id);

    void update(OrderDTO orderUpdateDTO) throws WrongAmountException;

    Order updateQuantityInStock(UpdateOrderQuantityInStock updateBookQuantityInStock);

    Order orderById(Integer id);
}
