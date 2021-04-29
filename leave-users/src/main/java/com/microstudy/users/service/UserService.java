package com.microstudy.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.microstudy.users.entity.UserEntity;
import com.microstudy.users.utils.PageUtils;

import java.util.Map;

/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2020-12-06 20:31:26
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

