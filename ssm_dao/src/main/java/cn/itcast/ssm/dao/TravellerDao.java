package cn.itcast.ssm.dao;

import cn.itcast.ssm.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {


    @Select(" select * from traveller where id in(select travellerId from order_traveller where orderId=#{id}) ")
    List<Traveller> findById(String id);

}
