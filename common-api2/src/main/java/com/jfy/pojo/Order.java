package com.jfy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    private String status;

    private BigDecimal totalPrice;

    private Integer uid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @TableField(exist = false)
    List<Product> productList;


}
