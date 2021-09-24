package com.github.comma.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2021-09-25 3:08
 */
@Data
public class PageDTO<T> {

    private int pageIndex;

    private int pageSize;

    private long totalCount;

    private long totalPages;

    private List<T> content;

}