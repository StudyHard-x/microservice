package com.microstudy.users.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-01-18 21:43:42
 */
@Data
@TableName("leave_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * name
	 */
	private String name;
	/**
	 *
	 */
	private Long parentId;
	/**
	 *
	 */
	private Integer level;
	/**
	 * 是否显示 0,1
	 */
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 *
	 */
	private Integer sort;
	/**
	 *
	 */
	private String icon;

	@TableField(exist = false)
	private List<CategoryEntity> children;

}
