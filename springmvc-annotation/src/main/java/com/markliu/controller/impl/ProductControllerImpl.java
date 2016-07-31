package com.markliu.controller.impl;

import com.markliu.controller.ProductController;
import com.markliu.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: markliu
 * Time  : 16-7-28 上午11:27
 */
@Controller(value = "productController")
@RequestMapping(value = "/product")
public class ProductControllerImpl implements ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    @RequestMapping(value = "/product_input_page", method = {RequestMethod.POST, RequestMethod.GET})
    public String productInputPage() {
        logger.info("productInputPage called...");
        System.out.println("productInputPage called...");
        return "product_form";
    }

    @RequestMapping(value = "/product_input", method = {RequestMethod.POST, RequestMethod.GET})
    public String productInput(Product product, Model model) {

        logger.info("productInput called...");
        System.out.println(product.toString());
        model.addAttribute("product", product);
        return "product_details";
    }
}
