package com.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.utils.MyMD5Utils;
import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.mapper.YonghuDao;
import com.entity.YonghuEntity;
import com.service.YonghuService;
import com.entity.view.YonghuView;

//用户 服务实现类
@Service("yonghuService")
@Transactional
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity> implements YonghuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<YonghuView> page =new Query<YonghuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

    @Override
    public YonghuEntity selectByUsername(String username) {
        // Condition.create()用于创建查询条件，eq()添加等于条件
        return this.selectOne(
                Condition.create()
                        .eq("username", username) // "username"对应数据库表中的账户字段
        );
    }

    /**
     * 重写新增方法，添加密码加密逻辑
     * @param entity 用户实体
     * @return 新增结果
     */
    @Override
    public boolean insert(YonghuEntity entity) {
        // 密码非空且不是已加密的32位MD5密文时，进行加密处理
        if (entity.getPassword() != null && !entity.getPassword().matches("[a-f0-9]{32}")) {
            entity.setPassword(MyMD5Utils.md5WithSalt(entity.getPassword()));
        }
        // 调用父类原有新增逻辑，保证原有功能不变
        return super.insert(entity);
    }

    /**
     * 重写根据ID更新方法，添加密码加密逻辑
     * @param entity 用户实体
     * @return 更新结果
     */
    @Override
    public boolean updateById(YonghuEntity entity) {
        // 密码非空且不是已加密的32位MD5密文时，进行加密处理
        if (entity.getPassword() != null && !entity.getPassword().matches("[a-f0-9]{32}")) {
            entity.setPassword(MyMD5Utils.md5WithSalt(entity.getPassword()));
        }
        // 调用父类原有更新逻辑，保证原有功能不变
        return super.updateById(entity);
    }

    /**
     * （可选）批量新增用户时的密码加密（若你有批量导入需求）
     * @param entityList 用户实体列表
     * @return 批量新增结果
     */
    @Override
    public boolean insertBatch(List<YonghuEntity> entityList) {
        // 遍历列表，对每个用户的密码进行加密处理
        for (YonghuEntity entity : entityList) {
            if (entity.getPassword() != null && !entity.getPassword().matches("[a-f0-9]{32}")) {
                entity.setPassword(MyMD5Utils.md5WithSalt(entity.getPassword()));
            }
        }
        // 调用父类批量新增逻辑
        return super.insertBatch(entityList);
    }

}
