package com.monkeyk.sos.service.impl;

import com.monkeyk.sos.domain.Application;
import com.monkeyk.sos.dao.service.AppRepository;
import com.monkeyk.sos.domain.OpenAppDetails;
import com.monkeyk.sos.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.monkeyk.sos.utils.CacheConstants.CLIENT_DETAILS_CACHE;

/**
 * OAuth 业务处理服务对象, 事务拦截也加在这一层
 *
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @Autowired
    private AppRepository oauthRepository;

    @Override
    public Application loadOauthClientDetails(String appKey) {
        return oauthRepository.findOauthClientDetails(appKey);
    }

    /**
     * 加载所有应用
     * @return
     */
    @Override
    public List<Application> loadAllOauthClientDetailsDtos() {
        List<Application> clientDetailses = oauthRepository.findAllOauthClientDetails();
        return clientDetailses;
    }

    /**
     * 删除应用
     * @param appKey
     */
    @Override
    public void archiveOauthClientDetails(String appKey) {
        oauthRepository.updateOauthClientDetailsArchive(appKey, "003");
    }

    /**
     * 根据appKey获取应用
     * @param appKey
     * @return
     */
    @Override
    public Application loadOauthClientDetailsDto(String appKey) {
          Application oauthClientDetails = oauthRepository.findOauthClientDetails(appKey);
        return oauthClientDetails;
    }

    /**
     * 新增应用信息
     * @param application
     */
    @Override
    public void registerClientDetails(Application application) {
        oauthRepository.saveOauthClientDetails(application);
    }


    @Override
    @Cacheable(value = CLIENT_DETAILS_CACHE, key = "#appKey")
    public ClientDetails loadClientByClientId(String appKey) throws ClientRegistrationException {
        Application application = loadOauthClientDetailsDto(appKey);
        if(application == null) return null;
        return new OpenAppDetails(application);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        throw new InvalidClientException("未实现[addClientDetails]方法");
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#clientDetails.getClientId()")
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        throw new InvalidClientException("未实现[addClientDetails]方法");
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#appKey")
    public void updateClientSecret(String appKey, String secret) throws NoSuchClientException {
        oauthRepository.updateAppSecret(appKey,secret);
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#appKey")
    public void removeClientDetails(String appKey) throws NoSuchClientException {
        archiveOauthClientDetails(appKey);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<Application> appList = loadAllOauthClientDetailsDtos();
        if(appList == null) return null;
        List<ClientDetails> detailsList = new ArrayList<ClientDetails>(appList.size());
        for(Application app:appList){
            OpenAppDetails openAppDetails = new OpenAppDetails(app);
            detailsList.add(openAppDetails);
        }
        return detailsList;
    }
}