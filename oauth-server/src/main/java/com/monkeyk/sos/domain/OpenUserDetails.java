package com.monkeyk.sos.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security中的 UserDetails 实现
 *
 * @author Shengzhao Li
 */
public class OpenUserDetails implements UserDetails {

    private static final long serialVersionUID = 3957586021470480642L;

    protected static final String ROLE_PREFIX = "ROLE_";

    protected User user;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList();

    public OpenUserDetails() {
    }

    public OpenUserDetails(User user) {
        this.user = user;
        initialAuthorities();
    }
    enum Privilege {
        USER,          //Default privilege
        UNITY,
        MOBILE
    }
    private void initialAuthorities() {
        //Default, everyone have it
        this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name()));
        //default user have all privileges
        this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.UNITY.name()));
        this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.MOBILE.name()));
        Integer[] roleIds = user.getRoleIds();
        if (roleIds != null) {
            for (Integer privilege : roleIds) {
                this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege));
            }
        }
    }

    /**
     * Return authorities, more information see {@link #initialAuthorities()}
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User user() {
        return user;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}