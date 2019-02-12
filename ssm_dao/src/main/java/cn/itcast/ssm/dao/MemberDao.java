package cn.itcast.ssm.dao;

import cn.itcast.ssm.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    @Select("select * from member where id=#{id}")
    Member findById(String id);

}
