package com.microstudy.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.microstudy.users.entity.CategoryEntity;
import com.microstudy.users.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-01-18 21:43:42
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeCategoryByIds(List<Long> asList);
}

