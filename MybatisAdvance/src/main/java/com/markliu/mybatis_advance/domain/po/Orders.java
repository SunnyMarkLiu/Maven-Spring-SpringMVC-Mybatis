package com.markliu.mybatis_advance.domain.po;

import java.util.Date;
import java.util.List;

public class Orders {

    private Integer id;
    private Integer userId;
    private String number;
    private Date createtime;
    private String note;

	// 订单关联的用户信息
	private User user;

	// 订单的详细信息列表
	private List<Orderdetail> orderdetails;

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

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"id=" + id +
				", userId=" + userId +
				", number='" + number + '\'' +
				", createtime=" + createtime +
				", note='" + note + '\'' +
				", user=" + user +
				", orderdetails=" + orderdetails.toString() +
				'}';
	}
}