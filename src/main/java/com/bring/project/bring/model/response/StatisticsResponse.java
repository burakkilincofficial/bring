package com.bring.project.bring.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsResponse {
    private Integer totalOrderCount;
    private Double totalAmountOfOrders;
    private Integer totalCountOfBooks;
    private String month;
}
