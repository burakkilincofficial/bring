package com.bring.project.bring.service;

import com.bring.project.bring.common.model.dto.AppPage;
import com.bring.project.bring.common.model.response.PaginatedApiResponse;
import com.bring.project.bring.model.dto.CustomerSaveDTO;
import com.bring.project.bring.model.dto.CustomerUpdateDTO;
import com.bring.project.bring.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    PaginatedApiResponse<List<Customer>> getAllPageableCustomer(AppPage appPage);

    List<Customer> getAllCustomers();

    Customer findById(Integer id);

    Customer save(CustomerSaveDTO customerSaveDTO);

    void delete(Integer id);

    Customer update(CustomerUpdateDTO customerUpdateDTO);
}
