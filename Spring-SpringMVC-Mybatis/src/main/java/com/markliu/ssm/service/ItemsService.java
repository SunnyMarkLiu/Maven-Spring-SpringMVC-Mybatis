package com.markliu.ssm.service;

import com.markliu.ssm.po.ItemsCustom;
import com.markliu.ssm.po.ItemsCustomQueryVo;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 下午2:42
 */
public interface ItemsService {
	/**
	 * 获取 Items 列表
	 * @param itemsCustomQueryVo 封装针对 ItemsCustom 的复杂查询的 Vo 类
	 * @return Items 列表
	 * @throws Exception database exception
	 */
	List<ItemsCustom> getAllItemsLikeName(ItemsCustomQueryVo itemsCustomQueryVo) throws Exception;
}
