package com.bring.project.bring.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppPage {
    private int pageNo = 0;
    private int pageSize = 10;
    private String sortBy;
    private String sortDirection = Sort.Direction.DESC.name();

}
