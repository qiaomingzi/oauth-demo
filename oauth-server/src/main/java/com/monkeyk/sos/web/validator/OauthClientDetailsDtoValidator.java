package com.monkeyk.sos.web.validator;

import com.monkeyk.sos.domain.Application;
import com.monkeyk.sos.service.AppService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class OauthClientDetailsDtoValidator implements Validator {


    @Autowired
    private AppService oauthService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Application clientDetailsDto = (Application) target;

        validateClientId(clientDetailsDto, errors);
        validateClientSecret(clientDetailsDto, errors);

        validateGrantTypes(clientDetailsDto, errors);
    }

    private void validateGrantTypes(Application clientDetailsDto, Errors errors) {
        final String grantTypes = clientDetailsDto.getAuthorizedGrantTypes();
        if (StringUtils.isEmpty(grantTypes)) {
            errors.rejectValue("authorizedGrantTypes", null, "grant_type(s) is required");
            return;
        }

        if ("refresh_token".equalsIgnoreCase(grantTypes)) {
            errors.rejectValue("authorizedGrantTypes", null, "grant_type(s) 不能只是[refresh_token]");
        }
    }

    private void validateClientSecret(Application clientDetailsDto, Errors errors) {
        final String clientSecret = clientDetailsDto.getAppSecret();
        if (StringUtils.isEmpty(clientSecret)) {
            errors.rejectValue("clientSecret", null, "client_secret is required");
            return;
        }

        if (clientSecret.length() < 8) {
            errors.rejectValue("clientSecret", null, "client_secret 长度至少8位");
        }
    }

    private void validateClientId(Application clientDetailsDto, Errors errors) {
        final String appKey = clientDetailsDto.getAppKey();
        if (StringUtils.isEmpty(appKey)) {
            errors.rejectValue("appKey", null, "appKey is required");
            return;
        }


        Application clientDetailsDto1 = oauthService.loadOauthClientDetailsDto(appKey);
        if (clientDetailsDto1 != null) {
            errors.rejectValue("appKey", null, "appKey [" + appKey + "] 已存在");
        }

    }
}