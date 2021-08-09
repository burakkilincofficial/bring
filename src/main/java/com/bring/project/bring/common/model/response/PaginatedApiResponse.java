package com.bring.project.bring.common.model.response;

import com.bring.project.bring.common.model.dto.PageVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaginatedApiResponse<T> extends ApiResponse<T> {
    private PageVO pageVO;

    public PaginatedApiResponse(T data, PageVO pageVO) {
        super(data);
        this.pageVO = pageVO;
    }
}
