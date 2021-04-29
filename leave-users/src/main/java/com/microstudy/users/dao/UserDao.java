package com.microstudy.users.dao;

import com.microstudy.users.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author xch
 * @email 123456@gmail.com
 * @date 2020-12-06 20:31:26
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
