package com.bring.project.bring.service;

import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.entity.Order;
import com.bring.project.bring.model.response.StatisticsResponse;
import com.bring.project.bring.repository.BookRepository;
import com.bring.project.bring.repository.CustomerRepository;
import com.bring.project.bring.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class StatisticsServiceImpl implements StatisticsService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public StatisticsServiceImpl(OrderRepository orderRepository,
                                 BookRepository bookRepository,
                                 CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public StatisticsResponse getAllStatistics() {
        List<Order> allOrder = orderRepository.findAll();
        List<Book> allBooks = bookRepository.findAll();
        List<Double> collectOrderTotalAmount = allOrder.stream().map(x -> x.getTotalAmountAllPurchased()).collect(Collectors.toList());


        return StatisticsResponse.builder()
                .totalOrderCount(allOrder.size())
                .totalCountOfBooks(allBooks.size())
                .totalAmountOfOrders(collectOrderTotalAmount.stream().mapToDouble(Double::doubleValue).sum())
                .month("Aug")
                .build();
    }
}
