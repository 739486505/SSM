package cn.itcast.ssm.service;

import cn.itcast.ssm.Permission;
import cn.itcast.ssm.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionToRole(String roleId, String[] ids);
}
