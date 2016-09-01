package com.monkeyk.sos.dao.service;

import com.monkeyk.sos.domain.Application;

import java.util.List;

/**
 * 处理 OAuth 相关业务的 Repository
 *
 * @author Shengzhao Li
 */
public interface AppRepository extends Repository {

    Application findOauthClientDetails(String appKey);

    List<Application> findAllOauthClientDetails();

    void updateOauthClientDetailsArchive(String appKey, String archive);

    void saveOauthClientDetails(Application application);

    /**
     * 修改appsecret
     * @param appKey
     * @param secret
     */
    public void updateAppSecret(String appKey, String secret);
}