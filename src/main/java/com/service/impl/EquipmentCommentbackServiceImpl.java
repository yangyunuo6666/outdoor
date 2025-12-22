package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.EquipmentCommentbackDao;
import com.entity.EquipmentCommentbackEntity;
import com.service.EquipmentCommentbackService;
import com.entity.view.EquipmentCommentbackView;

//设备评价 服务实现类
@Service("equipmentCommentbackService")
@Transactional
public class EquipmentCommentbackServiceImpl extends ServiceImpl<EquipmentCommentbackDao, EquipmentCommentbackEntity> implements EquipmentCommentbackService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<EquipmentCommentbackView> page =new Query<EquipmentCommentbackView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
