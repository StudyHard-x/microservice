package com.microstudy.users.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author xch
 * @email 123456@gmail.com
 * @date 2021-02-23 14:24:00
 */
@Data
@TableName("leave_owncalendar")
public class OwncalendarEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 *
	 */
	private Date date;
	/**
	 *
	 */
	private String type;
	/**
	 *
	 */
	private Integer status;

}
