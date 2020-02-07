package com.java.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "cf_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增主键
    private long id;
    @Column(name = "rolename")
    private String roleName;
    @Column(name = "roletype")
    private String roleType;
    @Column(name = "isenable")
    private String isEnable;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
    @Column
    private Date recordUpdateDate;
    @Column
    private String merchantCode;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleType='" + roleType + '\'' +
                ", isEnable='" + isEnable + '\'' +
                '}';
    }
}
