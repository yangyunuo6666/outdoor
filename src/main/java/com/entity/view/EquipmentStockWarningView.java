package com.entity.view;

import com.annotation.ColumnInfo;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.EquipmentEntity;
import org.apache.commons.beanutils.BeanUtils;
import java.io.Serializable;


/**
 * 设备库存预警视图
 */
@TableName("equipment")
public class EquipmentStockWarningView extends EquipmentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 继承设备基本属性并添加预警标识
    @ColumnInfo(comment = "是否预警", type = "boolean")
    private boolean warning;

    public EquipmentStockWarningView() {
    }

    public EquipmentStockWarningView(EquipmentEntity equipment) {
        try {
            BeanUtils.copyProperties(this, equipment);
            this.warning = equipment.getEquipmentKucunNumber() != null &&
                    equipment.getEquipmentKucunNumber() < 10;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getter和setter
    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }
}