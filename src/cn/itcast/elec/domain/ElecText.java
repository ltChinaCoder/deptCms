package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;
//entity加hbm文件相当于是po对象 持久对象 要用ser 也就是所谓的持久层 永久存在的 
public class ElecText implements Serializable{
private String textID;
private String textName;
private Date textDate;
private String textRemark;
public String getTextID() {
	return textID;
}
public void setTextID(String textID) {
	this.textID = textID;
}
public String getTextName() {
	return textName;
}
public void setTextName(String textName) {
	this.textName = textName;
}
public Date getTextDate() {
	return textDate;
}
public void setTextDate(Date textDate) {
	this.textDate = textDate;
}
public String getTextRemark() {
	return textRemark;
}
public void setTextRemark(String textRemark) {
	this.textRemark = textRemark;
}

}
