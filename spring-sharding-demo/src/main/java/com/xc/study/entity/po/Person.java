package com.xc.study.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "person")
public class Person {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 姓名
     */
    @TableField(value = "\"name\"")
    private String name;

    /**
     * 年纪
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    @TableField(value = "vaild_flag")
    private String vaildFlag;

    @TableField(value = "create_date")
    private Date createDate;
}