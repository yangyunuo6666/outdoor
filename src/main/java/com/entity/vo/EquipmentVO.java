package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 设备
 * 手机端接口返回实体辅助类（主要作用去除一些不必要的字段）
 */
@TableName("equipment")
public class EquipmentVO implements Serializable {
    private static final long serialVersionUID = 1L;


    //主键
    @TableField(value = "id")
    private Integer id;

    //设备编号
    @TableField(value = "equipment_uuid_number")
    private String equipmentUuidNumber;

    //设备名称
    @TableField(value = "equipment_name")
    private String equipmentName;

    //设备照片
    @TableField(value = "equipment_photo")
    private String equipmentPhoto;

    //设备类型
    @TableField(value = "equipment_types")
    private Integer equipmentTypes;

    //设备库存
    @TableField(value = "equipment_kucun_number")
    private Integer equipmentKucunNumber;

    //租赁价格/h
    @TableField(value = "equipment_new_money")
    private Double equipmentNewMoney;

    //设备热度
    @TableField(value = "equipment_clicknum")
    private Integer equipmentClicknum;

    //设备介绍
    @TableField(value = "equipment_content")
    private String equipmentContent;

    //是否上架
    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;

    //逻辑删除
    @TableField(value = "equipment_delete")
    private Integer equipmentDelete;

    //录入时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time")
    private Date insertTime;

    //创建时间  show1 show2 photoShow
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


    //设置：设备类型
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

    //获取：创建时间  show1 show2 photoShow
    public Date getCreateTime() {
        return createTime;
    }

    //设置：创建时间  show1 show2 photoShow
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
