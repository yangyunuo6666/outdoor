package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.EquipmentOrderEntity;
import java.util.Map;

//设备订单 服务类
public interface EquipmentOrderService extends IService<EquipmentOrderEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}