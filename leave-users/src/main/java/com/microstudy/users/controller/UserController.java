package com.microstudy.users.controller;

import java.util.Arrays;
import java.util.Map;

import com.microstudy.users.utils.PageUtils;
import com.microstudy.users.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microstudy.users.entity.UserEntity;
import com.microstudy.users.service.UserService;




/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2020-12-06 20:31:26
 */
@RestController
@RequestMapping("users/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("users:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
//    @RequiresPermissions("users:user:info")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("users:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("users:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("users:user:delete")
    public R delete(@RequestBody Integer[] userIds){
		userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
