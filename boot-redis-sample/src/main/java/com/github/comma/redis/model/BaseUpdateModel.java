package com.github.comma.redis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 11:02
 */
@Data
@ApiModel
public class BaseUpdateModel implements Serializable {

    private static final long serialVersionUID = -4080750659225087954L;
    @ApiModelProperty(notes = "更新用户id", hidden = true)
    private Integer update_by;

    @ApiModelProperty(notes = "更新时间", hidden = true)
    private Date update_time;

    @PostConstruct
    public void init() {
        this.update_time = new Date();
    }
}