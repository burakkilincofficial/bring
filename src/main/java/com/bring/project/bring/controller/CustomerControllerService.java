package com.bring.project.bring.controller;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.mapper.BasePostResponseMapper;
import com.bring.project.bring.common.model.response.BasePostResponse;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.CustomerAlreadyExistException;
import com.bring.project.bring.error.exception.CustomerDBException;
import com.bring.project.bring.error.exception.CustomerEntityNotFoundException;
import com.bring.project.bring.mapper.CustomerMapper;
import com.bring.project.bring.model.dto.CustomerSaveDTO;
import com.bring.project.bring.model.dto.CustomerUpdateDTO;
import com.bring.project.bring.model.entity.Customer;
import com.bring.project.bring.model.response.CustomerResponse;
import com.bring.project.bring.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerControllerService {
    private final CustomerService customerService;
    private final BasePostResponseMapper basePostResponseMapper;
    private final CustomerMapper customerMapper;

    public CustomerControllerService(CustomerService customerService, BasePostResponseMapper basePostResponseMapper, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.basePostResponseMapper = basePostResponseMapper;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<PaginatedApiResponse<List<Customer>>> getCustomerListPageable(AppPage appPage) {
        return new ResponseEntity<>(customerService.getAllPageableCustomer(appPage), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomerList() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasePostResponse> save(@RequestBody @Valid CustomerSaveDTO customerSaveDTO) {
        return new ResponseEntity<>(basePostResponseMapper.toBaseResponse(customerService.save(customerSaveDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Integer id) {
        Customer customer = customerService.findById(id);
        return new ResponseEntity<>(customerMapper.entityToDto(customer), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody @Valid CustomerUpdateDTO customerUpdateDTO) {
        customerService.update(customerUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler({CustomerEntityNotFoundException.class})
    public ResponseEntity<Object> handleCustomerEntityNotFoundException(CustomerEntityNotFoundException exception) {
        log.error("CustomerControllerService-handleCustomerEntityNotFoundException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CustomerDBException.class})
    public ResponseEntity<Object> handleCustomerDBException(CustomerDBException exception) {
        log.error("CustomerControllerService-handleCustomerDBException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CustomerAlreadyExistException.class})
    public ResponseEntity<Object> handleAlreadyExistException(CustomerAlreadyExistException exception) {
        log.error("CustomerControllerService-handleAlreadyExistException: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
