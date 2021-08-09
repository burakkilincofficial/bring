package com.bring.project.bring.service.impl;

import com.bring.project.bring.common.error.ErrorCode;
import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.dto.Paginator;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.BookException;
import com.bring.project.bring.error.exception.NotEnoughAmountBookInEntityException;
import com.bring.project.bring.error.exception.OrderEntityNotFoundException;
import com.bring.project.bring.error.exception.WrongAmountException;
import com.bring.project.bring.mapper.OrderMapper;
import com.bring.project.bring.model.dto.BuyBookDTO;
import com.bring.project.bring.model.dto.OrderDTO;
import com.bring.project.bring.model.dto.UpdateOrderQuantityInStock;
import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.entity.Order;
import com.bring.project.bring.model.response.OrderResponse;
import com.bring.project.bring.repository.BookRepository;
import com.bring.project.bring.repository.OrderRepository;
import com.bring.project.bring.service.BookService;
import com.bring.project.bring.service.CustomerService;
import com.bring.project.bring.service.OrderService;
import com.bring.project.bring.util.PageUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final BookService bookService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            BookRepository bookRepository,
                            OrderMapper orderMapper,
                            CustomerService customerService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @Override
    public PaginatedApiResponse<List<OrderResponse>> getAllPageableOrder(AppPage appPage) {

        Page<Order> pageOrders = orderRepository.findAll(PageUtil.getPageable(appPage));
        Page<OrderResponse> dtoPage = pageOrders.map(entity -> {
            OrderResponse dto = new OrderResponse();
            dto.setBooks(entity.getBooksOfOrder());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setLastModifiedDate(entity.getLastModifiedDate());
            dto.setCustomer(entity.getCustomer());
            dto.setId(entity.getId());
            dto.setIsValid(entity.getValid());
            return dto;
        });
        return new PaginatedApiResponse<>(dtoPage.getContent(), Paginator.asPageVO(pageOrders));


    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderListResponseList = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setIsValid(order.getValid());
            orderResponse.setCreatedDate(order.getCreatedDate());
            orderResponse.setLastModifiedDate(order.getLastModifiedDate());
            orderResponse.setCustomer(order.getCustomer());
            orderResponse.setBooks(order.getBooksOfOrder());
            orderListResponseList.add(orderResponse);
        }

        return orderListResponseList;
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderEntityNotFoundException(ErrorCode.ORDER_ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public Order save(OrderDTO orderDTO) throws NotEnoughAmountBookInEntityException, WrongAmountException {
        Order order = orderMapper.saveDtoToEntity(orderDTO);
        validateBooksOfOrder(order, orderDTO);
        List<Book> bookList = new ArrayList<>();
        double totalAmountPrice = 0;

        for (BuyBookDTO buyBookDTO : orderDTO.getBookIds()) {
            Book book = bookService.findById(buyBookDTO.getBookId());
            book.setQuantityStock(getNewQuantityStock(buyBookDTO, book));
            totalAmountPrice += book.getUnitPrice() * buyBookDTO.getAmount();
            bookList.add(book);
        }

        order.setTotalAmountAllPurchased(totalAmountPrice);
        order.setBooksOfOrder(bookList);
        return orderRepository.save(order);
    }

    private int getNewQuantityStock(BuyBookDTO buyBookDTO, Book book) throws NotEnoughAmountBookInEntityException {
        if ((book.getQuantityStock() - buyBookDTO.getAmount()) >= 0) {
            return book.getQuantityStock() - buyBookDTO.getAmount();
        } else {
            throw new NotEnoughAmountBookInEntityException(ErrorCode.NOT_ENOUGH_AMOUNT_EXCEPTION.getMessage());
        }
    }

    private void validateBooksOfOrder(Order order, OrderDTO orderDTO) throws WrongAmountException {
        List<Integer> collectIds = orderDTO.getBookIds().stream().map(BuyBookDTO::getBookId).collect(Collectors.toList());
        checkOrderAmount(orderDTO);
        List<Book> books = bookRepository.findByIdIn(collectIds);
        addNewBookIfPresent(books, order);

    }

    private void checkOrderAmount(OrderDTO orderDTO) throws WrongAmountException {
        List<Integer> collectAmounts = orderDTO.getBookIds().stream().map(BuyBookDTO::getAmount).collect(Collectors.toList());
        for (Integer amount : collectAmounts) {
            if (amount <= 0) {
                throw new WrongAmountException(ErrorCode.WRONG_AMOUNT_EXCEPTION.getMessage());
            }
        }
    }

    private void addNewBookIfPresent(List<Book> books, Order order) {
        for (Book book : books) {
            order.addBook(book);
        }
    }

    @Override
    public void delete(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderEntityNotFoundException(ErrorCode.ORDER_ENTITY_NOT_FOUND.getMessage());
        }
        orderRepository.deleteById(id);

    }

    @Override
    public void update(OrderDTO orderDTO) throws WrongAmountException {
        if (orderDTO == null || orderDTO.getId() == null) {
            throw new OrderEntityNotFoundException(ErrorCode.ORDER_ENTITY_NOT_FOUND.getMessage());
        }
        Order order = this.orderById(orderDTO.getId());
        validateBooksOfOrder(order, orderDTO);
        this.updateEntityWithDTO(order, orderDTO);
        orderRepository.save(order);
    }

    private void updateEntityWithDTO(Order order, OrderDTO orderDTO) {
        order.setValid(orderDTO.getIsValid());
        order.setCustomer(customerService.findById(orderDTO.getCustomerId()));
    }

    @Override
    public Order updateQuantityInStock(UpdateOrderQuantityInStock updateBookQuantityInStock) {
        return null;
    }

    @Override
    public Order orderById(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new BookException(ErrorCode.ORDER_ENTITY_NOT_FOUND.name(), id);
        }
    }
}
