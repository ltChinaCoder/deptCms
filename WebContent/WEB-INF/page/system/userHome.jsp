<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.itcast.elec.domain.ElecUser"%>
 <%@ taglib uri="/struts-tags" prefix="s" %>
 <% ElecUser user=(ElecUser)request.getSession().getAttribute("user");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
</head>
<body>
<form id="Form1" name="Form1" action="system/elecEventAction_userHome.do?userId=${user.loganName}" method="post" style="margin:0px;"> 
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
				<TR height=10><td></td></TR>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>事件总览</strong></font>
					</td>					
				</tr>				
			
				<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					最小学时：</td>
					<td class="ta_01" >
					<input name="minScore" type="text" id="minScore"  size="10">
					</td> 
				</tr>
				<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					最大学时：</td>
					<td class="ta_01" >
					<input name="maxScore" type="text" id="maxScore"  size="10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font face="宋体" color="red">		        
					 <input style="font-size:12px; color:black; height=20;width=80" id="BT_find" type="submit" value="查询" name="BT_find" 
						  >
					</font>
					</td> 
				</tr>
				<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					开始时间：</td>
					<td class="ta_01" >
					<input name="startTime" type="text" maxlength="50" size="10" onclick="JavaScript:calendar(document.Form1.startTime)">
					</td> 
				</tr>
				<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					结束时间：</td>
					<td class="ta_01" >
					<input name="endTime" type="text" id="endTime"  size="10" onclick="JavaScript:calendar(document.Form1.endTime)">					
					</td> 
				</tr>
					<tr>				
				</tr>		
		    </table>		    
		</form>
<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
										<TR>
											<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">事件列表</TD>
										</TR>
			             </TABLE>
                   </td>
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=5>			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">用户id</td>
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">学时</td>
								<td align="center" width="30%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">内容</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">志愿时间</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">记录时间</td>
								
								
							</tr>
							
							
								 <s:if test="#request.list!=null">
								 <s:iterator value="%{#request.list}" var="inf">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#inf.userId}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#inf.score}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<s:property value="%{#inf.remark}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#inf.doDate}"/>
										</td>									
										<td style="HEIGHT:22px" align="center" width="10%">
											<s:property value="%{#inf.recordDate}"/>
										</td>			 
									</tr>
									</s:iterator>
									</s:if>								
						</table>											
					</td>
				</tr> 
					</TBODY>
		</table>
</body>
</html>