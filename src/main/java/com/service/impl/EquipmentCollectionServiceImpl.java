package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.EquipmentCollectionDao;
import com.entity.EquipmentCollectionEntity;
import com.service.EquipmentCollectionService;
import com.entity.view.EquipmentCollectionView;

//设备收藏 服务实现类
@Service("equipmentCollectionService")
@Transactional
public class EquipmentCollectionServiceImpl extends ServiceImpl<EquipmentCollectionDao, EquipmentCollectionEntity> implements EquipmentCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<EquipmentCollectionView> page =new Query<EquipmentCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
