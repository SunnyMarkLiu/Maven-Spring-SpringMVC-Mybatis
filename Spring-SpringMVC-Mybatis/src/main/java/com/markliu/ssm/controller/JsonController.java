package com.markliu.ssm.controller;

import com.markliu.ssm.mapper.ItemsCustomMapper;
import com.markliu.ssm.mapper.ItemsMapper;
import com.markliu.ssm.po.Items;
import com.markliu.ssm.po.ItemsCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Json 请求与相应测试
 * Author: markliu
 * Time  : 16-8-26 上午10:10
 */
@Controller
@RequestMapping("/json")
public class JsonController {

	private ItemsMapper itemsMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setItemsMapper(ItemsMapper itemsMapper) {
		this.itemsMapper = itemsMapper;
	}

	/**
	 * 请求 json，响应 json
	 * '@RequestBody'： 将请求的 json 串转成 java 对象
	 * '@ResponseBody'： 将 java 对象转成 json 串进行响应
	 */
	@RequestMapping(value = "/requestJsonAndResponseJson", produces = "application/json")
	public @ResponseBody
	ItemsCustom requestJsonAndResponseJson(
			@RequestBody ItemsCustom itemsCustom) throws Exception {

		Items items = itemsMapper.selectByPrimaryKey(itemsCustom.getId());
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	/**
	 * 请求 key/value，响应 json
	 * '@ResponseBody'： 将 java 对象转成 json 串进行响应
	 */
	@RequestMapping(value = "/requestKeyValueAndResponseJson", produces = "application/json")
	public @ResponseBody
	ItemsCustom requestKeyValueAndResponseJson(ItemsCustom itemsCustom) throws Exception {

		// 其他业务处理
		return itemsCustom;
	}
}
