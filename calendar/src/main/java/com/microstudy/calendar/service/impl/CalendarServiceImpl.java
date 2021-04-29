package com.microstudy.calendar.service.impl;

//import com.microstudy.common.to.CalendarTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microstudy.calendar.utils.PageUtils;
import com.microstudy.calendar.utils.Query;

import com.microstudy.calendar.dao.CalendarDao;
import com.microstudy.calendar.entity.CalendarEntity;
import com.microstudy.calendar.service.CalendarService;


@Service("calendarService")
public class CalendarServiceImpl extends ServiceImpl<CalendarDao, CalendarEntity> implements CalendarService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CalendarEntity> page = this.page(
                new Query<CalendarEntity>().getPage(params),
                new QueryWrapper<CalendarEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CalendarEntity> getCalendarList() {
        QueryWrapper<CalendarEntity> queryWrapper = new QueryWrapper<CalendarEntity>();
        List<CalendarEntity> calendarEntities = this.baseMapper.selectList(queryWrapper);
        return calendarEntities;
    }

}
