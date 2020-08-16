package cn.dreamchan.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 用户与岗位关联
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_post")
public class UserPostEntity implements Serializable {


	// 用户ID
	private Long userId;

	// 岗位ID
	private Long postId;

}