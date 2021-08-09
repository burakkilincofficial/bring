package com.bring.project.bring.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerResponse {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Date createdDate;
    private Date lastModifiedDate;
    private String email;
}
