package com.markliu.controller;

import com.markliu.model.Product;
import org.springframework.ui.Model;

/**
 * Author: markliu
 * Time  : 16-7-28 上午11:25
 */
public interface ProductController {

    String productInputPage();
    String productInput(String requestParam, String newrequestParam, Product product, Model model);
}
