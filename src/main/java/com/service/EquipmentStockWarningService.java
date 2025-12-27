package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.EquipmentEntity;
import com.utils.PageUtils;
import java.util.Map;

public interface EquipmentStockWarningService extends IService<EquipmentEntity> {
    PageUtils queryWarningPage(Map<String, Object> params);
}
