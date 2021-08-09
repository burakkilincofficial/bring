package com.bring.project.bring.mapper;

import com.bring.project.bring.model.dto.BookSaveDTO;
import com.bring.project.bring.model.dto.BookUpdateDTO;
import com.bring.project.bring.model.entity.Book;
import com.bring.project.bring.model.response.BookResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class BookMapper {
    public Book saveDtoToEntity(BookSaveDTO bookSaveDTO) {
        Book book = new Book();
        book.setBookName(bookSaveDTO.getBookName());
        book.setWriter(bookSaveDTO.getWriter());
        book.setCategory(bookSaveDTO.getCategory());
        book.setQuantityStock(bookSaveDTO.getQuantityInStock());
        book.setUnitPrice(bookSaveDTO.getUnitPrice());
        return book;
    }

    public Book updateDtoToEntity(BookUpdateDTO bookUpdateDTO) {
        Book book = new Book();
        book.setId(bookUpdateDTO.getId());
        book.setBookName(bookUpdateDTO.getBookName());
        book.setWriter(bookUpdateDTO.getWriter());
        book.setCategory(bookUpdateDTO.getCategory());
        book.setUnitPrice(bookUpdateDTO.getUnitPrice());
        return book;
    }

    public BookResponse entityToDto(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .writer(book.getWriter())
                .category(book.getCategory())
                .createdDate(book.getCreatedDate())
                .lastModifiedDate(book.getLastModifiedDate())
                .quantityInStock(book.getQuantityStock())
                .build();
    }
}
