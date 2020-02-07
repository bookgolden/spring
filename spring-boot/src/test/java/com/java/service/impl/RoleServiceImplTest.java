package com.java.service.impl;

import com.java.bean.Role;
import com.java.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void getRoleByName() {
        List<Role> list = roleService.getRoleByName("一");
        list.forEach(System.out::println);
    }
}