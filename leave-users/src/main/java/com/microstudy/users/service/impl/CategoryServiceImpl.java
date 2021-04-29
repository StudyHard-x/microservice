package com.microstudy.users.service.impl;

import com.microstudy.users.utils.PageUtils;
import com.microstudy.users.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.microstudy.users.dao.CategoryDao;
import com.microstudy.users.entity.CategoryEntity;
import com.microstudy.users.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

//    @Autowired
//    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. find all category
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2. to tree structure
        // 2.1  1 level id is 0
        List<CategoryEntity> level1Menus = entities.stream().filter((categoryEntity) -> {
            return categoryEntity.getParentId() == 0;
        }).map((menu)->{
            menu.setChildren(getChildren(menu,entities));
            return menu;
        }).sorted((menu1,menu2)->{
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return level1Menus;
    }

    // 删除
    @Override
    public void removeCategoryByIds(List<Long> asList) {
        //检查是否引用
        //TODO
        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    // 递归查找子菜单
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter((categoryEntity) -> {
            return categoryEntity.getParentId() == root.getId();
        }).map(categoryEntity -> {
            // 1. 找到子菜单
            categoryEntity.setChildren(getChildren(categoryEntity,all));
            return categoryEntity;
            // 2. 排序
        }).sorted((menu1,menu2)->{
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return children;
    }

}
