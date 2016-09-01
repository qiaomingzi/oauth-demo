package com.monkeyk.sos.dao.service;

import com.monkeyk.sos.domain.User;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface UserRepository extends Repository {

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String account);

    List<User> findUsersByUsername(String account);
}