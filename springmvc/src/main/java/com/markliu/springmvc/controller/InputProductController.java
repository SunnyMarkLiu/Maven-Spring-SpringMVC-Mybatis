package com.markliu.springmvc.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Author: markliu
 * Time  : 16-7-25 下午10:18
 */
public class InputProductController implements Controller{


    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("InputProductController handleRequest...");
        return new ModelAndView("product_form");
    }
}
