package com.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.view.EquipmentStockWarningView;
import com.service.EquipmentStockWarningService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.EquipmentDao;
import com.entity.EquipmentEntity;



@Service("equipmentStockWarningService")
@Transactional
public class EquipmentStockWarningServiceImpl extends ServiceImpl<EquipmentDao, EquipmentEntity> implements EquipmentStockWarningService {

    @Override
    public PageUtils queryWarningPage(Map<String, Object> params) {
        // 构建查询条件：库存<10
        EntityWrapper<EquipmentEntity> queryWrapper = new EntityWrapper<>();
        queryWrapper.lt("equipment_kucun_number", 10);

        Page<EquipmentStockWarningView> page = new Query<EquipmentStockWarningView>(params).getPage();
        List<EquipmentStockWarningView> list = baseMapper.selectList(queryWrapper).stream()
                .map(EquipmentStockWarningView::new)
                .collect(Collectors.toList());

        page.setRecords(list);
        return new PageUtils(page);
    }
}
