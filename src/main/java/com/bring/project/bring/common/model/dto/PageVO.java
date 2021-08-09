package com.bring.project.bring.common.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageVO {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalRecords;
    private Integer totalPage;
}
