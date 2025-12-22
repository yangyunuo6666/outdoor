package com.entity;

import com.annotation.ColumnInfo;

import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


//设备订单
@TableName("equipment_order")
public class EquipmentOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public EquipmentOrderEntity() {

	}

	public EquipmentOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    //主键
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    //订单号
    @ColumnInfo(comment="订单号",type="varchar(200)")
    @TableField(value = "equipment_order_uuid_number")

    private String equipmentOrderUuidNumber;


    //设备
    @ColumnInfo(comment="设备",type="int(11)")
    @TableField(value = "equipment_id")

    private Integer equipmentId;


    //用户
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    //购买数量
    @ColumnInfo(comment="购买数量",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    //租赁时间/h
    @ColumnInfo(comment="租赁时间/h",type="int(11)")
    @TableField(value = "equipment_order_number")

    private Integer equipmentOrderNumber;


    //实付价格
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "equipment_order_true_price")

    private Double equipmentOrderTruePrice;


    //开始租赁时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="开始租赁时间",type="datetime")
    @TableField(value = "equipment_order_time")

    private Date equipmentOrderTime;


    //订单类型
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "equipment_order_types")

    private Integer equipmentOrderTypes;


    //支付类型
    @ColumnInfo(comment="支付类型",type="int(11)")
    @TableField(value = "equipment_order_payment_types")

    private Integer equipmentOrderPaymentTypes;


    //订单创建时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    //创建时间  listShow
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    //获取：主键
    public Integer getId() {
        return id;
    }

    //设置：主键
    public void setId(Integer id) {
        this.id = id;
    }

    //获取：订单号
    public String getEquipmentOrderUuidNumber() {
        return equipmentOrderUuidNumber;
    }

    //设置：订单号
    public void setEquipmentOrderUuidNumber(String equipmentOrderUuidNumber) {
        this.equipmentOrderUuidNumber = equipmentOrderUuidNumber;
    }

    //获取：设备
    public Integer getEquipmentId() {
        return equipmentId;
    }

    //设置：设备
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    //获取：用户
    public Integer getYonghuId() {
        return yonghuId;
    }

    //设置：用户
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    //获取：购买数量
    public Integer getBuyNumber() {
        return buyNumber;
    }

    //设置：购买数量
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    //获取：租赁时间/h
    public Integer getEquipmentOrderNumber() {
        return equipmentOrderNumber;
    }

    //设置：租赁时间/h
    public void setEquipmentOrderNumber(Integer equipmentOrderNumber) {
        this.equipmentOrderNumber = equipmentOrderNumber;
    }

    //获取：实付价格
    public Double getEquipmentOrderTruePrice() {
        return equipmentOrderTruePrice;
    }

    //设置：实付价格
    public void setEquipmentOrderTruePrice(Double equipmentOrderTruePrice) {
        this.equipmentOrderTruePrice = equipmentOrderTruePrice;
    }

    //获取：开始租赁时间
    public Date getEquipmentOrderTime() {
        return equipmentOrderTime;
    }

    //设置：开始租赁时间
    public void setEquipmentOrderTime(Date equipmentOrderTime) {
        this.equipmentOrderTime = equipmentOrderTime;
    }

    //获取：订单类型
    public Integer getEquipmentOrderTypes() {
        return equipmentOrderTypes;
    }

    //设置：订单类型
    public void setEquipmentOrderTypes(Integer equipmentOrderTypes) {
        this.equipmentOrderTypes = equipmentOrderTypes;
    }

    // 获取：支付类型
    public Integer getEquipmentOrderPaymentTypes() {
        return equipmentOrderPaymentTypes;
    }

    //设置：支付类型
    public void setEquipmentOrderPaymentTypes(Integer equipmentOrderPaymentTypes) {
        this.equipmentOrderPaymentTypes = equipmentOrderPaymentTypes;
    }

    //获取：订单创建时间
    public Date getInsertTime() {
        return insertTime;
    }

    //设置：订单创建时间
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    //获取：创建时间  listShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间  listShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EquipmentOrder{" +
            ", id=" + id +
            ", equipmentOrderUuidNumber=" + equipmentOrderUuidNumber +
            ", equipmentId=" + equipmentId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", equipmentOrderNumber=" + equipmentOrderNumber +
            ", equipmentOrderTruePrice=" + equipmentOrderTruePrice +
            ", equipmentOrderTime=" + DateUtil.convertString(equipmentOrderTime,"yyyy-MM-dd") +
            ", equipmentOrderTypes=" + equipmentOrderTypes +
            ", equipmentOrderPaymentTypes=" + equipmentOrderPaymentTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
