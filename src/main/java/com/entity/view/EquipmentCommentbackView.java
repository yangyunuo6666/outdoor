package com.entity.view;

import com.annotation.ColumnInfo;
import com.entity.EquipmentCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
* 玩具评价
* 后端返回视图实体辅助类
* 通常后端关联的表或者自定义的字段需要返回使用
*/
@TableName("equipment_commentback")
public class EquipmentCommentbackView extends EquipmentCommentbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 玩具
		//玩具编号
		@ColumnInfo(comment="玩具编号",type="varchar(200)")
		private String equipmentUuidNumber;

		//玩具名称
		@ColumnInfo(comment="玩具名称",type="varchar(200)")
		private String equipmentName;

		//玩具照片
		@ColumnInfo(comment="玩具照片",type="varchar(200)")
		private String equipmentPhoto;

		//玩具类型
		@ColumnInfo(comment="玩具类型",type="int(11)")
		private Integer equipmentTypes;
			//玩具类型的值
			@ColumnInfo(comment="玩具类型的字典表值",type="varchar(200)")
			private String equipmentValue;

		//玩具库存
		@ColumnInfo(comment="玩具库存",type="int(11)")
		private Integer equipmentKucunNumber;

		//租赁价格/h
		@ColumnInfo(comment="租赁价格/h",type="decimal(10,2)")
		private Double equipmentNewMoney;

		//玩具热度
		@ColumnInfo(comment="玩具热度",type="int(11)")
		private Integer equipmentClicknum;

		//玩具介绍
		@ColumnInfo(comment="玩具介绍",type="longtext")
		private String equipmentContent;

		//是否上架
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			//是否上架的值
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;

		//逻辑删除
		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer equipmentDelete;
	//级联表 用户

		// 用户姓名
		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;

		//联系方式
		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String yonghuPhone;

		//身份证号
		@ColumnInfo(comment="身份证号",type="varchar(200)")
		private String yonghuIdNumber;

		//用户头像
		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;

		//电子邮箱
		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;

		//余额
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;


	public EquipmentCommentbackView() {

	}

	public EquipmentCommentbackView(EquipmentCommentbackEntity equipmentCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, equipmentCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 玩具

		//获取： 玩具编号
		public String getEquipmentUuidNumber() {
			return equipmentUuidNumber;
		}

		//设置： 玩具编号
		public void setEquipmentUuidNumber(String equipmentUuidNumber) {
			this.equipmentUuidNumber = equipmentUuidNumber;
		}

		//获取： 玩具名称
		public String getEquipmentName() {
			return equipmentName;
		}

		//设置： 玩具名称
		public void setEquipmentName(String equipmentName) {
			this.equipmentName = equipmentName;
		}

		//获取： 玩具照片
		public String getEquipmentPhoto() {
			return equipmentPhoto;
		}

		//设置： 玩具照片
		public void setEquipmentPhoto(String equipmentPhoto) {
			this.equipmentPhoto = equipmentPhoto;
		}

		//获取： 玩具类型
		public Integer getEquipmentTypes() {
			return equipmentTypes;
		}

		//设置： 玩具类型
		public void setEquipmentTypes(Integer equipmentTypes) {
			this.equipmentTypes = equipmentTypes;
		}


			//获取： 玩具类型的值
			public String getEquipmentValue() {
				return equipmentValue;
			}
			//设置： 玩具类型的值
			public void setEquipmentValue(String equipmentValue) {
				this.equipmentValue = equipmentValue;
			}

		//获取： 玩具库存
		public Integer getEquipmentKucunNumber() {
			return equipmentKucunNumber;
		}

		//设置： 玩具库存
		public void setEquipmentKucunNumber(Integer equipmentKucunNumber) {
			this.equipmentKucunNumber = equipmentKucunNumber;
		}

		//获取： 租赁价格/h
		public Double getEquipmentNewMoney() {
			return equipmentNewMoney;
		}

		//设置： 租赁价格/h
		public void setEquipmentNewMoney(Double equipmentNewMoney) {
			this.equipmentNewMoney = equipmentNewMoney;
		}

		//获取： 玩具热度
		public Integer getEquipmentClicknum() {
			return equipmentClicknum;
		}

		//设置： 玩具热度
		public void setEquipmentClicknum(Integer equipmentClicknum) {
			this.equipmentClicknum = equipmentClicknum;
		}

		//获取： 玩具介绍
		public String getEquipmentContent() {
			return equipmentContent;
		}

		//设置： 玩具介绍
		public void setEquipmentContent(String equipmentContent) {
			this.equipmentContent = equipmentContent;
		}

		//获取： 是否上架
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}

		//设置： 是否上架
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			// 获取： 是否上架的值
			public String getShangxiaValue() {
				return shangxiaValue;
			}

			//设置： 是否上架的值
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		//获取： 逻辑删除
		public Integer getEquipmentDelete() {
			return equipmentDelete;
		}
		//设置： 逻辑删除
		public void setEquipmentDelete(Integer equipmentDelete) {
			this.equipmentDelete = equipmentDelete;
		}
	//级联表的get和set 用户

		//获取： 用户姓名
		public String getYonghuName() {
			return yonghuName;
		}

		//设置： 用户姓名
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		//获取： 联系方式
		public String getYonghuPhone() {
			return yonghuPhone;
		}

		//设置： 联系方式
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		//获取： 身份证号
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}

		// 设置： 身份证号
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		//获取： 用户头像
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}

		//设置： 用户头像
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		//获取： 电子邮箱
		public String getYonghuEmail() {
			return yonghuEmail;
		}

		//设置： 电子邮箱
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		//获取： 余额
		public Double getNewMoney() {
			return newMoney;
		}

		//设置： 余额
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}


	@Override
	public String toString() {
		return "EquipmentCommentbackView{" +
			", equipmentUuidNumber=" + equipmentUuidNumber +
			", equipmentName=" + equipmentName +
			", equipmentPhoto=" + equipmentPhoto +
			", equipmentKucunNumber=" + equipmentKucunNumber +
			", equipmentNewMoney=" + equipmentNewMoney +
			", equipmentClicknum=" + equipmentClicknum +
			", equipmentContent=" + equipmentContent +
			", equipmentDelete=" + equipmentDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			"} " + super.toString();
	}
}
