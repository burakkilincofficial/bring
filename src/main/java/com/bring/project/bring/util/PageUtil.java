package com.bring.project.bring.util;

import com.bring.project.bring.common.model.dto.AppPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class PageUtil {
    public static Pageable getPageable(AppPage appPage) {
        return PageRequest.of(appPage.getPageNo(), appPage.getPageSize());
    }

}
