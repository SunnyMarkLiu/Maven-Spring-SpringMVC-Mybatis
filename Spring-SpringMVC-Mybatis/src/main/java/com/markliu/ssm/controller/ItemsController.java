package com.markliu.ssm.controller;

import com.markliu.ssm.mapper.ItemsMapper;
import com.markliu.ssm.po.Items;
import com.markliu.ssm.po.ItemsCustom;
import com.markliu.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 下午4:21
 */
@Controller
@RequestMapping(value = "/items")
public class ItemsController {

	private ItemsService itemsService;

	@Autowired
	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	private ItemsMapper itemsMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setItemsMapper(ItemsMapper itemsMapper) {
		this.itemsMapper = itemsMapper;
	}

	@RequestMapping(value = "/query_items", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryItems() throws Exception {

		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items.toString());

		List<ItemsCustom> itemsCustomList = itemsService.getAllItemsLikeName(null);

		for (ItemsCustom itemsCustom : itemsCustomList) {
			System.out.println(itemsCustom.toString());
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsCustomList", itemsCustomList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
}
