package com.bring.project.bring.mapper;

import com.bring.project.bring.model.dto.CustomerSaveDTO;
import com.bring.project.bring.model.dto.CustomerUpdateDTO;
import com.bring.project.bring.model.entity.Customer;
import com.bring.project.bring.model.response.CustomerResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CustomerMapper {

    public Customer saveDtoToEntity(CustomerSaveDTO customerSaveDTO) {
        Customer customer = new Customer();
        customer.setName(customerSaveDTO.getName());
        customer.setSurname(customerSaveDTO.getSurname());
        customer.setAge(customerSaveDTO.getAge());
        customer.setEmail(customerSaveDTO.getEmail());
        return customer;
    }

    public Customer updateDtoToEntity(CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = new Customer();
        customer.setId(customerUpdateDTO.getId());
        customer.setName(customerUpdateDTO.getName());
        customer.setSurname(customerUpdateDTO.getSurname());
        customer.setAge(customerUpdateDTO.getAge());
        customer.setEmail(customerUpdateDTO.getEmail());
        return customer;
    }

    public CustomerResponse entityToDto(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .createdDate(customer.getCreatedDate())
                .lastModifiedDate(customer.getLastModifiedDate())
                .email(customer.getEmail())
                .build();
    }
}
