package com.markliu.ssm.po;

/**
 * 封装针对 ItemsCustom 的复杂查询的 Vo 类，偏于系统的扩展
 * Author: markliu
 * Time  : 16-8-23 下午1:58
 */
public class ItemsCustomQueryVo {

	// 条件查询的扩展 Items
	private ItemsCustom itemsCustom;

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
}
