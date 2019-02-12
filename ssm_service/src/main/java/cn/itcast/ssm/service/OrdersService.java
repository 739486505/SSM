package cn.itcast.ssm.service;

import cn.itcast.ssm.Orders;

import java.util.List;

public interface OrdersService {



    List<Orders> findAll(int page,int Size);

    Orders findById(String id);
}
