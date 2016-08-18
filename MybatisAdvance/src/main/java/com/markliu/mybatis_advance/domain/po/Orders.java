package com.markliu.mybatis_advance.domain.po;

import java.util.Date;

public class Orders {

    private Integer id;
    private Integer userId;
    private String number;
    private Date createtime;
    private String note;

	// 订单关联的用户信息
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"id=" + id +
				", userId=" + userId +
				", number='" + number + '\'' +
				", createtime=" + createtime +
				", note='" + note + '\'' +
				", user=" + user.toString() +
				'}';
	}
}