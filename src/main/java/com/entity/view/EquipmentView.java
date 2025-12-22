package com.entity.view;

import com.annotation.ColumnInfo;
import com.entity.EquipmentEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
* 设备
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("equipment")
public class EquipmentView extends EquipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	//设备类型的值
	@ColumnInfo(comment="设备类型的字典表值",type="varchar(200)")
	private String equipmentValue;

	//是否上架的值
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;



	public EquipmentView() {

	}

	public EquipmentView(EquipmentEntity equipmentEntity) {
		try {
			BeanUtils.copyProperties(this, equipmentEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//当前表的
	//获取： 设备类型的值
	public String getEquipmentValue() {
		return equipmentValue;
	}

	// 设置： 设备类型的值
	public void setEquipmentValue(String equipmentValue) {
		this.equipmentValue = equipmentValue;
	}
	//当前表的

	// 获取： 是否上架的值
	public String getShangxiaValue() {
		return shangxiaValue;
	}

	//设置： 是否上架的值
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
	}



	@Override
	public String toString() {
		return "EquipmentView{" +
			", equipmentValue=" + equipmentValue +
			", shangxiaValue=" + shangxiaValue +
			"} " + super.toString();
	}
}
