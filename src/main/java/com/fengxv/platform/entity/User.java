package com.fengxv.platform.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@TableName(value = "user_t")
@Data
public class User {
    /**主键id,自增*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**用户名*/
    @TableField(value = "user_name")
    private String userName;

    /**电话号码*/
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**1:男，0，女*/
    private Integer sex;

    /**用户密码*/
    private String password;

    /**年龄*/
    private Integer age;

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getSex() { return sex; }

    public void setSex(Integer sex) { this.sex = sex; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
