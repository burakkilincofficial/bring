package com.bring.project.bring.controller;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.mapper.BasePostResponseMapper;
import com.bring.project.bring.common.model.response.BasePostResponse;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.NotEnoughAmountBookInEntityException;
import com.bring.project.bring.error.exception.OrderDBException;
import com.bring.project.bring.error.exception.OrderEntityNotFoundException;
import com.bring.project.bring.error.exception.WrongAmountException;
import com.bring.project.bring.mapper.OrderMapper;
import com.bring.project.bring.model.dto.OrderDTO;
import com.bring.project.bring.model.dto.UpdateOrderQuantityInStock;
import com.bring.project.bring.model.entity.Order;
import com.bring.project.bring.model.response.OrderResponse;
import com.bring.project.bring.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderControllerService {
    private final OrderService orderService;
    private final BasePostResponseMapper basePostResponseMapper;
    private final OrderMapper orderMapper;

    public OrderControllerService(OrderService orderService, BasePostResponseMapper basePostResponseMapper, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.basePostResponseMapper = basePostResponseMapper;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<PaginatedApiResponse<List<OrderResponse>>> getOrderListPageable(AppPage appPage) {
        return new ResponseEntity<>(orderService.getAllPageableOrder(appPage), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrderList() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasePostResponse> save(@RequestBody @Valid OrderDTO orderDTO) throws NotEnoughAmountBookInEntityException, WrongAmountException {
        return new ResponseEntity<>(basePostResponseMapper.toBaseResponse(orderService.save(orderDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(orderMapper.entityToDto(order), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody @Valid OrderDTO orderDTO) throws WrongAmountException {
        orderService.update(orderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/quantity")
    public ResponseEntity<Object> updateQuantityInStock(@RequestBody UpdateOrderQuantityInStock updateBookQuantityInStock) {
        orderService.updateQuantityInStock(updateBookQuantityInStock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler({NotEnoughAmountBookInEntityException.class})
    public ResponseEntity<Object> handleNotEnoughAmountException(NotEnoughAmountBookInEntityException exception) {
        log.error("OrderControllerService-handleNotEnoughAmountException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({WrongAmountException.class})
    public ResponseEntity<Object> handleWrongAmountException(WrongAmountException exception) {
        log.error("OrderControllerService-handleWrongAmountException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({OrderEntityNotFoundException.class})
    public ResponseEntity<Object> handleOrderEntityNotFoundException(OrderEntityNotFoundException exception) {
        log.error("OrderControllerService-handleOrderEntityNotFoundException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OrderDBException.class})
    public ResponseEntity<Object> handleOrderDBException(OrderDBException exception) {
        log.error("OrderControllerService-handleOrderDBException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
