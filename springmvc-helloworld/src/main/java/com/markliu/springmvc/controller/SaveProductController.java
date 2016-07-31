package com.markliu.springmvc.controller;

import com.markliu.springmvc.domain.Product;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Author: markliu
 * Time  : 16-7-25 下午9:53
 */
public class SaveProductController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 封装请求参数
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        System.out.println(product.toString());
        return new ModelAndView("product_details", "product", product);
    }
}
