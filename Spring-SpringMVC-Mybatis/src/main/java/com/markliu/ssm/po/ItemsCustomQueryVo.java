package com.markliu.ssm.po;

import java.util.List;

/**
 * 封装针对 ItemsCustom 的复杂查询的 Vo 类，偏于系统的扩展
 * Author: markliu
 * Time  : 16-8-23 下午1:58
 */
public class ItemsCustomQueryVo {

	// 条件查询的扩展 Items
	private ItemsCustom itemsCustom;

	// 数据绑定到 list 集合中
	private List<ItemsCustom> itemsCustoms;

	public List<ItemsCustom> getItemsCustoms() {
		return itemsCustoms;
	}

	public void setItemsCustoms(List<ItemsCustom> itemsCustoms) {
		this.itemsCustoms = itemsCustoms;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
}
