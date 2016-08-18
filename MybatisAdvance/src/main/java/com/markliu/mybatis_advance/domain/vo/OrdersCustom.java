package com.markliu.mybatis_advance.domain.vo;

import com.markliu.mybatis_advance.domain.po.Orders;

/**
 * Author: markliu
 * Time  : 16-8-18 下午11:12
 */
public class OrdersCustom extends Orders {

	private String username;
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return super.toString() +
				"\nOrdersCustom{" +
				"username='" + username + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
