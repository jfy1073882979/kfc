package com.jfy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jfy
 * @since 2024-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("discuss")
public class Discuss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    private Integer uid;

    private Integer pid;

    private String content;

    private Integer rating;

    private Date createtime;


}
