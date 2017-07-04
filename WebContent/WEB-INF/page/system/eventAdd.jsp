
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
  <head>
   <title>添加事件</title>
  <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
				<script language="javascript" src="${pageContext.request.contextPath }/script/jquery-3.2.1.min.js"></script>
		
		<script language="javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		 <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <Script language="javascript">
	function check_null(){
	    
	    var theForm=document.Form1;
	    
	    if(theForm.userId.value.toString().length!=12)
		{
			alert("用户id为12位，请核实");
			theForm.userId.focus();
			return false;
		}
	    if(Trim(theForm.score.value)=="")
		{
			alert("学时不能为空");
			theForm.score.focus();
			return false;
		}
		var pattern = /^[0-9]+/;
		flag = pattern.test(theForm.score.value);
		if(!flag)
			{
			alert("学时只能为正数");
			theForm.score.focus();
			return false;
			}
	    if(Trim(theForm.doDate.value)=="")
		{
			alert("志愿时间不可为空 ");
			theForm.doDate.focus();
			return false;
		}
	   if(theForm.remark.value.length==0){
     
        	alert("备注不可为空");
			theForm.remark.focus();
			return false; 
        }			  
	     document.Form1.action="system/elecEventAction_save.do";
	    
	     	document.Form1.submit();
	     
		 refreshOpener();
	}
   </script>
  </head>
  
 <body>
 
  <form name="Form1" method="post" id="myform">
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

    <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>添加用户</strong></font>
		</td>
    </tr>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">用户&nbsp;&nbsp;id：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff"><input name="userId" type="text" maxlength="25" id="userId" size="20" >
          </td>
    </tr>


<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">学&nbsp;&nbsp;&nbsp;时：</td>
<td class="ta_01" bgColor="#ffffff"><input name="score" type="text" maxlength="25"  size=20 >
</td>
</tr>
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">志愿时间：</td>
<td class="ta_01" bgColor="#ffffff"><input name="doDate" type="text" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.doDate)">
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
	<!--  onClick="check_null()" -->
	<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55" onClick="check_null()">
	 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
	<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">		
	</td>
</tr>
</table>　
</form>

</body>
</html>
