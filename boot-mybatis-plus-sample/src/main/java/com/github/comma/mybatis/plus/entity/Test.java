package com.github.comma.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * test bean
 *
 * @author wangfc
 * @date 2019-09-05 22:38
 */
@Data
@TableName("t_test")
public class Test implements Serializable {

    @TableId(type = IdType.NONE)
    private int age;

    private String name;

}