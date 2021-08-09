package com.bring.project.bring.repository;

import com.bring.project.bring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByIdIn(@Param("ids") List<Integer> ids);
}
