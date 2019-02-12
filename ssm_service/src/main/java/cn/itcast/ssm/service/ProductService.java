package cn.itcast.ssm.service;

import cn.itcast.ssm.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    void save(Product product);

}
