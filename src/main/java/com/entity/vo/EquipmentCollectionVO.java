package com.entity.vo;

import com.entity.EquipmentCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 设备收藏
 * 手机端接口返回实体辅助类（主要作用去除一些不必要的字段）
 */
@TableName("equipment_collection")
public class EquipmentCollectionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @TableField(value = "id")
    private Integer id;

    //设备
    @TableField(value = "equipment_id")
    private Integer equipmentId;

    //用户
    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    //类型
    @TableField(value = "equipment_collection_types")
    private Integer equipmentCollectionTypes;

    //收藏时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time")
    private Date insertTime;

    //创建时间 show3 photoShow
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

    //获取：创建时间 show3 photoShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间 show3 photoShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
