package com.markliu.ssm.po;

import java.util.Date;

public class Items {
	private Integer id;

	private String name;

	private Float price;

	private String picture;

	private Date createtime;

	private String detail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture == null ? null : picture.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	@Override
	public String toString() {
		return "Items{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", picture='" + picture + '\'' +
				", createtime=" + createtime +
				", detail='" + detail + '\'' +
				'}';
	}
}