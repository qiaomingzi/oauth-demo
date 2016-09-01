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

import com.monkeyk.sos.dao.service.AppRepository;
import com.monkeyk.sos.domain.Application;
import com.monkeyk.sos.utils.GuidGenerator;
import com.monkeyk.sos.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/*
  * @author Shengzhao Li
  */
public class OauthRepositoryJdbcTest extends AbstractRepositoryTest {


    @Autowired
    private AppRepository oauthRepositoryMyBatis;


    @Test
    public void findOauthClientDetails() {
        Application oauthClientDetails = oauthRepositoryMyBatis.findOauthClientDetails("unity-client");
        assertNull(oauthClientDetails);

    }


    @Test
    public void saveOauthClientDetails() {

        final String clientId = GuidGenerator.generate();

        Application clientDetails = new Application();
        clientDetails.setAppKey("ddooelddd");
        clientDetails.setAppSecret("xxxxx");
        oauthRepositoryMyBatis.saveOauthClientDetails(clientDetails);

        final Application oauthClientDetails = oauthRepositoryMyBatis.findOauthClientDetails(clientId);
        assertNotNull(oauthClientDetails);
        assertNotNull(oauthClientDetails.getAppKey());
        assertNull(oauthClientDetails.getAppSecret());

    }

    @Test
    public void findAllOauthClientDetails() {
        final List<Application> allOauthClientDetails = oauthRepositoryMyBatis.findAllOauthClientDetails();
        assertNotNull(allOauthClientDetails);
        assertTrue(allOauthClientDetails.isEmpty());
    }

    @Test
    public void updateOauthClientDetailsArchive() {
        oauthRepositoryMyBatis.updateOauthClientDetailsArchive("ddooelddd", "003");
    }


}