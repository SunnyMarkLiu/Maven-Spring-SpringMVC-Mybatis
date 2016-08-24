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

	/**
	 * 根据 id 查询 Items，返回扩展的 ItemsCustom
	 * @param id 查询的 Items 的id
	 * @return	返回扩展的 ItemsCustom
	 * @throws Exception
	 */
	ItemsCustom getItemsCustomById(Integer id) throws Exception;

	/**
	 * 根据 id 更新 Items 商品信息
	 * @param id 待更新的商品的 id
	 * @param itemsCustom 更新的 Items
	 * @throws Exception
	 */
	void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
