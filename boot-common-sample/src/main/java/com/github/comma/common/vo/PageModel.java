package com.github.comma.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
@Data
public class PageModel implements Serializable {

    private int pageNum = 1 ;

    private int pageSize = 10 ;

}
