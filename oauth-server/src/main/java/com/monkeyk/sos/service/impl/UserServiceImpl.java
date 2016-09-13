package com.monkeyk.sos.service.impl;

import com.monkeyk.sos.domain.OpenUserDetails;
import com.monkeyk.sos.domain.User;
import com.monkeyk.sos.dao.service.UserRepository;
import com.monkeyk.sos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 处理用户, 账号, 安全相关业务
 *
 * @author Shengzhao Li
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null || user.getStatus() == 0) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }

        return new OpenUserDetails(user);
    }
    @Override
    public User loadCurrentUserJsonDto() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getPrincipal();

        if (authentication instanceof OAuth2Authentication &&
                (principal instanceof String || principal instanceof org.springframework.security.core.userdetails.User)) {
            //loadOauthUserJsonDto((OAuth2Authentication) authentication);
            //authentication.getDetails();
            return new User();
        } else {
            final OpenUserDetails userDetails = (OpenUserDetails) principal;
            User user = userRepository.findByUsername(userDetails.getUsername());

            return user;
        }
    }

    @Override
    public List<User> loadUserOverviewDto(User overviewDto) {
        List<User> users = userRepository.findUsersByUsername(overviewDto.getUsername());
        return users;
    }

    @Override
    public boolean isExistedUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Override
    public String saveUser(User user) {
        userRepository.saveUser(user);
        return user.getId().toString();
    }

    private User loadOauthUserJsonDto(OAuth2Authentication oAuth2Authentication) {
        User user = new User();
        user.setAccount(oAuth2Authentication.getName());

        final Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
        Integer[] roles = new Integer[authorities.size()];
        int i = 0 ;
        for (GrantedAuthority authority : authorities) {
            roles[i] = Integer.parseInt(authority.getAuthority());
        }
        user.setRoleIds(roles);
        return user;
    }
}