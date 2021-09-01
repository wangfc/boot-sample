package com.github.comma.redis.model.sys.dict;

import com.github.comma.redis.model.BaseSaveModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class Update extends BaseSaveModel {

    private Integer id;

    private String name;

    private String type;

    private String code;

    private String value;

    private Integer sortOrder;

    private String remark;

}