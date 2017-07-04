

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>



<html>
  <head>
   <title>
   <s:if test="#request.viewFlag==null">
    编辑用户
   </s:if>
  <s:else>
    查看明细
  </s:else>
   </title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <Script language="javascript">

	function check_null(){
	
		var theForm=document.Form1;
	    
	  
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	    if(theForm.jctId.value=="")
		{
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkMobilPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
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
		 var password=document.getElementById("logonPwd").value;		
		 var defaultPwd=document.getElementById("logonPwd").defaultValue;
		 
		 if(password==defaultPwd)
			 {
			 document.getElementById("md5Flag").value="1";			 		 
			 }
		 var roleFlag=document.getElementById("roleFlag");	
	     document.Form1.action="system/elecUserAction_save.do";	      
		 document.Form1.submit();		
		   refreshOpener();
		   
		 //加载aja引擎
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
		 }
	  	return xmlHttp;
	}
	function check_nullTwo()
	{
		var theForm=document.Form1;
	    
		  
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	   
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		
		
	   
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	  
		 var password=document.getElementById("logonPwd").value;		
		 var defaultPwd=document.getElementById("logonPwd").defaultValue;
		 
		 if(password==defaultPwd)
			 {
			 document.getElementById("md5Flag").value="1";			 		 
			 }
		 var roleFlag=document.getElementById("roleFlag");	
	     document.Form1.action="system/elecUserAction_save.do";	      
		 document.Form1.submit();		
		   refreshOpener();
		   
		 //加载aja引擎
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
		 }
	  	return xmlHttp;
	}
</script>
</head>

  
 <body>
    <form name="Form1" method="post">	
    <br>
    
    <table cellSpacing="1" cellPadding="5" width="90%" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	 <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>
		  <s:if test="#request.viewFlag==null">
    编辑用户
   </s:if>
  <s:else>
    查看明细
  </s:else>
		 </strong></font>
		</td>
		
    </tr>
      <s:hidden name="userId" id="userId"></s:hidden>
      <s:hidden name="roleFlag" id="roleFlag"></s:hidden>
<s:hidden name="md5Flag" id="md5Flag"></s:hidden>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：
         <td class="ta_01" bgColor="#ffffff">
         <input name="logonName" type="text" maxlength="25" id="logonName" value="${user.logonName}" size="20" readonly="readonly">
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：


         <td class="ta_01" bgColor="#ffffff"><input name="userName" type="text" maxlength="25" value="${user.userName}" id="userName" size="20"> 
          <font color="#FF0000">*</font></td>
    </tr>
<tr>


<td align="center" bgColor="#f5fafe" class="ta_01">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
<td class="ta_01" bgColor="#ffffff">

<s:select list="#request.sexList" name="sexId" cssStyle="width:155px" listValue="ddlName" listKey="ddlCode"></s:select></td>
<s:if test="#request.roleFlag!=1">
<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：</td>
<td class="ta_01" bgColor="#ffffff">
<s:select list="#request.jctList" listKey="ddlCode"  listValue="ddlName" name="jctId" cssStyle="width:155px"> 
</s:select> <font color="#FF0000">*</font></td>
</s:if>
</tr>
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
<td class="ta_01" bgColor="#ffffff"><input id ="logonPwd" name="logonPwd" value="${user.logonPwd}" type="password" maxlength="25"  size=22 >
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
<td class="ta_01" bgColor="#ffffff"><input name="passwordconfirm"  value="${user.logonPwd}" type="password" maxlength="25"  size=22>
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="birthday" type="text"  value="${user.birthday}" maxlength="50"  size="20" onclick="JavaScript:calendar(document.Form1.birthday)" >
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
<td class="ta_01" bgColor="#ffffff"><input name="address" type="text" value="${user.address}" maxlength="50"  size="20">
</td>
</tr>
<s:if test="#request.roleFlag!=1">
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
<td class="ta_01" bgColor="#ffffff"><input name="contactTel" type="text" maxlength="25"  value="${user.contactTel}" size=20 value=""></td>
<td align="center" bgColor="#f5fafe" class="ta_01">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
<td class="ta_01" bgColor="#ffffff">
<input name="mobile" type="text" maxlength="25"  size=20 value="${user.mobile}">
</td>
</tr>
</s:if>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
<td class="ta_01" bgColor="#ffffff"><input name="email" type="text" maxlength="50" value="${user.email}" size=20 value="">
</td>
<s:if test="#request.roleFlag!=1">
<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
<td class="ta_01" bgColor="#ffffff">
<s:select list="#request.onDutyList" name="isDuty" cssStyle="width:155px" listKey="ddlCode"  listValue="ddlName"></s:select></td>
</tr>
</s:if>
<s:if test="#request.roleFlag!=1">
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">入学日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="onDutyDate" type="text" value="${user.onDutyDate}" maxlength="50" size=20  onclick="JavaScript:calendar(document.Form1.onDutyDate)">
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">离校日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="offDutyDate" type="text" value="${user.offDutyDate}" maxlength="50" size=20  onclick="JavaScript:calendar(document.Form1.offDutyDate)">
</td>
</tr>
<TR> 
<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
<TD class="ta_01" bgColor="#ffffff" colSpan="3"><input name="remark"  style="WIDTH:92%;"  value="${user.remark}">
</TD>
</TR>
 </s:if>
<TR>
<td  align="center"  colSpan="4"  class="sep1"></td>
</TR>
<tr>
	<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
	<s:if test="#request.viewFlag==null">
	<s:if test="#request.roleFlag==1">
	<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"  onClick="check_nullTwo()">
	 </s:if>
	 <s:else>
	 	<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"  onClick="check_null()">	 
	 </s:else>
	</s:if>	
	 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
	  <s:if test="#request.roleFlag!=1">
       <input   style="font-size:12px;   color:black; height=22;width=55" type="button" value="关闭"  name="Reset1"  onClick="window.close()">
      </s:if>
	</td>
</tr>
</table>　
</form>
</body>
</html>
