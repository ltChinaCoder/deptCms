package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;
//vo值对象  对应表单的属性值
//vo对象与po对象比较  相同点都是javabean 不同点：po对象对应数据库的字段 
//vo对象对应表单的数据 随意修改 删除 添加
public class ElecTextForm implements Serializable {

	private String textID;
	private String textName;
	private String textDate;
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
	public String getTextDate() {
		return textDate;
	}
	public void setTextDate(String textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}

	}

