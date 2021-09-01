package com.github.comma.redis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 10:56
 */
@Data
@ApiModel
public class BaseSaveModel {

    @ApiModelProperty(notes = "创建用户id", hidden = true)
    private Integer create_by;

    @ApiModelProperty(notes = "创建时间", hidden = true)
    private Date create_time;

    @ApiModelProperty(notes = "更新用户id", hidden = true)
    private Integer update_by;

    @ApiModelProperty(notes = "更新时间", hidden = true)
    private Date update_time;

    @PostConstruct
    public void init() {
        this.create_time = new Date();
        this.update_time = new Date();
    }

}