package com.learningcenter.product.controller;

import com.learningcenter.product.bean.Product;
import com.learningcenter.product.dao.ProductDao;
import com.learningcenter.product.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:
 *
 * @author Jason
 * @email 285290078@qq.com
 * @create 2018-06-13 19:27
 **/
@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/product/{id}")
    @ResponseBody
    public Response<Product> getProduct(@PathVariable("id") int id) {

        Product product = productDao.findById(id);

        return Response.success(product);

    }

    @GetMapping("/product/list")
    @ResponseBody
    public Response<List<Product>> getProduct() {

        List<Product> products = productDao.findAll();

        return Response.success(products);

    }

    @PostMapping("/product/update")
    public Response<Product> updateProduct(@RequestBody Product product) {

        productDao.update(product);

        return Response.success(product);

    }

    @PostMapping("/product/save")
    @ResponseBody
    public Response<Product> saveProduct(@RequestBody Product product) {

        productDao.add(product);

        return Response.success(product);

    }

    @DeleteMapping("/product/{id}")
    @ResponseBody
    public Response<Product> updateProduct(@PathVariable("id") int id) {

        productDao.delete(id);

        return Response.success(id);

    }


}
