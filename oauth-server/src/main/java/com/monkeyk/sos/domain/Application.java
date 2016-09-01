package com.monkeyk.sos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的应用
 */
public class Application implements Serializable{
	private static final long serialVersionUID = 0L;
	private Long appId;//应用ID
    private Integer appType;//应用类型
	private String appName;//应用名称
	private String appKey;//应用key   client_id
	private String appSecret;//应用密钥 client_secret
	private String redirectUri;//回调地址 web_server_redirect_uri
	private String homePage;//应用主页
	private String domain;//应用域名
	private String littleLogo;//应用小logo
	private String bigLogo;//应用大logo
	private String remark;//说明        additional_information
	private Long updateUserId;//创建者id
	private Long createUserId;//创建者id
	private Date createTime;//创建时间   create_time
	private Date statusTime;//更新时间
    private String status;//001|正常 002|待审核 003|删除  archived   是否已存档(即实现逻辑删除),

	private String resourceIds;//resource_ids  客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,与security配置文件resource一致
	private String scope; //scope      指定客户端申请的权限范围,可选值包括read,write,trust;  intercept-url的access属性有关系
	// 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials,
	//"authorization_code,refresh_token"(针对通过浏览器访问的客户端); "password,refresh_token"(针对移动设备的客户端).
	private String authorizedGrantTypes="authorization_code,refresh_token"; //authorized_grant_types
	private String authorities;//authorities 拥有的Spring Security的权限值
	private Integer accessTokenValidity;//access_token_validity     access_token的有效时间值(单位:秒),
	private Integer refreshTokenValidity;//refresh_token_validity  refresh_token的有效时间值(单位:秒),
	private String additionalInformation;
	private Integer trusted = 0;//trusted     是否为受信任的,默认为'0'(即不受信任的,1为受信任的).
	private String autoApprove;//autoapprove    是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLittleLogo() {
		return littleLogo;
	}

	public void setLittleLogo(String littleLogo) {
		this.littleLogo = littleLogo;
	}

	public String getBigLogo() {
		return bigLogo;
	}

	public void setBigLogo(String bigLogo) {
		this.bigLogo = bigLogo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Integer getTrusted() {
		return trusted;
	}

	public void setTrusted(Integer trusted) {
		this.trusted = trusted;
	}

	public String getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}


	public String getScopeWithBlank() {
		if (scope != null && scope.contains(",")) {
			return scope.replaceAll(",", " ");
		}
		return scope;
	}
	public boolean isContainsAuthorizationCode() {
		return this.authorizedGrantTypes.contains("authorization_code");
	}

	public boolean isContainsPassword() {
		return this.authorizedGrantTypes.contains("password");
	}

	public boolean isContainsImplicit() {
		return this.authorizedGrantTypes.contains("implicit");
	}

	public boolean isContainsClientCredentials() {
		return this.authorizedGrantTypes.contains("client_credentials");
	}

	public boolean isContainsRefreshToken() {
		return this.authorizedGrantTypes.contains("refresh_token");
	}

}
