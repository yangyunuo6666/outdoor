package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 设备
 * 接收传参的实体类
 * 实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了
 * 取自ModelAndView 的model名称
 */
public class EquipmentModel implements Serializable {
    private static final long serialVersionUID = 1L;


    //主键
    private Integer id;

    //设备编号
    private String equipmentUuidNumber;

    //设备名称
    private String equipmentName;

    //设备照片
    private String equipmentPhoto;

    //设备类型
    private Integer equipmentTypes;

    //设备库存
    private Integer equipmentKucunNumber;

    //租赁价格/h
    private Double equipmentNewMoney;

    //设备热度
    private Integer equipmentClicknum;

    //设备介绍
    private String equipmentContent;

    //是否上架
    private Integer shangxiaTypes;

    //逻辑删除
    private Integer equipmentDelete;


    //录入时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    //创建时间  show1 show2 photoShow
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

    //获取：设备编号
    public String getEquipmentUuidNumber() {
        return equipmentUuidNumber;
    }

    //设置：设备编号
    public void setEquipmentUuidNumber(String equipmentUuidNumber) {
        this.equipmentUuidNumber = equipmentUuidNumber;
    }

    //获取：设备名称
    public String getEquipmentName() {
        return equipmentName;
    }

    //设置：设备名称
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    //获取：设备照片
    public String getEquipmentPhoto() {
        return equipmentPhoto;
    }

    // 设置：设备照片
    public void setEquipmentPhoto(String equipmentPhoto) {
        this.equipmentPhoto = equipmentPhoto;
    }

    //获取：设备类型
    public Integer getEquipmentTypes() {
        return equipmentTypes;
    }

    // 设置：设备类型
    public void setEquipmentTypes(Integer equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }

    //获取：设备库存
    public Integer getEquipmentKucunNumber() {
        return equipmentKucunNumber;
    }

    //设置：设备库存
    public void setEquipmentKucunNumber(Integer equipmentKucunNumber) {
        this.equipmentKucunNumber = equipmentKucunNumber;
    }

    //获取：租赁价格/h
    public Double getEquipmentNewMoney() {
        return equipmentNewMoney;
    }

    //设置：租赁价格/h
    public void setEquipmentNewMoney(Double equipmentNewMoney) {
        this.equipmentNewMoney = equipmentNewMoney;
    }

    //获取：设备热度
    public Integer getEquipmentClicknum() {
        return equipmentClicknum;
    }

    //设置：设备热度
    public void setEquipmentClicknum(Integer equipmentClicknum) {
        this.equipmentClicknum = equipmentClicknum;
    }

    //获取：设备介绍
    public String getEquipmentContent() {
        return equipmentContent;
    }

    //设置：设备介绍
    public void setEquipmentContent(String equipmentContent) {
        this.equipmentContent = equipmentContent;
    }

    //获取：是否上架
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }

    //设置：是否上架
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }

    //获取：逻辑删除
    public Integer getEquipmentDelete() {
        return equipmentDelete;
    }

    // 设置：逻辑删除
    public void setEquipmentDelete(Integer equipmentDelete) {
        this.equipmentDelete = equipmentDelete;
    }

    //获取：录入时间
    public Date getInsertTime() {
        return insertTime;
    }

    //设置：录入时间
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    //获取：创建时间  show1 show2 photoShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间  show1 show2 photoShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
