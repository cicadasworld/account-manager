package com.jin.bean.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hujin
 * @version 2022/1/28
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<Account> accounts;

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", accounts=" + accounts +
                ", roles=" + roles +
                '}';
    }
}
