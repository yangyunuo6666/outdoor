
package com.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.view.EquipmentOrderView;
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
import com.entity.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 设备订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/equipmentOrder")
public class EquipmentOrderController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentOrderController.class);

    private static final String TABLE_NAME = "equipmentOrder";

    @Autowired
    private EquipmentOrderService equipmentOrderService;


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
    private EquipmentCollectionService equipmentCollectionService;//设备收藏
    @Autowired
    private EquipmentCommentbackService equipmentCommentbackService;//设备评价
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
        CommonUtil.checkMap(params);
        PageUtils page = equipmentOrderService.queryPage(params);

        //字典表数据转换
        List<EquipmentOrderView> list =(List<EquipmentOrderView>)page.getList();
        for(EquipmentOrderView c:list){
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
        EquipmentOrderEntity equipmentOrder = equipmentOrderService.selectById(id);
        if(equipmentOrder !=null){
            //entity转view
            EquipmentOrderView view = new EquipmentOrderView();
            BeanUtils.copyProperties( equipmentOrder , view );//把实体数据重构到view中
            //级联表 设备
            //级联表
            EquipmentEntity equipment = equipmentService.selectById(equipmentOrder.getEquipmentId());
            if(equipment != null){
            BeanUtils.copyProperties( equipment , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setEquipmentId(equipment.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(equipmentOrder.getYonghuId());
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

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody EquipmentOrderEntity equipmentOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,equipmentOrder:{}",this.getClass().getName(),equipmentOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            equipmentOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        equipmentOrder.setCreateTime(new Date());
        equipmentOrder.setInsertTime(new Date());
        equipmentOrderService.insert(equipmentOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody EquipmentOrderEntity equipmentOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,equipmentOrder:{}",this.getClass().getName(),equipmentOrder.toString());
        EquipmentOrderEntity oldEquipmentOrderEntity = equipmentOrderService.selectById(equipmentOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            equipmentOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            equipmentOrderService.updateById(equipmentOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<EquipmentOrderEntity> oldEquipmentOrderList =equipmentOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        equipmentOrderService.deleteBatchIds(Arrays.asList(ids));

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
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<EquipmentOrderEntity> equipmentOrderList = new ArrayList<>();//上传的东西
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
                            EquipmentOrderEntity equipmentOrderEntity = new EquipmentOrderEntity();
//                            equipmentOrderEntity.setEquipmentOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            equipmentOrderEntity.setEquipmentId(Integer.valueOf(data.get(0)));   //设备 要改的
//                            equipmentOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            equipmentOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            equipmentOrderEntity.setEquipmentOrderNumber(Integer.valueOf(data.get(0)));   //租赁时间/h 要改的
//                            equipmentOrderEntity.setEquipmentOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            equipmentOrderEntity.setEquipmentOrderTime(sdf.parse(data.get(0)));          //开始租赁时间 要改的
//                            equipmentOrderEntity.setEquipmentOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            equipmentOrderEntity.setEquipmentOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            equipmentOrderEntity.setInsertTime(date);//时间
//                            equipmentOrderEntity.setCreateTime(date);//时间
                            equipmentOrderList.add(equipmentOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("equipmentOrderUuidNumber")){
                                    List<String> equipmentOrderUuidNumber = seachFields.get("equipmentOrderUuidNumber");
                                    equipmentOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> equipmentOrderUuidNumber = new ArrayList<>();
                                    equipmentOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("equipmentOrderUuidNumber",equipmentOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<EquipmentOrderEntity> equipmentOrderEntities_equipmentOrderUuidNumber = equipmentOrderService.selectList(new EntityWrapper<EquipmentOrderEntity>().in("equipment_order_uuid_number", seachFields.get("equipmentOrderUuidNumber")));
                        if(equipmentOrderEntities_equipmentOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(EquipmentOrderEntity s:equipmentOrderEntities_equipmentOrderUuidNumber){
                                repeatFields.add(s.getEquipmentOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        equipmentOrderService.insertBatch(equipmentOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = equipmentOrderService.queryPage(params);

        //字典表数据转换
        List<EquipmentOrderView> list =(List<EquipmentOrderView>)page.getList();
        for(EquipmentOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        EquipmentOrderEntity equipmentOrder = equipmentOrderService.selectById(id);
            if(equipmentOrder !=null){


                //entity转view
                EquipmentOrderView view = new EquipmentOrderView();
                BeanUtils.copyProperties( equipmentOrder , view );//把实体数据重构到view中

                //级联表
                    EquipmentEntity equipment = equipmentService.selectById(equipmentOrder.getEquipmentId());
                if(equipment != null){
                    BeanUtils.copyProperties( equipment , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setEquipmentId(equipment.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(equipmentOrder.getYonghuId());
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


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody EquipmentOrderEntity equipmentOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,equipmentOrder:{}",this.getClass().getName(),equipmentOrder.toString());
            EquipmentEntity equipmentEntity = equipmentService.selectById(equipmentOrder.getEquipmentId());
            if(equipmentEntity == null){
                return R.error(511,"查不到该设备");
            }
            // Double equipmentNewMoney = equipmentEntity.getEquipmentNewMoney();

            if(false){
            }
            else if(equipmentEntity.getEquipmentNewMoney() == null){
                return R.error(511,"租赁价格/h不能为空");
            }
            else if((equipmentEntity.getEquipmentKucunNumber() -equipmentOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - equipmentEntity.getEquipmentNewMoney()*equipmentOrder.getBuyNumber() * equipmentOrder.getEquipmentOrderNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            equipmentOrder.setEquipmentOrderTypes(101); //设置订单状态为已租赁
            equipmentOrder.setEquipmentOrderTruePrice(equipmentEntity.getEquipmentNewMoney()*equipmentOrder.getBuyNumber()); //设置实付价格
            equipmentOrder.setYonghuId(userId); //设置订单支付人id
            equipmentOrder.setEquipmentOrderUuidNumber(String.valueOf(new Date().getTime()));
            equipmentOrder.setEquipmentOrderPaymentTypes(1);
            equipmentOrder.setInsertTime(new Date());
            equipmentOrder.setCreateTime(new Date());
            equipmentOrder.setEquipmentOrderNumber(equipmentOrder.getEquipmentOrderNumber());
                equipmentEntity.setEquipmentKucunNumber( equipmentEntity.getEquipmentKucunNumber() -equipmentOrder.getBuyNumber());
                equipmentService.updateById(equipmentEntity);
                equipmentOrderService.insert(equipmentOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            EquipmentOrderEntity equipmentOrder = equipmentOrderService.selectById(id);//当前表service
            Integer buyNumber = equipmentOrder.getBuyNumber();
            Integer equipmentOrderPaymentTypes = equipmentOrder.getEquipmentOrderPaymentTypes();
            Integer equipmentId = equipmentOrder.getEquipmentId();
            if(equipmentId == null)
                return R.error(511,"查不到该设备");
            EquipmentEntity equipmentEntity = equipmentService.selectById(equipmentId);
            if(equipmentEntity == null)
                return R.error(511,"查不到该设备");
            Double equipmentNewMoney = equipmentEntity.getEquipmentNewMoney();
            if(equipmentNewMoney == null)
                return R.error(511,"设备价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

            //判断是什么支付方式 1代表余额 2代表积分
            if(equipmentOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = equipmentEntity.getEquipmentNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }

            equipmentEntity.setEquipmentKucunNumber(equipmentEntity.getEquipmentKucunNumber() + buyNumber);


            equipmentOrder.setEquipmentOrderTypes(102);//设置订单状态为已退款
            equipmentOrderService.updateById(equipmentOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            equipmentService.updateById(equipmentEntity);//更新订单中设备的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer equipmentCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            EquipmentOrderEntity equipmentOrder = equipmentOrderService.selectById(id);
        if(equipmentOrder == null)
            return R.error(511,"查不到该订单");
        Integer equipmentId = equipmentOrder.getEquipmentId();
        if(equipmentId == null)
            return R.error(511,"查不到该设备");

        EquipmentCommentbackEntity equipmentCommentbackEntity = new EquipmentCommentbackEntity();
            equipmentCommentbackEntity.setId(id);
            equipmentCommentbackEntity.setEquipmentId(equipmentId);
            equipmentCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            equipmentCommentbackEntity.setEquipmentCommentbackText(commentbackText);
            equipmentCommentbackEntity.setInsertTime(new Date());
            equipmentCommentbackEntity.setReplyText(null);
            equipmentCommentbackEntity.setUpdateTime(null);
            equipmentCommentbackEntity.setCreateTime(new Date());
            equipmentCommentbackService.insert(equipmentCommentbackEntity);

            equipmentOrder.setEquipmentOrderTypes(105);//设置订单状态为已评价
            equipmentOrderService.updateById(equipmentOrder);//根据id更新
            return R.ok();
    }

    /**
     * 完成
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        EquipmentOrderEntity  equipmentOrderEntity = equipmentOrderService.selectById(id);
        equipmentOrderEntity.setEquipmentOrderTypes(103);//设置订单状态为已完成
        equipmentOrderService.updateById( equipmentOrderEntity);

        return R.ok();
    }


}

