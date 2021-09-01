package com.github.comma.redis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 10:45
 */
@Data
@ApiModel
public class PageModel implements Serializable {

    private static final long serialVersionUID = 4109941585077189943L;

    @ApiModelProperty(notes = "页码")
    private int pageNum = 1;

    @ApiModelProperty(notes = "每页条数，默认10条")
    private int pageSize = 10;
}