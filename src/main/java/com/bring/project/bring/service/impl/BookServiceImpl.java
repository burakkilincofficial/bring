package com.bring.project.bring.service.impl;

import com.bring.project.bring.common.error.ErrorCode;
import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.dto.Paginator;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.BookDBException;
import com.bring.project.bring.error.exception.BookEntityNotFoundException;
import com.bring.project.bring.mapper.BookMapper;
import com.bring.project.bring.model.dto.BookSaveDTO;
import com.bring.project.bring.model.dto.BookUpdateDTO;
import com.bring.project.bring.model.dto.UpdateBookQuantityInStock;
import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.repository.BookRepository;
import com.bring.project.bring.service.BookService;
import com.bring.project.bring.util.PageUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public PaginatedApiResponse<List<Book>> getAllPageableBook(AppPage appPage) {
        Page<Book> pageBooks = bookRepository.findAll(PageUtil.getPageable(appPage));
        return new PaginatedApiResponse<>(pageBooks.getContent(), Paginator.asPageVO(pageBooks));
    }

    @Override
    public List<Book> getAllCustomers() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookEntityNotFoundException(ErrorCode.BOOK_ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public Book save(BookSaveDTO bookSaveDTO) {
        Book book;
        try {
            book = bookRepository.save(bookMapper.saveDtoToEntity(bookSaveDTO));
        } catch (Exception e) {
            throw new BookDBException(ErrorCode.BOOK_DB_EXP.getMessage());
        }
        return book;
    }

    @Override
    public void delete(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new BookEntityNotFoundException(ErrorCode.CUSTOMER_ENTITY_NOT_FOUND.getMessage());
        }
        bookRepository.deleteById(id);

    }

    @Override
    public Book update(BookUpdateDTO bookUpdateDTO) {
        Book book = this.findById(bookUpdateDTO.getId());
        if (book == null) {
            throw new BookEntityNotFoundException(ErrorCode.BOOK_ENTITY_NOT_FOUND.getMessage());
        }
        Book bookUpdated = bookMapper.updateDtoToEntity(bookUpdateDTO);
        Book result;
        try {
            result = bookRepository.save(bookUpdated);
        } catch (Exception e) {
            throw new BookDBException(ErrorCode.BOOK_DB_EXP.getMessage());
        }
        return result;
    }

    @Override
    public Book updateQuantityInStock(UpdateBookQuantityInStock updateBookQuantityInStock) {
        Book book = this.findById(updateBookQuantityInStock.getId());
        if (book == null) {
            throw new BookEntityNotFoundException(ErrorCode.BOOK_ENTITY_NOT_FOUND.getMessage());
        }
        book.setQuantityStock(updateBookQuantityInStock.getNewQuantityInStock());
        bookRepository.save(book);
        return book;
    }
}
