<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%String name="";
String password="";
String checked="";
String n="";
if(request.getCookies()!=null)
{
	Cookie []c=request.getCookies();
	for(int i=0;i<c.length;i++)
	{
		if(c[i]!=null &&"name".equals(c[i].getName()))
		{
			name=c[i].getValue();
			n=URLDecoder.decode(name, "utf-8");
			checked="checked";
		}
		if(c[i]!=null &&"password".equals(c[i].getName()))
			password=c[i].getValue();
		}}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/buttonstyle.css" type="text/css" rel="stylesheet">
<LINK href="${pageContext.request.contextPath}/css/MainPage.css" type="text/css" rel="stylesheet">
<script type='text/javascript' src='${pageContext.request.contextPath}/script/pub.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/script/validate.js'></script>
<SCRIPT type="text/javascript">
function ini(){
   document.all.name.focus();
}
function changeNumber()
{
	var image=document.getElementById("image");
	image.src="${pageContext.request.contextPath}/image.jsp?timestamp="+new Date().getTime();
	}
function check(){
    var theForm = document.forms[0];
    if(!checkNull(theForm.name))
		{
			alert("请输入用户名");
			theForm.name.focus(); 
			return false;
		} 
	if(Trim(theForm.name.value)==""){
			alert("请输入用户名");
			theForm.name.focus();
			return false;
	    }
}
</SCRIPT>
<STYLE type=text/css>
BODY { margin: 0px; }
FORM {
	MARGIN: 0px; BACKGROUND-COLOR: #ffffff
} 
</STYLE>
<title></title>
</head>
<body onload="ini()">
<form action="${pageContext.request.contextPath}/system/elecMenuAction_home.do" method="post" onsubmit="return check();">
<table border="0" width="100%" id="table1" height="532" cellspacing="0" cellpadding="0" >
	<tr>
		<td>　</td>
	</tr>
	<tr>
		<td height="467">
		<table border="0" width="1024" id="table2" height="415" cellspacing="0" cellpadding="0" >
		<br><br><br><br><br>
			<tr>
				<td width=12%></td>
				<td align=center background="${pageContext.request.contextPath}/images/index.jpg">
				<table border="0" width="98%" id="table3" height="412" cellspacing="0" cellpadding="0">
					<tr height=122>
						<td colspan=2></td>
					</tr>
					
					<tr>
						<td height="313" width="73%"></td>
						<td height="99" width="27%">
							<table border="0" width="70%" id="table4">
							<tr  bgcolor="red" height="20">
						<s:fielderror name="error"> </s:fielderror>
					       </tr>
								<tr>
									<td width="100"><img border="0" src="${pageContext.request.contextPath}/images/yonghu.gif" width="84" height="20"></td>
									<td><input type="text" name="name" style="width: 100 px" value="<%=n %>"  maxlength="25"></td>
	
								</tr>
								<tr>
									<td width="100"><img border="0" src="${pageContext.request.contextPath}/images/mima.gif" width="84" height="20"></td>
									<td><input type="password" name="password" style="width: 100 px" value="<%=password%>"  maxlength="25"></td>
									
								</tr>
								<tr>
									<td width="100"><img border="0" src="${pageContext.request.contextPath}/images/check.jpg" width="84" height="20"></td>
									<td>
									<table>
									<tr>
									<td><input type="text" name="checkNumber" id="checkNumber" style="width: 55 px"   maxlength="4"></td>
									<td><img  src="${pageContext.request.contextPath}/image.jsp" id="image"  height="20"width="40" onclick="changeNumber()"></td>
									</tr>
									</table>	
									</td>									
								</tr>
								<tr>
									<td width="100"><img border="0" src="${pageContext.request.contextPath}/images/remeber.jpg" width="84" height="20"></td>
									<td><input type="checkbox" name="rememberMe" id="rememberMe" style="width: 20 px" value="yes" <%=checked%>></td>
									
								</tr>
								<tr>
									<td width="100"></td>
									<td width="100"><input type="submit" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" value="登   录" name="huifubtn"></td>

								</tr>
							</table>
						</td>
					</tr>
					
					</table>
				</td>
				<td width=13%></td>
			</tr>
			<tr>
		      <td align="center" colspan=3>&nbsp;</td>
	        </tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
