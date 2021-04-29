package com.microstudy.calendar.entity;

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
 * @date 2021-02-18 16:15:54
 */
@Data
@TableName("leave_calendar")
public class CalendarEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private Date createTime;
	/**
	 *
	 */
	private String type;
	/**
	 *
	 */
	private Date date;
	/**
	 *
	 */
	private Integer status;

}
