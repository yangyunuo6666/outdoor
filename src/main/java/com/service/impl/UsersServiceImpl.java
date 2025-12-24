
package com.service.impl;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Condition;
import com.service.UsersService;
import com.utils.MyMD5Utils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapper.UsersDao;
import com.entity.UsersEntity;
import com.utils.PageUtils;
import com.utils.Query;


//Root用户
@Service("userService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UsersEntity> page = this.selectPage(
                new Query<UsersEntity>(params).getPage(),
                new EntityWrapper<UsersEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<UsersEntity> selectListView(Wrapper<UsersEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			Wrapper<UsersEntity> wrapper) {
		 Page<UsersEntity> page =new Query<UsersEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}

	/**
	 * 重写新增方法：管理员密码自动加密
	 */
	@Override
	public boolean insert(UsersEntity entity) {
		// 密码非空且不是32位MD5密文时，进行加密（避免重复加密）
		if (entity.getPassword() != null && !entity.getPassword().matches("[a-f0-9]{32}")) {
			entity.setPassword(MyMD5Utils.md5WithSalt(entity.getPassword()));
		}
		return super.insert(entity);
	}

	/**
	 * 重写根据ID更新方法：管理员密码修改时自动加密
	 */
	@Override
	public boolean updateById(UsersEntity entity) {
		if (entity.getPassword() != null && !entity.getPassword().matches("[a-f0-9]{32}")) {
			entity.setPassword(MyMD5Utils.md5WithSalt(entity.getPassword()));
		}
		return super.updateById(entity);
	}

	/**
	 * 新增：根据管理员用户名查询（用于登录认证）
	 */
	@Override
	public UsersEntity selectByUsersUsername(String username) {
		// MyBatis-Plus 2.3版本使用Condition构造查询条件
		return this.selectOne(
				Condition.create()
						.eq("username", username) // 管理员表的用户名字段（与你的表字段一致）
		);
	}
}
