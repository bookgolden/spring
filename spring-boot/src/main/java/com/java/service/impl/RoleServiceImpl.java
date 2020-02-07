package com.java.service.impl;

import com.java.bean.Role;
import com.java.jpa.RoleRepository;
import com.java.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoleByName(String roleName) {
        return roleRepository.getByParam2(roleName);
    }

}
