package com.github.comma.redis.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/30
 */
@Entity
@Table(name = "sys_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SysConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String paramKey;

    @Column
    private String paramValue;

    @Column
    private Byte delFlag;

    @Column
    private String remark;


    @Override
    public String toString() {
        return "SysConfig{" +
                "id=" + id +
                ", paramKey='" + paramKey + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", delFlag=" + delFlag +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysConfig sysConfig = (SysConfig) o;
        return Objects.equals(id, sysConfig.id) &&
                Objects.equals(paramKey, sysConfig.paramKey) &&
                Objects.equals(paramValue, sysConfig.paramValue) &&
                Objects.equals(delFlag, sysConfig.delFlag) &&
                Objects.equals(remark, sysConfig.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paramKey, paramValue, delFlag, remark);
    }
}
