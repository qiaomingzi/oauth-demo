package com.monkeyk.sos.web.validator;
import com.monkeyk.sos.domain.User;
import com.monkeyk.sos.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 2016/3/25
 *
 * @author Shengzhao Li
 */
@Component
public class UserFormDtoValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User formDto = (User) target;

        validateUsername(errors, formDto);
        validatePassword(errors, formDto);
        validatePrivileges(errors, formDto);
    }

    private void validatePrivileges(Errors errors, User formDto) {
        final Integer[] roleIds = formDto.getRoleIds();
        if (roleIds == null || roleIds.length == 0) {
            errors.rejectValue("privileges", null, "Privileges is required");
        }
    }

    private void validatePassword(Errors errors, User formDto) {
        final String password = formDto.getPassword();
        if (StringUtils.isEmpty(password)) {
            errors.rejectValue("password", null, "Password is required");
        }
    }

    private void validateUsername(Errors errors, User formDto) {
        final String username = formDto.getAccount();
        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", null, "Username is required");
            return;
        }

        boolean existed = userService.isExistedUsername(username);
        if (existed) {
            errors.rejectValue("username", null, "Username already existed");
        }

    }
}
