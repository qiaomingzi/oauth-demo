/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.monkeyk.sos.dao;

import com.monkeyk.sos.domain.User;
import com.monkeyk.sos.dao.service.UserRepository;
import com.monkeyk.sos.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/*
  * @author Shengzhao Li
  */
public class UserRepositoryJdbcTest extends AbstractRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void findByGuid() {
        User user = userRepository.findByUsername("oood");
        assertNull(user);

        user = new User();
        user.setAccount("oood");
        user.setPassword("123");
        user.setTel("123");
        user.setEmail("ewo@honyee.cc");
        userRepository.saveUser(user);

        assertNotNull(user.getId());
    }




    @Test
    public void updateUser() {
        User user = userRepository.findByUsername("oood");
        assertNotNull(user);

        user.setAccount("oood");
        user.setPassword("123456");
        user.setTel("123456");
        user.setEmail("ewo@honyee.cc");
        userRepository.saveUser(user);
    }

    /*
    * Run the test must initial db firstly
    * */
    @Test(enabled = false)
    public void testPrivilege() {
        User user = userRepository.findByUsername("oood");
        assertNotNull(user);
        assertEquals(user.getRoleIds().size(), 1);
    }
}