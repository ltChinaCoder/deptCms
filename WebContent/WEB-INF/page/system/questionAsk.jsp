<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
function addEnter(element){
	   document.getElementById(element).value = document.getElementById(element).value+"<br>";
	   
}
function checkchar(){
	  
	  if(document.Form1.remark.value.length>500){
	  
	     alert("问题情况字数不能超过500字");
	     return;
	  }
	  if(document.Form1.remark.value.length==0){
		  
		     alert("问题情况不可为空");
		     return;
		  }
	  document.Form1.action="system/elecQuestionAction_save.do";
	  document.Form1.submit();
	  alert("提交成功");
}
</script>
</head>
<body>
<form name="Form1" id="Form1"  method=post>

	<table id="opperate1" cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>问题编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">问题描述：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	
			<textarea name="remark" id="remark"   style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('remark');"></textarea>
			</td>			
		</tr>
		<tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
			<input type="button" name="BT_Submit" value="提 交" onclick="checkchar()" id="BT_Submit" style="font-size:12px; color:black; height=20;width=50">
			</td>
		</tr>
		
	</table>	　 
</form>
</body>
</html>