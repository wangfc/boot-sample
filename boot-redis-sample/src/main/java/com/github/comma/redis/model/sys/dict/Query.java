package com.github.comma.redis.model.sys.dict;

import com.github.comma.redis.model.PageModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 10:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Query extends PageModel {

    private String name;

    private String type;

    private String code;

}