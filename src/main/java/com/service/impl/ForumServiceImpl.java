package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.ForumDao;
import com.entity.ForumEntity;
import com.service.ForumService;
import com.entity.view.ForumView;

//论坛 服务实现类
@Service("forumService")
@Transactional
public class ForumServiceImpl extends ServiceImpl<ForumDao, ForumEntity> implements ForumService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<ForumView> page =new Query<ForumView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
