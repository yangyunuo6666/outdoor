package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.EquipmentCommentbackEntity;
import com.entity.view.EquipmentCommentbackView;
import org.apache.ibatis.annotations.Param;

/**
 * 玩具评价 Dao 接口
 *
 * @author 
 */
public interface EquipmentCommentbackDao extends BaseMapper<EquipmentCommentbackEntity> {

   List<EquipmentCommentbackView> selectListView(Pagination page, @Param("params")Map<String,Object> params);

}
