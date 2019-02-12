package cn.itcast.ssm.dao;

import cn.itcast.ssm.Member;
import cn.itcast.ssm.Orders;
import cn.itcast.ssm.Product;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {




    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",
                    one = @One(select = "cn.itcast.ssm.dao.ProductDao.findById"))
    })
    List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",javaType = Product.class,
                    one = @One(select = "cn.itcast.ssm.dao.ProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,
                    one = @One(select = "cn.itcast.ssm.dao.MemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.TravellerDao.findById"))
    })
    Orders findById(String id);
}
