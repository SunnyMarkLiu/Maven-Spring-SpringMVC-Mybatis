package com.markliu.ssm.service.impl;

import com.markliu.ssm.mapper.ItemsCustomMapper;
import com.markliu.ssm.po.ItemsCustom;
import com.markliu.ssm.po.ItemsCustomQueryVo;
import com.markliu.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 下午2:43
 */
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

	// 注意 itemsCustomMapper 已通过包自动扫描的方式注入到 IoC 容器中，
	// 所以此处可以通过 Autowired 自动注入
	private ItemsCustomMapper itemsCustomMapper;

	@Autowired
	public void setItemsCustomMapper(ItemsCustomMapper itemsCustomMapper) {
		this.itemsCustomMapper = itemsCustomMapper;
	}

	public List<ItemsCustom> getAllItemsLikeName(ItemsCustomQueryVo itemsCustomQueryVo)
			throws Exception {
		// 调用 dao 层的ItemsCustomMapper
		return itemsCustomMapper.getAllItemsLikeName(itemsCustomQueryVo);
	}
}
