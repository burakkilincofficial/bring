package com.bring.project.bring.service.impl;

import com.bring.project.bring.common.error.ErrorCode;
import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.dto.Paginator;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.error.exception.CustomerAlreadyExistException;
import com.bring.project.bring.error.exception.CustomerDBException;
import com.bring.project.bring.error.exception.CustomerEntityNotFoundException;
import com.bring.project.bring.mapper.CustomerMapper;
import com.bring.project.bring.model.dto.CustomerSaveDTO;
import com.bring.project.bring.model.dto.CustomerUpdateDTO;
import com.bring.project.bring.model.entity.Customer;
import com.bring.project.bring.repository.CustomerRepository;
import com.bring.project.bring.service.CustomerService;
import com.bring.project.bring.util.PageUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public PaginatedApiResponse<List<Customer>> getAllPageableCustomer(AppPage appPage) {
        Page<Customer> pageCustomers = customerRepository.findAll(PageUtil.getPageable(appPage));
        return new PaginatedApiResponse<>(pageCustomers.getContent(), Paginator.asPageVO(pageCustomers));
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerEntityNotFoundException(ErrorCode.CUSTOMER_ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public Customer save(CustomerSaveDTO customerSaveDTO) {
        Customer customer;
        checkCustomerExists(customerSaveDTO.getEmail());
        customer = customerRepository.save(customerMapper.saveDtoToEntity(customerSaveDTO));
        return customer;

    }

    private void checkCustomerExists(String email) {
        if (customerRepository.findByEmail(email) != null) {
            throw new CustomerAlreadyExistException(ErrorCode.CUSTOMER_ALREADY_EXISTS.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerEntityNotFoundException(ErrorCode.CUSTOMER_ENTITY_NOT_FOUND.getMessage());
        }
        customerRepository.deleteById(id);

    }

    @Override
    public Customer update(CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = this.findById(customerUpdateDTO.getId());
        checkCustomerExists(customerUpdateDTO.getEmail());
        if (customer == null) {
            throw new CustomerEntityNotFoundException(ErrorCode.CUSTOMER_ENTITY_NOT_FOUND.getMessage());
        }
        Customer customerUpdated = customerMapper.updateDtoToEntity(customerUpdateDTO);
        Customer result;
        try {
            result = customerRepository.save(customerUpdated);
        } catch (Exception e) {
            throw new CustomerDBException(ErrorCode.CUSTOMER_DB_EXP.getMessage());
        }
        return result;
    }


}
