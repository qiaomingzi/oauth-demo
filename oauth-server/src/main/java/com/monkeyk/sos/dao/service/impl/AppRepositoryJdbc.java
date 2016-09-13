package com.monkeyk.sos.dao.service.impl;

import com.monkeyk.sos.domain.Application;
import com.monkeyk.sos.dao.service.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.monkeyk.sos.utils.CacheConstants.CLIENT_DETAILS_CACHE;

/**
 * 2015/11/16
 *
 * @author Shengzhao Li
 */
@Repository("oauthRepositoryJdbc")
public class AppRepositoryJdbc implements AppRepository {


    private RowMapper applicationRowMapper = new BeanPropertyRowMapper(Application.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Application findOauthClientDetails(String appKey) {
        final String sql = " select * from t_open_application where  APP_KEY = ? and status='001'";
        final List<Application> list = this.jdbcTemplate.query(sql, new Object[]{appKey}, applicationRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Application> findAllOauthClientDetails() {
        final String sql = " select * from t_open_application where  status='001' order by create_time desc ";
        return this.jdbcTemplate.query(sql, applicationRowMapper);
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#appKey")
    public void updateOauthClientDetailsArchive(String appKey, String status) {
        final String sql = " update t_open_application set status = ? where APP_KEY = ? ";
        this.jdbcTemplate.update(sql, status, appKey);
    }
    public void updateAppSecret(String appKey, String secret) {
        final String sql = " update t_open_application set app_secret = ? where APP_KEY = ? ";
        this.jdbcTemplate.update(sql, secret, appKey);
    }
    @Override
    public void saveOauthClientDetails(final Application application) {
        final String sql = " insert into t_open_application(APP_TYPE,APP_NAME,APP_KEY,APP_SECRET," + //4
                "REDIRECT_URI,HOME_PAGE, DOMAIN,LITTLE_LOGO,BIG_LOGO,REMARK,UPDATE_USER_ID,CREATE_USER_ID," +//8
                "CREATE_TIME,STATUS_TIME, STATUS,RESOURCE_IDS,SCOPE,AUTHORIZED_GRANT_TYPES," + //6
                "AUTHORITIES,ACCESS_TOKEN_VALIDITY,REFRESH_TOKEN_VALIDITY,ADDITIONAL_INFORMATION,TRUSTED,AUTOAPPROVE" +//6
                ") values (?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,)";


        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, application.getAppType());
                ps.setString(2, application.getAppName());
                ps.setString(3, application.getAppKey());
                ps.setString(4, application.getAppSecret());
                ps.setString(5, application.getRedirectUri());
                ps.setString(6, application.getHomePage());
                ps.setString(7, application.getDomain());
                ps.setObject(8, application.getLittleLogo());
                ps.setObject(9, application.getBigLogo());
                ps.setString(10, application.getRemark());
                ps.setLong(11, application.getUpdateUserId());
                ps.setLong(12, application.getCreateUserId());

                ps.setObject(13, application.getCreateTime());
                ps.setObject(14, application.getStatusTime());
                ps.setString(15, application.getStatus());
                ps.setString(16, application.getResourceIds());
                ps.setString(17, application.getScope());
                ps.setObject(18, application.getAuthorizedGrantTypes());
                ps.setObject(19, application.getAuthorities());
                ps.setInt(20, application.getAccessTokenValidity());
                ps.setInt(21, application.getRefreshTokenValidity());
                ps.setString(22, application.getAdditionalInformation());
                ps.setInt(23, application.getTrusted());
                ps.setString(24, application.getAutoApprove());
            }
        });
    }
}
