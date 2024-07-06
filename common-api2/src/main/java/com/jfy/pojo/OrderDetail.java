package com.jfy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("t_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "opid", type = IdType.AUTO)
    private Integer opid;

    private Integer oid;

    private Integer pid;


}
