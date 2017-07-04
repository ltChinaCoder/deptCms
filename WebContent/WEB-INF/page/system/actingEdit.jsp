

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<title>待办事宜编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">

  <script language="javascript"> 
  function checkchar(){
  
  if(document.Form1.stationRun.value.length>500){
  
     alert("学院公告情况字数不能超过500字");
     return;
  }
  if(document.Form1.devRun.value.length>500){
  
     alert("最近志愿活动情况字数不能超过500字");
     return;
  }
  document.Form1.action="system/elecCommonMsgAction_save.do";
  document.Form1.submit();
  loading();
  opener.location.reload();
  }
  function addEnter(element){
   document.getElementById(element).value = document.getElementById(element).value+"<br>";
   
  }
  var len = 500 ;
	var add = 0 ;
	function openContenFrame(){
	    var td1 = document.getElementById('tdOne') ;
	    var td2 = document.getElementById('tdTwo') ;
	    add = add+10 ;
	    td1.width = add ;
	    if(len - add <= 0){
	       td2.width = 1 ;
	    }else{
	       td2.width = len - add ;
	    }
	    if(add<=len) {
		   ;
	    }else{
	       td1.width = 1 ;
	       td2.width = 500 ;
	       add = 0 ;
	    }
	    setTimeout('openContenFrame()',100) ;
	}
	function loading(){
	    document.getElementById("load").style.display="";
	    document.getElementById("opperate1").style.display="none";
	    openContenFrame();
	}
  </script>


</head>

<body>
<form name="Form1" id="Form1" method=post >

	<table id="opperate1" cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>待办事宜编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">学院公告：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	  
			<textarea name="stationRun" id="stationRun"   style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('stationRun');">${ecmf.stationRun}</textarea>
			</td>
			<td>
			<input type="hidden" name="comID" id="comID" value="${ecmf.comID} "/>
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">最近志愿活动：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<textarea name="devRun" id="devRun"  style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('devRun');">${ecmf.devRun}</textarea>
			</td>
			
		</tr>
        <tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
			<input type="button" name="BT_Submit" value="保存" onclick="checkchar()" id="BT_Submit" style="font-size:12px; color:black; height=20;width=50">
			</td>
		</tr>
	</table>	　 
</form>
<table id="load" width="700" border="0" align="center" bgcolor="#FAFAFA" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;display:none ">
	  <tr>
	    <td><br><br>
	    <table  width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#287BCE" style="border-collapse:collapse ">
	        <tr bgcolor="#F7F7F6">
	          <td width="20%" height="100" valign="middle">
			    <table align='center' width='500'>
			      <tr>
			       <td colspan='2' align='center' ><font size="2">
			        正在进行保存待办事宜，用时较长，请稍后...
			        </font>
			       </td>
			      </tr>
			      <tr>
			        <td id='tdOne' height='25' width=1 bgcolor="blue">&nbsp;</td>
			        <td id='tdTwo' height='25' width=500 bgColor='#999999'>&nbsp;</td>
			      </tr>
			    </table>
	          </td>
	        </tr>
	    </table>
	    </td>
	  </tr>
	</table>
</body>
</html>