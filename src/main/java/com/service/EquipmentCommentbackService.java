package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.EquipmentCommentbackEntity;
import java.util.Map;

//设备评价 服务类
public interface EquipmentCommentbackService extends IService<EquipmentCommentbackEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}