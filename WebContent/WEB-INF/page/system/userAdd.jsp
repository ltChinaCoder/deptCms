
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
  <head>
   <title>添加用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
<Script language="javascript">
function show()
{
	alert("如果您不输入密码 默认密码为000000");}
	function check_null(){
	    
	    var theForm=document.Form1;
	    
	    if(Trim(theForm.logonName.value)=="" || theForm.logonName.value.length!=12)
		{
			alert("学号不能为空或者必须为12位");
			theForm.logonName.focus();
			return false;
		}
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	    if(theForm.jctId.value=="")
		{
			alert("请选择所属学院");
			theForm.jctId.focus();
			return false;
		}
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.mobile)){
         if(!checkPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		else
    	{
    	alert("请输入手机号码");
		theForm.mobile.focus();
		return false;
    	}
		
		
	    if(checkNull(theForm.contactTel.value)){
         if(!checkMobilPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
	    
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false; 
        }
		 
	   document.Form1.action="system/elecUserAction_save.do";
		 document.Form1.submit();	 		 
		 refreshOpener();
	}
	 function createXmlHttpRequest()
	 {
		 var xmlHttp;
		 try {
			 xmlHttp=new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp=new ActiveXObject("Msxm12.XMLHTTP")
				
			} catch (e) {
				try {
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP")
				} catch (e) {
					// TODO: handle exception
				}
			}
		}
		return xmlHttp;
	 }
  	

//使用ajax
function checkLogonName()
{
	var logonName=document.getElementById("logonName").value;

	//创建引擎
	xmlHttp=createXmlHttpRequest();
	//事件处理函数 其实就是一个监听器 监听服务器和客户端的链接情况
	xmlHttp.onreadystatechange=function ()
	{
		if(xmlHttp.readyState==4)
			{
			if(xmlHttp.status==200)
				{
				
				var data=xmlHttp.responseText;
	              if(data==1)
	            	  {
	            	  alert("该登录名已经被注册，请重新输入！");
	            	  document.getElementById("logonName").value="";
	            	  document.getElementById("logonName").focus();
	            	  }
				}
			}
	}
	
	//与后台服务器进行链接
	xmlHttp.open("post", "../../CheckLogonName",true);//这个步骤一定要写到最前面
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//发送请求	
	xmlHttp.send("logonName="+logonName);	
}
	
   </script>
  </head>
  
 <body>
 
  <form name="Form1" method="post">
 <br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

    <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>添加用户</strong></font>
		</td>
    </tr>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">学&nbsp;&nbsp;&nbsp;&nbsp;号：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff"><input name="logonName" type="text" maxlength="25" id="logonName" size="20" onblur="checkLogonName()">
          </td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff"><input name="userName" type="text" maxlength="25" id="userName" size="20" /> 
          </td>
    </tr>
<tr>


<td align="center" bgColor="#f5fafe" class="ta_01">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
<td class="ta_01" bgColor="#ffffff">
<s:select list="#request.sexList" name="sexId" cssStyle="width:155px" listValue="ddlName" listKey="ddlCode"></s:select>
</td>

<td align="center" bgColor="#f5fafe" class="ta_01">所属学院：<font color="#FF0000">*</font></td>
<td class="ta_01" bgColor="#ffffff">
<s:select list="#request.jctList" listKey="ddlCode"  listValue="ddlName" name="jctId" cssStyle="width:155px"> 
</s:select>
</td>
</tr>
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
<td class="ta_01" bgColor="#ffffff"><input name="logonPwd" type="password" maxlength="25"  size=22 onfocus="show()">
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
<td class="ta_01" bgColor="#ffffff"><input name="passwordconfirm" type="password" maxlength="25"  size=22>
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="birthday" type="text" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.birthday)" >
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
<td class="ta_01" bgColor="#ffffff"><input name="address" type="text" maxlength="50" size="20">
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
<td class="ta_01" bgColor="#ffffff"><input name="contactTel" type="text" maxlength="25"  size="20"></td>
<td align="center" bgColor="#f5fafe" class="ta_01">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
<td class="ta_01" bgColor="#ffffff">
<input name="mobile" type="text" maxlength="25"  size="20">
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
<td class="ta_01" bgColor="#ffffff"><input name="email" type="text" maxlength="50"  size="20">
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
<td class="ta_01" bgColor="#ffffff">
<s:select list="#request.onDutyList" name="isDuty" cssStyle="width:155px" listKey="ddlCode"  listValue="ddlName"></s:select>
</td>
</tr>

<tr>

<td align="center" bgColor="#f5fafe" class="ta_01">入学日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="onDutyDate" type="text" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.onDutyDate)">
</td>
<td align="center" bgColor="#ffffff" class="ta_01"></td>
<td class="ta_01" bgColor="#ffffff"></td>
</tr>

<TR>
<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea name="remark"  style="WIDTH:95%"  rows="4" cols="52"></textarea></TD>
</TR>
<TR>
<td  align="center"  colSpan="4"  class="sep1"></td>
</TR>
<tr>
	<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
	<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
	 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
	<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
		
	</td>
</tr>
</table>　
</form>

</body>
</html>
