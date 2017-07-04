package cn.itcast.elec.domain;

import java.util.Date;

public class ElecQuestion {
private String questionId;
private String userId;
private String remark;
private Date doDate;
public String getQuestionId() {
	return questionId;
}
public void setQuestionId(String questionId) {
	this.questionId = questionId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public Date getDoDate() {
	return doDate;
}
public void setDoDate(Date doDate) {
	this.doDate = doDate;
}

}
