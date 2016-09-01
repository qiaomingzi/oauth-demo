package com.monkeyk.sos.dao.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

import static com.monkeyk.sos.utils.CacheConstants.ACCESS_TOKEN_CACHE;
import static com.monkeyk.sos.utils.CacheConstants.REFRESH_TOKEN_CACHE;

/**
 * 2016/7/26
 * <p/>
 * 扩展默认的 TokenStore, 增加对缓存的支持
 *
 * @author Shengzhao Li
 */
public class CustomTokenStore extends JdbcTokenStore {
    private static final String insertAccessTokenSql = "insert into t_open_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String selectAccessTokenSql = "select token_id, token from t_open_access_token where token_id = ?";

    private static final String selectAccessTokenAuthenticationSql = "select token_id, authentication from t_open_access_token where token_id = ?";

    private static final String selectAccessTokenFromAuthenticationSql = "select token_id, token from t_open_access_token where authentication_id = ?";

    private static final String selectAccessTokensFromUserNameAndClientIdSql = "select token_id, token from t_open_access_token where user_name = ? and client_id = ?";

    private static final String selectAccessTokensFromUserNameSql = "select token_id, token from t_open_access_token where user_name = ?";

    private static final String selectAccessTokensFromClientIdSql = "select token_id, token from t_open_access_token where client_id = ?";

    private static final String deleteAccessTokenSql = "delete from t_open_access_token where token_id = ?";

    private static final String deleteAccessTokenFromRefreshTokenSql = "delete from t_open_access_token where refresh_token = ?";

    private static final String insertRefreshTokenSql = "insert into t_open_refresh_token (token_id, token, authentication) values (?, ?, ?)";

    private static final String selectRefreshTokenSql = "select token_id, token from t_open_refresh_token where token_id = ?";

    private static final String selectRefreshTokenAuthenticationSql = "select token_id, authentication from t_open_refresh_token where token_id = ?";

    private static final String deleteRefreshTokenSql = "delete from t_open_refresh_token where token_id = ?";


    public CustomTokenStore(DataSource dataSource) {
        super(dataSource);
        setInsertAccessTokenSql(insertAccessTokenSql);
        setSelectAccessTokenSql(selectAccessTokenSql);
        setDeleteAccessTokenSql(deleteAccessTokenSql);
        setInsertRefreshTokenSql(insertRefreshTokenSql);
        setSelectRefreshTokenSql(selectRefreshTokenSql);
        setDeleteRefreshTokenSql(deleteRefreshTokenSql);
        setSelectAccessTokenAuthenticationSql(selectAccessTokenAuthenticationSql);
        setSelectRefreshTokenAuthenticationSql(selectRefreshTokenAuthenticationSql);
        setSelectAccessTokenFromAuthenticationSql(selectAccessTokenFromAuthenticationSql);
        setDeleteAccessTokenFromRefreshTokenSql(deleteAccessTokenFromRefreshTokenSql);
        setSelectAccessTokensFromUserNameSql(selectAccessTokensFromUserNameSql);
        setSelectAccessTokensFromUserNameAndClientIdSql(selectAccessTokensFromUserNameAndClientIdSql);
        setSelectAccessTokensFromClientIdSql(selectAccessTokensFromClientIdSql);
    }


    @Cacheable(value = ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return super.readAccessToken(tokenValue);
    }


    @CacheEvict(value = ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public void removeAccessToken(String tokenValue) {
        super.removeAccessToken(tokenValue);
    }


    @Cacheable(value = REFRESH_TOKEN_CACHE, key = "#token")
    public OAuth2RefreshToken readRefreshToken(String token) {
        return super.readRefreshToken(token);
    }

    @CacheEvict(value = REFRESH_TOKEN_CACHE, key = "#token")
    public void removeRefreshToken(String token) {
        super.removeRefreshToken(token);
    }


}
