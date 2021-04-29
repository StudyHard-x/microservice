package com.microstudy.users.feign;

import com.microstudy.users.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

//注册的服务名
@FeignClient(value = "calendar")
public interface CalendarFeignService {
    //对应的方法
    @RequestMapping("calendar/calendar/allCalendar")
    public R allCalendar();

    @RequestMapping("calendar/calendar/calendarList")
    public List<Map<String, Object>> getCalendarList();
}
