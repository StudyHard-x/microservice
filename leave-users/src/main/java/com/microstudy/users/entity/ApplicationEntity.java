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
 * @date 2020-12-06 20:31:26
 */
@Data
@TableName("leave_application")
public class ApplicationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private Integer userId;
	/**
	 *
	 */
	private String userName;
	/**
	 *
	 */
	private String userDepartment;
	/**
	 * the time that table created
	 */
	private Date createTime;
	/**
	 * the time that application finished
	 */
	private Date endTime;
	/**
	 *
	 */
	private String leaveType;
	/**
	 *
	 */
	private String leaveReason;
	/**
	 *
	 */
	private Date startDate;
	/**
	 *
	 */
	private Date endDate;
	/**
	 *
	 */
	private Integer date;
	/**
	 *
	 */
	private String file;

	private Integer status;

	private Integer showStatus;

}
