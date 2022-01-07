package com.huyphungkien.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Accounts")
public class Account implements Serializable{
	@Id
	@Column(name="userName",nullable = false,length = 30)
	private String userName;
	@Column(name="passWord",nullable = false,length = 60)
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
