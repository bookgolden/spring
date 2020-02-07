package com.java.service;

import com.java.bean.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoleByName(String roleName);

}
