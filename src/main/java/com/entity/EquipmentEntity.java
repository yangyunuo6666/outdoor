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


//设备
@TableName("equipment")
public class EquipmentEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public EquipmentEntity() {

	}

	public EquipmentEntity(T t) {
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


    //设备编号
    @ColumnInfo(comment="设备编号",type="varchar(200)")
    @TableField(value = "equipment_uuid_number")

    private String equipmentUuidNumber;


    //设备名称
    @ColumnInfo(comment="设备名称",type="varchar(200)")
    @TableField(value = "equipment_name")

    private String equipmentName;


    //设备照片
    @ColumnInfo(comment="设备照片",type="varchar(200)")
    @TableField(value = "equipment_photo")

    private String equipmentPhoto;


    //设备类型
    @ColumnInfo(comment="设备类型",type="int(11)")
    @TableField(value = "equipment_types")

    private Integer equipmentTypes;


    //设备库存
    @ColumnInfo(comment="设备库存",type="int(11)")
    @TableField(value = "equipment_kucun_number")

    private Integer equipmentKucunNumber;


    //租赁价格/h
    @ColumnInfo(comment="租赁价格/h",type="decimal(10,2)")
    @TableField(value = "equipment_new_money")

    private Double equipmentNewMoney;


    //设备热度
    @ColumnInfo(comment="设备热度",type="int(11)")
    @TableField(value = "equipment_clicknum")

    private Integer equipmentClicknum;


    //设备介绍
    @ColumnInfo(comment="设备介绍",type="longtext")
    @TableField(value = "equipment_content")

    private String equipmentContent;


    //是否上架
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    //逻辑删除
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "equipment_delete")

    private Integer equipmentDelete;


    //录入时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    //创建时间
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

    //设置：设备照片
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

    //设置：逻辑删除
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

    //获取：创建时间
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Equipment{" +
            ", id=" + id +
            ", equipmentUuidNumber=" + equipmentUuidNumber +
            ", equipmentName=" + equipmentName +
            ", equipmentPhoto=" + equipmentPhoto +
            ", equipmentTypes=" + equipmentTypes +
            ", equipmentKucunNumber=" + equipmentKucunNumber +
            ", equipmentNewMoney=" + equipmentNewMoney +
            ", equipmentClicknum=" + equipmentClicknum +
            ", equipmentContent=" + equipmentContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", equipmentDelete=" + equipmentDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
