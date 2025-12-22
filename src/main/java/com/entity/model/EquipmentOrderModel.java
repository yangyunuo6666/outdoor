package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 设备订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class EquipmentOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;


    //主键
    private Integer id;

    //订单号
    private String equipmentOrderUuidNumber;

    //设备
    private Integer equipmentId;

    // 用户
    private Integer yonghuId;

    //购买数量
    private Integer buyNumber;

    // 租赁时间/h
    private Integer equipmentOrderNumber;

    //实付价格
    private Double equipmentOrderTruePrice;

    //订单类型
    private Integer equipmentOrderTypes;

    //支付类型
    private Integer equipmentOrderPaymentTypes;


    //订单创建时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    //创建时间 show3 listShow
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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

    // 设置：订单创建时间
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    // 获取：创建时间 show3 listShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间 show3 listShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
