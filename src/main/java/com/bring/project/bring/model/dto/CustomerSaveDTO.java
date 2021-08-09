package com.bring.project.bring.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerSaveDTO {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private Integer age;
    @NotNull
    private String email;
}
