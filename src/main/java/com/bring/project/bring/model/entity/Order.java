package com.bring.project.bring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_valid", columnDefinition = "boolean default true")
    private Boolean isValid = true;

    @Column(name = "total_amount_all_purchased")
    private Double totalAmountAllPurchased;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksOfOrder = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    public Double getTotalAmountAllPurchased() {
        return totalAmountAllPurchased;
    }

    public void setTotalAmountAllPurchased(Double totalAmountAllPurchased) {
        this.totalAmountAllPurchased = totalAmountAllPurchased;
    }

    public void addBook(Book book) {
        this.booksOfOrder.add(book);
        book.getOrders().add(this);
    }

    public List<Integer> getBookIdList() {
        return booksOfOrder
                .stream()
                .map(Book::getId)
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public List<Book> getBooksOfOrder() {
        return booksOfOrder;
    }

    public void setBooksOfOrder(List<Book> booksOfOrder) {
        this.booksOfOrder = booksOfOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
