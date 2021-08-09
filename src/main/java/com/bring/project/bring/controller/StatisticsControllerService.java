package com.bring.project.bring.controller;

import com.bring.project.bring.common.model.mapper.BasePostResponseMapper;
import com.bring.project.bring.model.response.StatisticsResponse;
import com.bring.project.bring.service.StatisticsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/api/statistics")
public class StatisticsControllerService {
    private final StatisticsService orderService;
    private final BasePostResponseMapper basePostResponseMapper;

    public StatisticsControllerService(StatisticsService orderService, BasePostResponseMapper basePostResponseMapper
    ) {
        this.orderService = orderService;
        this.basePostResponseMapper = basePostResponseMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<StatisticsResponse> getAllStatisticsList() {
        return new ResponseEntity<>(orderService.getAllStatistics(), HttpStatus.OK);
    }


}
