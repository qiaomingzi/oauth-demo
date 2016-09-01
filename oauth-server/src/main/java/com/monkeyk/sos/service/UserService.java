package com.monkeyk.sos.service;

import com.monkeyk.sos.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    User loadCurrentUserJsonDto();

    List<User> loadUserOverviewDto(User overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(User formDto);
}