package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.EquipmentDao;
import com.entity.EquipmentEntity;
import com.service.EquipmentService;
import com.entity.view.EquipmentView;

//设备 服务实现类
@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl extends ServiceImpl<EquipmentDao, EquipmentEntity> implements EquipmentService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<EquipmentView> page =new Query<EquipmentView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
