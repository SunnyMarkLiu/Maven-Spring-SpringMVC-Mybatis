package com.markliu.springmvc_basicflow.controller;

import com.markliu.springmvc_basicflow.pojo.Items;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理器适配值调用该 Handler，用于设置模型数据跳转视图
 * Author: markliu
 * Time  : 16-8-3 下午2:19
 */
public class ItemsControllerHandler implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 调用 service -> dao 查找数据库

        List<Items> itemsList = new ArrayList<Items>();

        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        //返回的 ModelAndView
        ModelAndView modelAndView =  new ModelAndView();
        //相当于 request的setAttribut，在 jsp 页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);
        //指定视图
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }
}
