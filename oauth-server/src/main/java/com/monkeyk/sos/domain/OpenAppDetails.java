package com.monkeyk.sos.domain;

import com.monkeyk.sos.utils.JsonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Spring Security中的 UserDetails 实现
 *
 * @author Shengzhao Li
 */
public class OpenAppDetails implements ClientDetails {
    protected Application application;
    private Set<String> resourceIds;
    private Set<String> authorizedGrantTypes;
    private Set<String> scopes;
    private List<GrantedAuthority> authorities;
    private Set<String> registeredRedirectUris;
    private Map<String, Object> additionalInformation;
    private Set<String> autoApproveScopes;

    public OpenAppDetails(Application application) {
        this.application = application;

        if (StringUtils.hasText(application.getResourceIds())) {
            Set<String> resources = StringUtils.commaDelimitedListToSet(application.getResourceIds());
            if (!resources.isEmpty()) {
                this.resourceIds = resources;
            }
        }

        if (StringUtils.hasText(application.getScope())) {
            Set<String> scopeList = StringUtils.commaDelimitedListToSet(application.getScope());
            if (!scopeList.isEmpty()) {
                this.scopes = scopeList;
            }
        }

        if (StringUtils.hasText(application.getAuthorizedGrantTypes())) {
            this.authorizedGrantTypes = StringUtils
                    .commaDelimitedListToSet(application.getAuthorizedGrantTypes());
        } else {
            this.authorizedGrantTypes = new HashSet<String>(Arrays.asList(
                    "authorization_code", "refresh_token"));
        }

        if (StringUtils.hasText(application.getAuthorities())) {
            this.authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(application.getAuthorities());
        }

        if (StringUtils.hasText(application.getRedirectUri())) {
            this.registeredRedirectUris = StringUtils
                    .commaDelimitedListToSet(application.getRedirectUri());
        }

        if (StringUtils.hasText(application.getAdditionalInformation())) {
            try {
                JsonUtils.IJsonMapper mapper = JsonUtils.createJsonMapper();
                additionalInformation = mapper.read(application.getAdditionalInformation(), Map.class);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.hasText(application.getAutoApprove())) {
            autoApproveScopes = StringUtils.commaDelimitedListToSet(application.getAutoApprove());
        }
    }

    @Override
    public String getClientId() {
        return application.getAppKey();
    }

    @Override
    public Set<String> getResourceIds() {
         return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return application.getAppSecret();
    }

    @Override
    public boolean isScoped() {
        return this.scopes != null && !this.scopes.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return this.scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return application.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return application.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}