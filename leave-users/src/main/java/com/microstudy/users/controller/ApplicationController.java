package com.microstudy.users.controller;


import com.microstudy.users.entity.ApplicationEntity;
import com.microstudy.users.entity.OwncalendarEntity;
import com.microstudy.users.feign.CalendarFeignService;
import com.microstudy.users.service.ApplicationService;
import com.microstudy.users.utils.PageUtils;
import com.microstudy.users.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2020-12-06 20:31:26
 */
@RestController
@RequestMapping("users/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("users:application:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = applicationService.queryPage(params);

        return R.ok().put("page", page);
    }

    // 某用户的列表
    @RequestMapping("/listAll/{tableId}/{userId}")
//    @RequiresPermissions("users:application:list")
    public R listAll(@RequestParam Map<String, Object> params,
                     @PathVariable("tableId") Long tableId,
                     @PathVariable("userId") Integer userId){
//        PageUtils page = applicationService.queryPage(params);
        PageUtils page = applicationService.queryPage(params, tableId, userId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("users:application:info")
    public R info(@PathVariable("id") Integer id){
		ApplicationEntity application = applicationService.getById(id);

        return R.ok().put("application", application);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("users:application:save")
    public R save(@RequestBody ApplicationEntity application){
        Date startDate = application.getStartDate();
        Date endDate = application.getEndDate();
        try
        {
            long diff = endDate.getTime() - startDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            application.setDate((int) days + 1);
        } catch (Exception e) { }

		applicationService.save(application);
        return R.ok();
    }

    private void verifyApplication(ApplicationEntity app, Integer date){
        Date sDate = app.getStartDate();
        for (int i = 0; i < date; i ++){

        }

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("users:application:update")
    public R update(@RequestBody ApplicationEntity application){
        Date startDate = application.getStartDate();
        Date endDate = application.getEndDate();
        try
        {
            long diff = endDate.getTime() - startDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            application.setDate((int) days + 1);
        } catch (Exception e) { }
		applicationService.updateById(application);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
//    @RequiresPermissions("users:application:delete")
//    public R delete(@RequestBody Integer[] ids){
    public R delete(@PathVariable("id") Integer id){
//        applicationService.removeByIds(Arrays.asList(id));
        ApplicationEntity application = applicationService.getById(id);
        application.setShowStatus(0);
        applicationService.updateById(application);
        return R.ok();
    }

    @RequestMapping("/pass/{id}")
    public R passManagement(@PathVariable("id") Integer id){
        ApplicationEntity application = applicationService.getById(id);
        application.setStatus(2);
        applicationService.updateById(application);
        return R.ok();
    }

    @RequestMapping("/decline/{id}")
    public R declineManagement(@PathVariable("id") Integer id){
        ApplicationEntity application = applicationService.getById(id);
        application.setStatus(0);
        applicationService.updateById(application);
        return R.ok();
    }

    @Autowired
    CalendarFeignService calendarFeignService;
    @RequestMapping("/listCalendar/{userId}")
    public R listCalendar(@PathVariable("userId") Integer userId) throws ParseException {
//        List<ApplicationEntity> list = applicationService.getApplicationList(userId);
        List<ApplicationEntity> applicationEntities = applicationService.getApplicationList(userId);
        List<OwncalendarEntity> owncalendarEntityList = new ArrayList<>();
        List AllTime = new ArrayList<>();  // 存放所有时间

        //////
        int i = 0;
        for(int it =0; it < applicationEntities.size(); it ++){
//            System.out.println(applicationEntities.get(it).getCreateTime());

            String type = applicationEntities.get(it).getLeaveType();
            Date time = applicationEntities.get(it).getStartDate();
            Integer date = applicationEntities.get(it).getDate();

            Integer status = applicationEntities.get(it).getStatus();
            if (status == 2) {
                for (int j = 0; j < date; j ++){
                    OwncalendarEntity owncalendarEntity = new OwncalendarEntity();
                    owncalendarEntity.setId((long) i);
                    owncalendarEntity.setDate(time);
                    AllTime.add(time);
                    owncalendarEntity.setType(type);
                    owncalendarEntity.setStatus(status);

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(time);
                    calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
                    time=calendar.getTime(); //这个时间就是日期往后推一天的结果

                    owncalendarEntityList.add(i, owncalendarEntity);
                    i += 1;
                }
            }
        }
        ////////
        List<Map<String, Object>> list = calendarFeignService.getCalendarList();
        for(int it = 0; it < list.size(); it ++){

            String type = (String) list.get(it).get("type");
            String d = (String) list.get(it).get("date");
            String a = d.substring(0,10);
            Integer status = (Integer) list.get(it).get("status");

            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
            Date date=sim.parse(a);
            if (! AllTime.contains(date)){
                OwncalendarEntity owncalendarEntity = new OwncalendarEntity();
                owncalendarEntity.setId((long) i);
                owncalendarEntity.setDate(date);
                owncalendarEntity.setType(type);
                owncalendarEntity.setStatus(status);

                owncalendarEntityList.add(i, owncalendarEntity);
                i += 1;
            }
        }
//        System.out.println("owncalendarEntityList" + owncalendarEntityList);
        return R.ok().put("list", owncalendarEntityList);
    }

    @RequestMapping("/listAllCalendar")
    public R listAllCalendar() throws ParseException {

        List<OwncalendarEntity> test = new ArrayList<>();
        return R.ok().put("youhuiquan", test);
    }
}
