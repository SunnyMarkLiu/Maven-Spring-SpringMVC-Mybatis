package com.markliu.controller.impl;

import com.markliu.controller.ProductController;
import com.markliu.model.Product;
import com.markliu.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: markliu
 * Time  : 16-7-28 上午11:27
 */
@Controller
@RequestMapping(value = "/product")
public class ProductControllerImpl implements ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product_input_page", method = {RequestMethod.POST, RequestMethod.GET})
    public String productInputPage() {
        logger.info("productInputPage called...");
        return "product_form";
    }

    // id 为路径变量
    @RequestMapping(value = "/product_view_page/{id}")
    public String productViewPage(@PathVariable int id, Model model) {
        logger.info("productViewPage called...");
        Product newproduct = productService.getProductById(id);
        model.addAttribute("newproduct", newproduct);
        return "product_details";
    }

    // requestParam : 请求参数
    @RequestMapping(value = "/product_input", method = {RequestMethod.POST, RequestMethod.GET})
    public String productInput(@RequestParam String requestParam, @RequestParam String newrequestParam, Product product, Model model) {

        logger.info("productInput called..." + requestParam + "-" + newrequestParam);
        productService.saveProduct(product);
        model.addAttribute("product", product);
        return "redirect:/product/product_view_page/" + product.getId();
    }
}
