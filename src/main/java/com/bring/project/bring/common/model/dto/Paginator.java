package com.bring.project.bring.common.model.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Paginator {
    private Paginator() {
    }

    public static <T> Page<T> paginate(List<T> list, int pageSize, int pageIndex) {
        int size = list.size();

        Pageable paging = PageRequest.of(Math.max(0, pageIndex - 1), Math.max(1, pageSize));
        int start = Math.min((int) paging.getOffset(), size);
        int end = Math.min(start + paging.getPageSize(), size);

        return new PageImpl<>(list.subList(start, end), paging, size);

    }

    public static <T> PageVO asPageVO(Page<T> page) {
        PageVO pageVO = new PageVO();
        pageVO.setPageNo(page.getPageable().getPageNumber() + 1);
        pageVO.setPageSize(page.getPageable().getPageSize());
        pageVO.setTotalRecords((int) page.getTotalElements());
        pageVO.setTotalPage(page.getTotalPages());
        return pageVO;
    }
}
