package com.bring.project.bring.controller;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.mapper.BasePostResponseMapper;
import com.bring.project.bring.common.model.response.BasePostResponse;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.BookDBException;
import com.bring.project.bring.error.exception.BookEntityNotFoundException;
import com.bring.project.bring.mapper.BookMapper;
import com.bring.project.bring.model.dto.BookSaveDTO;
import com.bring.project.bring.model.dto.BookUpdateDTO;
import com.bring.project.bring.model.dto.UpdateBookQuantityInStock;
import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.response.BookResponse;
import com.bring.project.bring.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookControllerService {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final BasePostResponseMapper basePostResponseMapper;

    public BookControllerService(BookService bookService, BookMapper bookMapper, BasePostResponseMapper basePostResponseMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.basePostResponseMapper = basePostResponseMapper;
    }

    @GetMapping
    public ResponseEntity<PaginatedApiResponse<List<Book>>> getBookListPageable(AppPage appPage) {
        return new ResponseEntity<>(bookService.getAllPageableBook(appPage), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBookList() {
        return new ResponseEntity<>(bookService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasePostResponse> save(@RequestBody @Valid BookSaveDTO bookSaveDTO) {
        return new ResponseEntity<>(basePostResponseMapper.toBaseResponse(bookService.save(bookSaveDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        return new ResponseEntity<>(bookMapper.entityToDto(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        bookService.update(bookUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/quantity")
    public ResponseEntity<Object> updateQuantityInStock(@RequestBody UpdateBookQuantityInStock updateBookQuantityInStock) {
        bookService.updateQuantityInStock(updateBookQuantityInStock);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler({BookEntityNotFoundException.class})
    public ResponseEntity<Object> handleBookEntityNotFoundException(BookEntityNotFoundException exception) {
        log.error("BookControllerService-handleBookEntityNotFoundException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookDBException.class})
    public ResponseEntity<Object> handleBookDBException(BookDBException exception) {
        log.error("BookControllerService-handleBookDBException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
