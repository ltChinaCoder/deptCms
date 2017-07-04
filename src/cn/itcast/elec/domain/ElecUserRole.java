package cn.itcast.elec.domain;

public class ElecUserRole {
private String seqID;
private String userID;
private String roleID;
private String remark;

public String getSeqID() {
	return seqID;
}
public void setSeqID(String seqID) {
	this.seqID = seqID;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getRoleID() {
	return roleID;
}
public void setRoleID(String roleID) {
	this.roleID = roleID;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
@Override
public String toString() {
	return "ElecUserRole [seqID=" + seqID + ", userID=" + userID + ", roleID=" + roleID + ", remark=" + remark + "]";
}

}
