package com.bring.project.bring.service;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.model.dto.BookSaveDTO;
import com.bring.project.bring.model.dto.BookUpdateDTO;
import com.bring.project.bring.model.dto.UpdateBookQuantityInStock;
import com.bring.project.bring.model.entity.Book;

import java.util.List;

public interface BookService {
    PaginatedApiResponse<List<Book>> getAllPageableBook(AppPage appPage);

    List<Book> getAllCustomers();

    Book findById(Integer id);

    Book save(BookSaveDTO bookSaveDTO);

    void delete(Integer id);

    Book update(BookUpdateDTO bookUpdateDTO);

    Book updateQuantityInStock(UpdateBookQuantityInStock updateBookQuantityInStock);
}
