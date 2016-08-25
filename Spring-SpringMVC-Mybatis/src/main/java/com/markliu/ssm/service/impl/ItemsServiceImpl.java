package com.markliu.ssm.service.impl;

import com.markliu.ssm.exception.ItemsException;
import com.markliu.ssm.mapper.ItemsCustomMapper;
import com.markliu.ssm.mapper.ItemsMapper;
import com.markliu.ssm.po.Items;
import com.markliu.ssm.po.ItemsCustom;
import com.markliu.ssm.po.ItemsCustomQueryVo;
import com.markliu.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setItemsCustomMapper(ItemsCustomMapper itemsCustomMapper) {
		this.itemsCustomMapper = itemsCustomMapper;
	}

	public List<ItemsCustom> getAllItemsLikeName(ItemsCustomQueryVo itemsCustomQueryVo)
			throws Exception {
		// 调用 dao 层的ItemsCustomMapper
		return itemsCustomMapper.getAllItemsLikeName(itemsCustomQueryVo);
	}

	private ItemsMapper itemsMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setItemsMapper(ItemsMapper itemsMapper) {
		this.itemsMapper = itemsMapper;
	}

	public ItemsCustom getItemsCustomById(Integer id) throws Exception {
		// 参数校验
		Assert.notNull(id, "query id is null");

		Items items = itemsMapper.selectByPrimaryKey(id);

		if (items == null) {
			throw new ItemsException("查询的该商品不存在！");
		}

		// 中间对商品信息进行业务处理
		// ...

		ItemsCustom itemsCustom = new ItemsCustom();
		BeanUtils.copyProperties(items, itemsCustom);

		return itemsCustom;
	}

	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 参数校验
		Assert.notNull(id, "query id is null");
		Assert.notNull(itemsCustom, "update itemsCustom is null");

		// 防止 itemsCustom 未设置 id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKey(itemsCustom);
	}
}
