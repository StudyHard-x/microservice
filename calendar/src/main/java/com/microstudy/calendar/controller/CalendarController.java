package com.microstudy.calendar.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microstudy.calendar.entity.CalendarEntity;
import com.microstudy.calendar.service.CalendarService;
import com.microstudy.calendar.utils.PageUtils;
import com.microstudy.calendar.utils.R;



/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-02-18 16:15:54
 */
@RestController
@RequestMapping("calendar/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("calendar:calendar:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = calendarService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("calendar:calendar:info")
    public R info(@PathVariable("id") Integer id){
		CalendarEntity calendar = calendarService.getById(id);

        return R.ok().put("calendar", calendar);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("calendar:calendar:save")
    public R save(@RequestBody CalendarEntity calendar){
		calendarService.save(calendar);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("calendar:calendar:update")
    public R update(@RequestBody CalendarEntity calendar){
		calendarService.updateById(calendar);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("calendar:calendar:delete")
    public R delete(@RequestBody Integer[] ids){
		calendarService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/allCalendar")
    public R allCalendar(){
        List<CalendarEntity> calendarEntities = calendarService.getCalendarList();
        return R.ok().put("list", calendarEntities);
    }

//    @RequestMapping("/calendarList")
//    public List calendarMap(){
//        List<CalendarEntity> calendarEntities = calendarService.getCalendarList();
//        List resultList = new ArrayList<>();
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for(int it =0; it < calendarEntities.size(); it ++){
//            String type = calendarEntities.get(it).getType();
//            Date date = calendarEntities.get(it).getDate();
//            Integer status = calendarEntities.get(it).getStatus();
//
//            CalendarEntity calendarEntity = new CalendarEntity();
//            calendarEntity.setType(type);
//            calendarEntity.setDate(date);
//            calendarEntity.setStatus(status);
//            resultList.add(calendarEntity);
//        }
//        return resultList;
//    }
@RequestMapping("/calendarList")
public List<Map<String, Object>> calendarMap(){
    List<CalendarEntity> calendarEntities = calendarService.getCalendarList();
    List<Map<String, Object>> resultList = new ArrayList<>();
    for(int it =0; it < calendarEntities.size(); it ++){
        String type = calendarEntities.get(it).getType();
        Date date = calendarEntities.get(it).getDate();
        Integer status = calendarEntities.get(it).getStatus();
        Map map = new HashMap();
        map.put("type",type);
        map.put("date",date);
        map.put("status",status);

        resultList.add(map);
    }
    return resultList;
}

}
