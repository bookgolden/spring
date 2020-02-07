package com.java.jpa;

import com.java.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM cf_role WHERE  roleName=?1 AND roleType=?2 ", nativeQuery = true)
    List<Role> getByParam(String roleName, String roleType);

//@Query(value = "SELECT * FROM cf_role WHERE roleName like CONCAT('%',?1,'%')", nativeQuery = true)
//@Query(value = "select * FROM cf_role where roleName like :roleName%", nativeQuery = true)
//@Query(value = "select * FROM cf_role where roleName like %:roleName", nativeQuery = true)
//@Query(value = "select * FROM cf_role where roleName like %:roleName%", nativeQuery = true)
    @Query(value = "select * FROM cf_role where roleName like CONCAT('%',:roleName,'%')", nativeQuery = true)
    List<Role> getByParam2(@Param("roleName") String roleName);

}
