package com.github.comma.redis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName(value = "sys_dict")
public class SysDict implements Serializable {

    private static final long serialVersionUID = -8388395723950458824L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private String code;

    private String value;

    private Integer sortOrder;

    private String remark;

    @TableLogic
    private Byte delFlag;

}