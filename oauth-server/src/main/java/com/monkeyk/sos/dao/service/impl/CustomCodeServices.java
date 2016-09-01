package com.monkeyk.sos.dao.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

import static com.monkeyk.sos.utils.CacheConstants.AUTHORIZATION_CODE_CACHE;

/**
 * 2016/7/23
 *
 * @author Shengzhao Li
 */
public class CustomCodeServices extends JdbcAuthorizationCodeServices {
    private static final String selectAuthenticationSql = "select code, authentication from t_open_oauth_code where code = ?";
    private static final String insertAuthenticationSql = "insert into t_open_oauth_code (code, authentication) values (?, ?)";
    private static final String deleteAuthenticationSql = "delete from t_open_oauth_code where code = ?";
    public CustomCodeServices(DataSource dataSource) {
        super(dataSource);
        setSelectAuthenticationSql(selectAuthenticationSql);
        setInsertAuthenticationSql(insertAuthenticationSql);
        setDeleteAuthenticationSql(deleteAuthenticationSql);
    }


    @Override
    @Cacheable(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    protected void store(String code, OAuth2Authentication authentication) {
        super.store(code, authentication);
    }

    @Override
    @CacheEvict(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    public OAuth2Authentication remove(String code) {
        return super.remove(code);
    }
}
