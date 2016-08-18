package com.markliu.mybatis_advance.mapper;

import com.markliu.mybatis_advance.domain.po.Orders;
import com.markliu.mybatis_advance.domain.vo.OrdersCustom;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-18 下午5:49
 */
public interface OrdersMapper {

	void insertOrders(Orders orders) throws Exception;
	List<OrdersCustom> selectOrdersAndUser() throws Exception;
}
