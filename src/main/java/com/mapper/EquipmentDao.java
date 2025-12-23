package com.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.EquipmentEntity;
import com.entity.view.EquipmentView;
import org.apache.ibatis.annotations.Param;


/**
 * 玩具 Dao 接口
 *
 * @author 
 */
public interface EquipmentDao extends BaseMapper<EquipmentEntity> {

   List<EquipmentView> selectListView(Pagination page, @Param("params")Map<String,Object> params);

}
