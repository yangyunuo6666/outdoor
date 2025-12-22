
package com.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.EquipmentEntity;
import com.entity.view.EquipmentOrderView;
import com.entity.view.EquipmentView;
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
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 设备
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    private static final String TABLE_NAME = "equipment";

    @Autowired
    private EquipmentService equipmentService;


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
    private EquipmentCollectionService equipmentCollectionService;//设备收藏
    @Autowired
    private EquipmentCommentbackService equipmentCommentbackService;//设备评价
    @Autowired
    private EquipmentOrderService equipmentOrderService;//设备订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("equipmentDeleteStart",1);params.put("equipmentDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = equipmentService.queryPage(params);

        //字典表数据转换
        List<EquipmentView> list =(List<EquipmentView>)page.getList();
        for(EquipmentView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        EquipmentEntity equipment = equipmentService.selectById(id);
        if(equipment !=null){
            //entity转view
            EquipmentView view = new EquipmentView();
            BeanUtils.copyProperties( equipment , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody EquipmentEntity equipment, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,equipment:{}",this.getClass().getName(),equipment.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<EquipmentEntity> queryWrapper = new EntityWrapper<EquipmentEntity>()
            .eq("equipment_name", equipment.getEquipmentName())
            .eq("equipment_types", equipment.getEquipmentTypes())
            .eq("equipment_kucun_number", equipment.getEquipmentKucunNumber())
            .eq("shangxia_types", equipment.getShangxiaTypes())
            .eq("equipment_delete", equipment.getEquipmentDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        EquipmentEntity equipmentEntity = equipmentService.selectOne(queryWrapper);
        if(equipmentEntity==null){
            equipment.setEquipmentClicknum(1);
            equipment.setShangxiaTypes(1);
            equipment.setEquipmentDelete(1);
            equipment.setInsertTime(new Date());
            equipment.setCreateTime(new Date());
            equipmentService.insert(equipment);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody EquipmentEntity equipment, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,equipment:{}",this.getClass().getName(),equipment.toString());
        EquipmentEntity oldEquipmentEntity = equipmentService.selectById(equipment.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(equipment.getEquipmentPhoto()) || "null".equals(equipment.getEquipmentPhoto())){
                equipment.setEquipmentPhoto(null);
        }

            equipmentService.updateById(equipment);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<EquipmentEntity> oldEquipmentList =equipmentService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        for(Integer id:ids){
            EquipmentEntity equipmentEntity = new EquipmentEntity();
            equipmentEntity.setId(id);
            equipmentEntity.setEquipmentDelete(2);
            list.add(equipmentEntity);
        }
        if(list != null && list.size() >0){
            equipmentService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<EquipmentEntity> equipmentList = new ArrayList<>();//上传的东西
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
                            EquipmentEntity equipmentEntity = new EquipmentEntity();
//                            equipmentEntity.setEquipmentUuidNumber(data.get(0));                    //设备编号 要改的
//                            equipmentEntity.setEquipmentName(data.get(0));                    //设备名称 要改的
//                            equipmentEntity.setEquipmentPhoto("");//详情和图片
//                            equipmentEntity.setEquipmentTypes(Integer.valueOf(data.get(0)));   //设备类型 要改的
//                            equipmentEntity.setEquipmentKucunNumber(Integer.valueOf(data.get(0)));   //设备库存 要改的
//                            equipmentEntity.setEquipmentNewMoney(data.get(0));                    //租赁价格/h 要改的
//                            equipmentEntity.setEquipmentClicknum(Integer.valueOf(data.get(0)));   //设备热度 要改的
//                            equipmentEntity.setEquipmentContent("");//详情和图片
//                            equipmentEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            equipmentEntity.setEquipmentDelete(1);//逻辑删除字段
//                            equipmentEntity.setInsertTime(date);//时间
//                            equipmentEntity.setCreateTime(date);//时间
                            equipmentList.add(equipmentEntity);


                            //把要查询是否重复的字段放入map中
                                //设备编号
                                if(seachFields.containsKey("equipmentUuidNumber")){
                                    List<String> equipmentUuidNumber = seachFields.get("equipmentUuidNumber");
                                    equipmentUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> equipmentUuidNumber = new ArrayList<>();
                                    equipmentUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("equipmentUuidNumber",equipmentUuidNumber);
                                }
                        }

                        //查询是否重复
                         //设备编号
                        List<EquipmentEntity> equipmentEntities_equipmentUuidNumber = equipmentService.selectList(new EntityWrapper<EquipmentEntity>().in("equipment_uuid_number", seachFields.get("equipmentUuidNumber")).eq("equipment_delete", 1));
                        if(equipmentEntities_equipmentUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(EquipmentEntity s:equipmentEntities_equipmentUuidNumber){
                                repeatFields.add(s.getEquipmentUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [设备编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        equipmentService.insertBatch(equipmentList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<EquipmentView> returnEquipmentViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = equipmentOrderService.queryPage(params1);
        List<EquipmentOrderView> orderViewsList =(List<EquipmentOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(EquipmentOrderView orderView:orderViewsList){
            Integer equipmentTypes = orderView.getEquipmentTypes();
            if(typeMap.containsKey(equipmentTypes)){
                typeMap.put(equipmentTypes,typeMap.get(equipmentTypes)+1);
            }else{
                typeMap.put(equipmentTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("equipmentTypes",type);
            PageUtils pageUtils1 = equipmentService.queryPage(params2);
            List<EquipmentView> equipmentViewList =(List<EquipmentView>)pageUtils1.getList();
            returnEquipmentViewList.addAll(equipmentViewList);
            if(returnEquipmentViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = equipmentService.queryPage(params);
        if(returnEquipmentViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnEquipmentViewList.size();//要添加的数量
            List<EquipmentView> equipmentViewList =(List<EquipmentView>)page.getList();
            for(EquipmentView equipmentView:equipmentViewList){
                Boolean addFlag = true;
                for(EquipmentView returnEquipmentView:returnEquipmentViewList){
                    if(returnEquipmentView.getId().intValue() ==equipmentView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnEquipmentViewList.add(equipmentView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnEquipmentViewList = returnEquipmentViewList.subList(0, limit);
        }

        for(EquipmentView c:returnEquipmentViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnEquipmentViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = equipmentService.queryPage(params);

        //字典表数据转换
        List<EquipmentView> list =(List<EquipmentView>)page.getList();
        for(EquipmentView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        EquipmentEntity equipment = equipmentService.selectById(id);
            if(equipment !=null){

                //点击数量加1
                equipment.setEquipmentClicknum(equipment.getEquipmentClicknum()+1);
                equipmentService.updateById(equipment);

                //entity转view
                EquipmentView view = new EquipmentView();
                BeanUtils.copyProperties( equipment , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody EquipmentEntity equipment, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,equipment:{}",this.getClass().getName(),equipment.toString());
        Wrapper<EquipmentEntity> queryWrapper = new EntityWrapper<EquipmentEntity>()
            .eq("equipment_uuid_number", equipment.getEquipmentUuidNumber())
            .eq("equipment_name", equipment.getEquipmentName())
            .eq("equipment_types", equipment.getEquipmentTypes())
            .eq("equipment_kucun_number", equipment.getEquipmentKucunNumber())
            .eq("equipment_clicknum", equipment.getEquipmentClicknum())
            .eq("shangxia_types", equipment.getShangxiaTypes())
            .eq("equipment_delete", equipment.getEquipmentDelete())
//            .notIn("equipment_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        EquipmentEntity equipmentEntity = equipmentService.selectOne(queryWrapper);
        if(equipmentEntity==null){
            equipment.setEquipmentClicknum(1);
            equipment.setEquipmentDelete(1);
            equipment.setInsertTime(new Date());
            equipment.setCreateTime(new Date());
        equipmentService.insert(equipment);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

