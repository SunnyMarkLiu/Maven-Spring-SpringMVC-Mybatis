package com.markliu.service;

import com.markliu.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Author: markliu
 * Time  : 16-7-31 下午1:53
 */
@Service("productService")
public class ProductService {

    private static final Log logger = LogFactory.getLog(ProductService.class);

    public void saveProduct(Product product) {
        logger.info("saveProduct" + product.toString());
    }

    public Product getProductById(int id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Get Springmvc");
        product.setPrice(100);
        logger.info("Service get Product:" + id);
        return product;
    }

}
