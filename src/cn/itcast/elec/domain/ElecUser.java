package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

public class ElecUser implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private String jctID;// À˘ Ùµ•Œªcode
	private String userName;
	private String loganName;
	private String logonName;
	private String loganPwd;
	private String sexID;
	private String address;
	private String contactTel;
	private String email;
	private String mobile;
	private Date birthday;
	private String isDuty;
	private String remark;
	private Date onDutyDate;
	private Date offDutyDate;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoganName() {
		return loganName;
	}

	public void setLoganName(String loganName) {
		this.loganName = loganName;
	}

	public String getLoganPwd() {
		return loganPwd;
	}

	public void setLoganPwd(String loganPwd) {
		this.loganPwd = loganPwd;
	}

	public String getSexID() {
		return sexID;
	}

	public void setSexID(String sexID) {
		this.sexID = sexID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOnDutyDate() {
		return onDutyDate;
	}

	public void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public Date getOffDutyDate() {
		return offDutyDate;
	}

	public void setOffDutyDate(Date offDutyDate) {
		this.offDutyDate = offDutyDate;
	}

	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	@Override
	public String toString() {
		return "ElecUser [userID=" + userID + ", jctID=" + jctID + ", userName=" + userName + ", loganName=" + loganName
				+ ", loganPwd=" + loganPwd + ", sexID=" + sexID + ", address=" + address + ", contactTel=" + contactTel
				+ ", email=" + email + ", mobile=" + mobile + ", birthday=" + birthday + ", isDuty=" + isDuty
				+ ", remark=" + remark + ", onDutyDate=" + onDutyDate + ", offDutyDate=" + offDutyDate + "]";
	}

}
