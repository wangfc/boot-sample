package com.github.comma.common.vo.in;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
@Data
public class TestIn {

    @NotEmpty(message = "name can not be null ")
    private String name ;

    @NotNull(message = "id can not be null ")
    private Long id;
}
