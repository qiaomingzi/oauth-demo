package com.monkeyk.sos.domain;

// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orgId;
	private String account;
	private String password;
	private String nickname;
	private String tel;
	private String email;
	private String descript;
	private Integer status = 1;
	private Date createTime = new Date();
	private Integer createUser;
	private List<String> roleIds = new ArrayList<String>();
	private String picPath;
		
	private Long certId;		//证件ID
	private Integer userType;	//用户类型:0:政府人员，1：个人，2：公司企业'
    // Constructors


    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(Integer orgId, String account, String password, String nickname, String tel, String email, String descript, Integer status, Date createTime, Integer createUser) {
        this.orgId = orgId;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.email = email;
        this.descript = descript;
        this.status = status;
        this.createTime = createTime;
        this.createUser = createUser;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

    public String getDescript() {
        return this.descript;
    }
    
    public void setDescript(String descript) {
        this.descript = descript;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

	public List<String> getRoleIds() {
		return roleIds;
	}


	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getUsername() {
		return this.account;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}


	public Long getCertId() {
		return certId;
	}


	public void setCertId(Long certId) {
		this.certId = certId;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}