package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 玩具订单
 * 手机端接口返回实体辅助类（主要作用去除一些不必要的字段）
 */
@TableName("equipment_order")
public class EquipmentOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    //主键
    @TableField(value = "id")
    private Integer id;

    //订单号
    @TableField(value = "equipment_order_uuid_number")
    private String equipmentOrderUuidNumber;

    //玩具
    @TableField(value = "equipment_id")
    private Integer equipmentId;

    //用户
    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    //购买数量
    @TableField(value = "buy_number")
    private Integer buyNumber;

    //租赁时间/h
    @TableField(value = "equipment_order_number")
    private Integer equipmentOrderNumber;

    //实付价格
    @TableField(value = "equipment_order_true_price")
    private Double equipmentOrderTruePrice;

    //订单类型
    @TableField(value = "equipment_order_types")
    private Integer equipmentOrderTypes;

    //支付类型
    @TableField(value = "equipment_order_payment_types")
    private Integer equipmentOrderPaymentTypes;

    //订单创建时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time")
    private Date insertTime;

    //创建时间 show3 listShow
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time")
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

    //获取：玩具
    public Integer getEquipmentId() {
        return equipmentId;
    }

    //设置：玩具
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

    //获取：订单类型
    public Integer getEquipmentOrderTypes() {
        return equipmentOrderTypes;
    }

    //设置：订单类型
    public void setEquipmentOrderTypes(Integer equipmentOrderTypes) {
        this.equipmentOrderTypes = equipmentOrderTypes;
    }

    //获取：支付类型
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

    //获取：创建时间 show3 listShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间 show3 listShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
