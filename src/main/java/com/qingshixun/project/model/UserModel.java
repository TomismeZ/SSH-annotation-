package com.qingshixun.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "t_user")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserModel {
	// 实体主键（自增长）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 性别(枚举值)
	public enum Gender {
		male, female;
	}

	// 用户名称（不可以为空）
	@Column(length = 100, nullable = false)
	private String name;

	// 用户性别(不可以为空)
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender = Gender.male;

	// 用户登录密码(不可以为空)
	@Column(length = 100, nullable = false)
	private String password;

	// 再次输入登录密码
	@Transient // 不作为数据库持久化字段
	private String passwordAgain;

	// 用户邮箱（可以为空）
	@Column(length = 200, nullable = true)
	private String email;

	// 创建时间(updateable=false表示编辑后，不更新此字段)
	@Column(nullable = true, length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE)
	private Date creatTime = new Date(System.currentTimeMillis());

	// 最后更新时间
	@Column(nullable = true, length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE)
	private Date updateTime = new Date(System.currentTimeMillis());

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", gender=" + gender + ", password=" + password
				+ ", passwordAgain=" + passwordAgain + ", email=" + email + ", creatTime=" + creatTime + ", updateTime="
				+ updateTime + "]";
	}

	
}