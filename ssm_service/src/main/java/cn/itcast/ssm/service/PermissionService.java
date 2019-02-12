package cn.itcast.ssm.service;


import cn.itcast.ssm.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
