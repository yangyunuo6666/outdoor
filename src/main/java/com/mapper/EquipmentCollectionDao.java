package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.EquipmentCollectionEntity;
import com.entity.view.EquipmentCollectionView;
import org.apache.ibatis.annotations.Param;

/**
 * 玩具收藏 Dao 接口
 *
 * @author 
 */
public interface  EquipmentCollectionDao extends BaseMapper<EquipmentCollectionEntity> {

   List<EquipmentCollectionView> selectListView(Pagination page, @Param("params")Map<String,Object> params);

}
