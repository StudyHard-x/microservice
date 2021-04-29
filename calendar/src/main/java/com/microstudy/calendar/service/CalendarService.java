package com.microstudy.calendar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.microstudy.calendar.utils.PageUtils;
import com.microstudy.calendar.entity.CalendarEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-02-18 16:15:54
 */
public interface CalendarService extends IService<CalendarEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CalendarEntity> getCalendarList();
}

