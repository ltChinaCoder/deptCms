package cn.itcast.elec.domain;

import java.util.Date;

public class ElecEvent {
private String eventId;
private String userId;
private int score;
private Date doDate;
private Date recordDate;
private String remark;
public String getEventId() {
	return eventId;
}
public void setEventId(String eventId) {
	this.eventId = eventId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public Date getDoDate() {
	return doDate;
}
public void setDoDate(Date doDate) {
	this.doDate = doDate;
}
public Date getRecordDate() {
	return recordDate;
}
public void setRecordDate(Date recordDate) {
	this.recordDate = recordDate;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
@Override
public String toString() {
	return "ElecEvent [eventId=" + eventId + ", userId=" + userId + ", score=" + score + ", doDate=" + doDate
			+ ", recordDate=" + recordDate + ", remark=" + remark + "]";
}

}
