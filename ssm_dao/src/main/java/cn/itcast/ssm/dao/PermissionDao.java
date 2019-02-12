package cn.itcast.ssm.dao;

import cn.itcast.ssm.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findById(String id);

    @Select("select * from permission")
    List<Permission> findAll();


    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);

}
