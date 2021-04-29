package com.microstudy.users.service.impl;

import com.microstudy.users.utils.PageUtils;
import com.microstudy.users.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.microstudy.users.dao.ApplicationDao;
import com.microstudy.users.entity.ApplicationEntity;
import com.microstudy.users.service.ApplicationService;


@Service("applicationService")
public class ApplicationServiceImpl extends ServiceImpl<ApplicationDao, ApplicationEntity> implements ApplicationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<ApplicationEntity> page = this.page(
                new Query<ApplicationEntity>().getPage(params),
                new QueryWrapper<ApplicationEntity>().like(StringUtils.isNotBlank(key),"user_name", key)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long TableId, Integer userId) {
        if (TableId == 3){
            String key = (String)params.get("key");

            IPage<ApplicationEntity> page = this.page(
                    new Query<ApplicationEntity>().getPage(params),
                    new QueryWrapper<ApplicationEntity>().like(StringUtils.isNotBlank(key),"user_name", key)
            );
            return new PageUtils(page);
        }else {
            String key = (String) params.get("key");
            QueryWrapper<ApplicationEntity> QueryWrapper = new QueryWrapper<ApplicationEntity>().eq("user_id", userId).eq("show_status",1);
            if(StringUtils.isEmpty(key)) {
                QueryWrapper.and((obj)->{
                    obj.like("user_name", key);
                });
            }

        IPage<ApplicationEntity> page = this.page(
                new Query<ApplicationEntity>().getPage(params),
                QueryWrapper);
//            Date time = page.getRecords().get(0).getCreateTime();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String cTime = formatter.format(time);
//            cTime = cTime.substring(0, 10) + ' ' + cTime.substring(11, 19);
//
//            Date date01;
//            try {
//                SimpleDateFormat format =
//                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                date01 = format.parse(cTime);
//            }
//            catch(ParseException pe) {
//                throw new IllegalArgumentException();
//            }
////            page.getRecords().get(0).setCreateTime(date01);
//            System.out.print("c time is " +  date01);
            return new PageUtils(page);
        }
    }

    @Override
    public List<ApplicationEntity> getApplicationList(Integer userId) {
        QueryWrapper<ApplicationEntity> QueryWrapper = new QueryWrapper<ApplicationEntity>().eq("user_id", userId);

        List<ApplicationEntity> applicationEntities = this.baseMapper.selectList(QueryWrapper);
        return applicationEntities;
    }



}
