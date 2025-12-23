package com.mapper;

import com.entity.EquipmentOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.EquipmentOrderView;

/**
 * 玩具订单 Dao 接口
 *
 * @author 
 */
public interface EquipmentOrderDao extends BaseMapper<EquipmentOrderEntity> {

   List<EquipmentOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
