package com.markliu.ssm.mapper;

import com.markliu.ssm.po.ItemsCustom;
import com.markliu.ssm.po.ItemsCustomQueryVo;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 下午1:52
 */
public interface ItemsCustomMapper {

	List<ItemsCustom> getAllItemsLikeName(ItemsCustomQueryVo itemsCustomQueryVo) throws Exception;
}
