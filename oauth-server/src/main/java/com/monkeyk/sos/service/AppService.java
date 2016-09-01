package com.monkeyk.sos.service;

import com.monkeyk.sos.domain.Application;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import java.util.List;

public interface AppService extends ClientDetailsService, ClientRegistrationService {

    Application loadOauthClientDetails(String appKey);

    List<Application> loadAllOauthClientDetailsDtos();

    void archiveOauthClientDetails(String appKey);

    Application loadOauthClientDetailsDto(String appKey);

    void registerClientDetails(Application application);
}