package cn.itcast.elec.web.form;

import java.io.File;

import cn.itcast.elec.util.StringHelper;

public class ElecUserForm {
	private String userId;
	private String jctId;
	private String userName;
	private String logonName;
	private String logonPwd;
	private String  sexId;
	private String address;
	private String contactTel;
	private String  email;
	private String  mobile;
	private String birthday;
	private String isDuty;
	private String remark;
	private String onDutyDate;
	private String offDutyDate;
	private String viewFlag;
	private String flag;
	private String roleName;
	private String roleFlag;
	private String md5Flag;
	private String sourceFileName; //待上传文件的文件名
	private String sourceContentType; //待上传文件的文件类型
	
    public String getSourceFileName() { 
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	public String getSourceContentType() {
		return sourceContentType;
	}
	public void setSourceContentType(String sourceContentType) {
		this.sourceContentType = sourceContentType;
	}
	public String getMd5Flag() {
		return md5Flag;
	}
	private  File file;
    private String jctName;
    private String jctCount;
	public String getJctName() {
		return jctName;
	}
	public void setJctName(String jctName) {
		this.jctName = jctName;
	}
	public String getJctCount() {
		return jctCount;
	}
	public void setJctCount(String jctCount) {
		this.jctCount = jctCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userID) {
		this.userId = userID;
	}
	public String getJctId() {
		return jctId;
	}
	public void setJctId(String jctID) {
		this.jctId = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String loganName) {
		this.logonName = loganName;
	}
	public String getLogonPwd() {
		return logonPwd;
	}
	public void setLogonPwd(String loganPwd) {
		this.logonPwd = loganPwd;
	}
	public String getSexId() {
		return sexId;
	}
	public void setSexId(String sexID) {
		this.sexId = sexID;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
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
	public String getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public String getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(String offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}
	
	public void setMd5Flag(String md5Flag) {
		this.md5Flag = md5Flag;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "ElecUserForm [userId=" + userId + ", jctId=" + jctId + ", userName=" + userName + ", logonName="
				+ logonName + ", logonPwd=" + logonPwd + ", sexId=" + sexId + ", address=" + address + ", contactTel="
				+ contactTel + ", email=" + email + ", mobile=" + mobile + ", birthday=" + birthday + ", isDuty="
				+ isDuty + ", remark=" + remark + ", onDutyDate=" + onDutyDate + ", offDutyDate=" + offDutyDate
				+ ", viewFlag=" + viewFlag + ", flag=" + flag + ", roleName=" + roleName + ", roleFlag=" + roleFlag
				+ ", md5Flag=" + md5Flag + ", file=" + file + ", jctName=" + jctName + ", jctCount=" + jctCount + "]";
	}
	

}
