package cn.itcast.elec.web.form;

import java.util.Date;

public class ElecLogForm  {
	private String  logId;
	private String ipAddress;
	private String opeName;
	private Date opeTime;
	private String details;
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOpeName() {
		return opeName;
	}
	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}
	public Date getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
