
package com.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.view.EquipmentCollectionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;

import com.service.TokenService;
import com.utils.*;

import com.service.DictionaryService;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;


@Api(tags = "设备收藏接口")
@RestController
@Controller
@RequestMapping("/equipmentCollection")
public class EquipmentCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentCollectionController.class);

    private static final String TABLE_NAME = "equipmentCollection";

    @Autowired
    private EquipmentCollectionService equipmentCollectionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private LiuyanService liuyanService;//留言板
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private EquipmentService equipmentService;//设备
    @Autowired
    private EquipmentCommentbackService equipmentCommentbackService;//设备评价
    @Autowired
    private EquipmentOrderService equipmentOrderService;//设备订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    @ApiOperation(value = "后端列表")
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = equipmentCollectionService.queryPage(params);

        //字典表数据转换
        List<EquipmentCollectionView> list =(List<EquipmentCollectionView>)page.getList();
        for(EquipmentCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    @ApiOperation(value = "后端详情")
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        EquipmentCollectionEntity equipmentCollection = equipmentCollectionService.selectById(id);
        if(equipmentCollection !=null){
            //entity转view
            EquipmentCollectionView view = new EquipmentCollectionView();
            BeanUtils.copyProperties( equipmentCollection , view );//把实体数据重构到view中
            //级联表 设备
            //级联表
            EquipmentEntity equipment = equipmentService.selectById(equipmentCollection.getEquipmentId());
            if(equipment != null){
            BeanUtils.copyProperties( equipment , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setEquipmentId(equipment.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(equipmentCollection.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    @ApiOperation(value = "后端保存")
    @RequestMapping("/save")
    public R save(@RequestBody EquipmentCollectionEntity equipmentCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,equipmentCollection:{}",this.getClass().getName(),equipmentCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            equipmentCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<EquipmentCollectionEntity> queryWrapper = new EntityWrapper<EquipmentCollectionEntity>()
            .eq("equipment_id", equipmentCollection.getEquipmentId())
            .eq("yonghu_id", equipmentCollection.getYonghuId())
            .eq("equipment_collection_types", equipmentCollection.getEquipmentCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        EquipmentCollectionEntity equipmentCollectionEntity = equipmentCollectionService.selectOne(queryWrapper);
        if(equipmentCollectionEntity==null){
            equipmentCollection.setInsertTime(new Date());
            equipmentCollection.setCreateTime(new Date());
            equipmentCollectionService.insert(equipmentCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    @ApiOperation(value = "后端修改")
    @RequestMapping("/update")
    public R update(@RequestBody EquipmentCollectionEntity equipmentCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,equipmentCollection:{}",this.getClass().getName(),equipmentCollection.toString());
        EquipmentCollectionEntity oldEquipmentCollectionEntity = equipmentCollectionService.selectById(equipmentCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            equipmentCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            equipmentCollectionService.updateById(equipmentCollection);//根据id更新
            return R.ok();
    }



    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<EquipmentCollectionEntity> oldEquipmentCollectionList =equipmentCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        equipmentCollectionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    @ApiOperation(value = "批量上传")
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<EquipmentCollectionEntity> equipmentCollectionList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            EquipmentCollectionEntity equipmentCollectionEntity = new EquipmentCollectionEntity();
//                            equipmentCollectionEntity.setEquipmentId(Integer.valueOf(data.get(0)));   //设备 要改的
//                            equipmentCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            equipmentCollectionEntity.setEquipmentCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            equipmentCollectionEntity.setInsertTime(date);//时间
//                            equipmentCollectionEntity.setCreateTime(date);//时间
                            equipmentCollectionList.add(equipmentCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        equipmentCollectionService.insertBatch(equipmentCollectionList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    @ApiOperation(value = "前端列表")
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = equipmentCollectionService.queryPage(params);

        //字典表数据转换
        List<EquipmentCollectionView> list =(List<EquipmentCollectionView>)page.getList();
        for(EquipmentCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }


    @ApiOperation(value = "前端详情")
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        EquipmentCollectionEntity equipmentCollection = equipmentCollectionService.selectById(id);
            if(equipmentCollection !=null){


                //entity转view
                EquipmentCollectionView view = new EquipmentCollectionView();
                BeanUtils.copyProperties( equipmentCollection , view );//把实体数据重构到view中

                //级联表
                    EquipmentEntity equipment = equipmentService.selectById(equipmentCollection.getEquipmentId());
                if(equipment != null){
                    BeanUtils.copyProperties( equipment , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setEquipmentId(equipment.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(equipmentCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    @ApiOperation(value = "前端保存")
    @RequestMapping("/add")
    public R add(@RequestBody EquipmentCollectionEntity equipmentCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,equipmentCollection:{}",this.getClass().getName(),equipmentCollection.toString());
        Wrapper<EquipmentCollectionEntity> queryWrapper = new EntityWrapper<EquipmentCollectionEntity>()
            .eq("equipment_id", equipmentCollection.getEquipmentId())
            .eq("yonghu_id", equipmentCollection.getYonghuId())
            .eq("equipment_collection_types", equipmentCollection.getEquipmentCollectionTypes())
//            .notIn("equipment_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        EquipmentCollectionEntity equipmentCollectionEntity = equipmentCollectionService.selectOne(queryWrapper);
        if(equipmentCollectionEntity==null){
            equipmentCollection.setInsertTime(new Date());
            equipmentCollection.setCreateTime(new Date());
        equipmentCollectionService.insert(equipmentCollection);

            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

