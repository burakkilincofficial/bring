package com.bring.project.bring.controller;

import com.bring.project.bring.common.model.mapper.BasePostResponseMapper;
import com.bring.project.bring.mapper.CustomerMapper;
import com.bring.project.bring.model.dto.CustomerSaveDTO;
import com.bring.project.bring.model.entity.Customer;
import com.bring.project.bring.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerControllerService.class)
@EnableWebMvc
public class CustomerControllerServiceTest {

    private static final String BASE_URL = "/api";
    private static final String CUSTOMERS = "/customers";
    private static final String GET_ALL = "/all";
    private static final String SLASH = "/";


    private static final Integer ID = 1;
    private static final Long AMOUNT = 10L;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    @InjectMocks
    private CustomerControllerService controllerService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private BasePostResponseMapper basePostResponseMapper;
    @MockBean
    private CustomerMapper customerMapper;
    @Autowired
    private WebApplicationContext wac;

    public CustomerControllerServiceTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerService).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void save() throws Exception {
        CustomerSaveDTO customerSaveDTO = new CustomerSaveDTO();
        customerSaveDTO.setEmail("bk12@gmail.com");
        customerSaveDTO.setSurname("kilinc");
        customerSaveDTO.setName("burak");
        customerSaveDTO.setAge(27);
        Customer customer = new Customer();
        customer.setAge(customerSaveDTO.getAge());
        customer.setSurname(customerSaveDTO.getSurname());
        customer.setName(customerSaveDTO.getName());
        customer.setEmail(customerSaveDTO.getEmail());


        when(customerService.save(customerSaveDTO)).thenReturn(customer);
        mockMvc.perform(post(BASE_URL + CUSTOMERS)
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void getCustomerWithId() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);

        when(customerService.findById(ID)).thenReturn(customer);
        mockMvc.perform(get(BASE_URL + CUSTOMERS + SLASH+ID )
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void getAllCustomerList() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        Customer customer1 = new Customer();
        customer.setId(2);
        List<Customer> employeeList = new ArrayList<>();
        employeeList.add(customer);
        employeeList.add(customer1);

        when(customerService.getAllCustomers()).thenReturn(employeeList);
        mockMvc.perform(get(BASE_URL + CUSTOMERS + GET_ALL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}