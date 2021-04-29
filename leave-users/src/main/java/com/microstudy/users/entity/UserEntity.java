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
@TableName("leave_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 
	 */
	private String userDepartment;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String userPosition;
	/**
	 * the application of ask for leave
	 */
	private String userTable;
	/**
	 * The rest date of leave
	 */
	private String userRest;

}
