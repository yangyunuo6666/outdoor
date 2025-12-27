package com.controller;

import com.entity.EquipmentEntity;
import com.service.EquipmentStockWarningService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import com.utils.PageUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

@RestController
@RequestMapping("equipmentStockWarning")
public class EquipmentStockWarningController {
    @Autowired
    private EquipmentStockWarningService equipmentStockWarningService;

    /**
     * 分页查询预警设备
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentStockWarningService.queryWarningPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        EquipmentEntity equipment = equipmentStockWarningService.selectById(id);
        return R.ok().put("data", equipment);
    }
}