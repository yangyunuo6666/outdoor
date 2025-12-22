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


//设备收藏
@TableName("equipment_collection")
public class EquipmentCollectionEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public EquipmentCollectionEntity() {

	}

	public EquipmentCollectionEntity(T t) {
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


    //设备
    @ColumnInfo(comment="设备",type="int(11)")
    @TableField(value = "equipment_id")

    private Integer equipmentId;


    //用户
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    //类型
    @ColumnInfo(comment="类型",type="int(11)")
    @TableField(value = "equipment_collection_types")

    private Integer equipmentCollectionTypes;


    //收藏时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="收藏时间",type="timestamp")
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

    //获取：类型
    public Integer getEquipmentCollectionTypes() {
        return equipmentCollectionTypes;
    }

    //设置：类型
    public void setEquipmentCollectionTypes(Integer equipmentCollectionTypes) {
        this.equipmentCollectionTypes = equipmentCollectionTypes;
    }

    //获取：收藏时间
    public Date getInsertTime() {
        return insertTime;
    }

    //设置：收藏时间
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
        return "EquipmentCollection{" +
            ", id=" + id +
            ", equipmentId=" + equipmentId +
            ", yonghuId=" + yonghuId +
            ", equipmentCollectionTypes=" + equipmentCollectionTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
