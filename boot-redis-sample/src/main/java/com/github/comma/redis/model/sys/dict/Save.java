package com.github.comma.redis.model.sys.dict;

import com.github.comma.redis.model.BaseSaveModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class Save extends BaseSaveModel {

    @NotBlank(message = "name不能为空")
    private String name;

    @NotBlank(message = "type不能为空")
    private String type;

    @NotBlank(message = "code不能为空")
    private String code;

    @NotBlank(message = "type不能为空")
    private String value;

    @NotNull(message = "sortOrder不能为空")
    private Integer sortOrder;

    private String remark;

}