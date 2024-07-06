package com.jfy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    private String pname;

    private BigDecimal price;

    private String pfile;

    private String description;

    private Integer count;

    private BigDecimal rating;

    @TableField(exist = false)
    private Cart cart;


}
