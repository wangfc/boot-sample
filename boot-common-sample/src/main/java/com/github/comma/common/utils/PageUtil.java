package com.github.comma.common.utils;

import com.github.comma.common.dto.PageDTO;

import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2021-09-25 3:10
 */
public final class PageUtil {

    private PageUtil() {
        throw new IllegalStateException();
    }

    public static PageDTO page(int pageIndex, int pageSize, long totalCount, List content) {
        return page(pageIndex, pageSize, totalCount, (totalCount - 1) / pageSize + 1, content);
    }

    public static PageDTO page(int pageIndex, int pageSize, long totalCount, long totalPages, List content) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPageIndex(pageIndex);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalCount(totalCount);
        pageDTO.setTotalPages(totalPages);
        pageDTO.setContent(content);
        return pageDTO;
    }
}