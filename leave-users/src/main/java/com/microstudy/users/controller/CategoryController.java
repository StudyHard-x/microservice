package com.microstudy.users.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.microstudy.users.utils.PageUtils;
import com.microstudy.users.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microstudy.users.entity.CategoryEntity;
import com.microstudy.users.service.CategoryService;




/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-01-18 21:43:42
 */
@RestController
@RequestMapping("users/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
    public R list(){
        List<CategoryEntity> entities = categoryService.listWithTree();
        return R.ok().put("data",entities);
    }
    @RequestMapping("/list")
//    @RequiresPermissions("users:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("users:category:info")
    public R info(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("users:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("users:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("users:category:delete")
    public R delete(@RequestBody Long[] ids){
//		categoryService.removeByIds(Arrays.asList(ids));
        // 检测categories 是否被引用
        categoryService.removeCategoryByIds(Arrays.asList(ids));

        return R.ok();
    }

}
