package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.EquipmentOrderDao;
import com.entity.EquipmentOrderEntity;
import com.service.EquipmentOrderService;
import com.entity.view.EquipmentOrderView;

//设备订单 服务实现类
@Service("equipmentOrderService")
@Transactional
public class EquipmentOrderServiceImpl extends ServiceImpl<EquipmentOrderDao, EquipmentOrderEntity> implements EquipmentOrderService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<EquipmentOrderView> page =new Query<EquipmentOrderView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
