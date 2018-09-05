package com.bluemobi.to.admin;

import com.appcore.model.AbstractObject;

public class AdminUserTO extends AbstractObject{

	private static final long serialVersionUID = -1462977694660984109L;

	private int userId;
	
	private String oldpass;
	
	private String newpass;
	
	private String conformpass;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getConformpass() {
		return conformpass;
	}

	public void setConformpass(String conformpass) {
		this.conformpass = conformpass;
	}
}
