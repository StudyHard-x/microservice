package com.microstudy.users;

import com.microstudy.users.entity.ApplicationEntity;
import com.microstudy.users.feign.CalendarFeignService;
import com.microstudy.users.service.ApplicationService;
//import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class LeaveUsersApplicationTests {

    @Autowired
    ApplicationService applicationService;
    @Autowired
    CalendarFeignService calendarFeignService;

    @Test
    void contextLoads() {
    }

    @Test
    void testList() {

        List<ApplicationEntity> applicationEntities = applicationService.getApplicationList(7);
        List<ApplicationEntity> applicationEntitiesList = new ArrayList<>();

        int i = 0;
        for(int it =0; it < applicationEntities.size(); it ++){
            System.out.println(applicationEntities.get(it).getCreateTime());

            String type = applicationEntities.get(it).getLeaveType();
            Date time = applicationEntities.get(it).getStartDate();
            Integer date = applicationEntities.get(it).getDate();
            Integer status = applicationEntities.get(it).getStatus();
            if (status == 2) {
                for (int j = 0; j < date; j ++){
                    ApplicationEntity applicationEntity = new ApplicationEntity();
                    applicationEntity.setId(i);
                    System.out.println("time is : " + time);
                    applicationEntity.setStartDate(time);
                    applicationEntity.setLeaveType(type);

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(time);
                    calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
                    time=calendar.getTime(); //这个时间就是日期往后推一天的结果

                    applicationEntitiesList.add(i, applicationEntity);
                    i += 1;
                }
            }
        }

        for(int k = 0; k < applicationEntitiesList.size(); k ++ ){
            System.out.println(applicationEntitiesList.get(k));
        }
        System.out.println(applicationEntitiesList);
    }

}
