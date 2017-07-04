
<%@page import="cn.itcast.elec.util.PageBean"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page %>
<%String chomds=(String)request.getSession().getAttribute("chomd");%>
 <script language="javascript"> 
  function checkchar(){
  	document.Form1.action="system/userAction_main.do";
  	document.Form1.submit();
  }
  </script>

<HTML>
	<HEAD>
		<title>用户管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript">
		function f()
		{
			var path='system/elecUserAction_exportData.do';
			openWindow(path,'700', '400');			
		}
		function  gotopage(path,where){
		       var page=document.Form2.pageNO;
		       
		       if(where=="next"){ 
		     
		          page.value=document.Form2.nextpageNO.value;
		      
		       }else if(where=="prev"){
		     
		          page.value=document.Form2.prevpageNO.value;
		       }else if(where=="end"){
		     
		          page.value=document.Form2.sumPage.value;
		       }else if(where=="start"){
		          page.value=1;
		       }else if(where=="go"){
		         var theForm = document.Form2;   
		         if(Trim(theForm.goPage.value)=="")
			     {
				     alert("请输入页数"); 
				     theForm.goPage.focus();   
				     return false;
			     }
			     if(!checkNumber(theForm.goPage))
			     {
				     alert("请输入正确页数(数字)"); 
				     theForm.goPage.focus();     
				     return false;
			     }
			     
			     var objgo=parseInt(theForm.goPage.value);
			     var objsum=parseInt(theForm.sumPage.value);
			     if(objgo<=0||objgo>objsum){
			         alert("不存在此页，请重新输入页数"); 
				     theForm.goPage.focus();     
				     return false; 
			     }
		         
		         page.value=theForm.goPage.value;                
		      } 
		     
		      document.Form1.pageNO.value=document.Form2.pageNO.value;
		      document.Form1.pageSize.value=document.Form2.pageSize.value;
		      
		      Pub.submitActionWithForm('Form2',path,'Form1');       
		  }
		  
		  function gotoquery(path){
			 
		      document.Form1.pageNO.value=1;
		      document.Form1.pageSize.value==10;		      
		      Pub.submitActionWithForm('Form2',path,'Form1'); 
		      
		  }
		</script>
	</HEAD>		
	<body >
		<form id="Form1" name="Form1" action="system/elecUserAction_home.do" method="post" style="margin:0px;"> 
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
				<TR height=10><td></td></TR>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>用户信息管理</strong></font>
					</td>
					
				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					学号：</td>
					<td class="ta_01" >
			
					<input name="logonName" type="text" id="logonName"  size="21"><font face="宋体" color="red">
					 <input style="font-size:12px; color:black; height=20;width=80" id="BT_find" type="button" value="查询" name="BT_find" 
						 onclick="gotoquery('system/elecUserAction_home.do')" >
					</font></td>
					
					   
						
				</tr>
				<input type="hidden" name="pageNO" id="pageNO"/>
		     <input type="hidden" name="pageSize" id="pageSize"/>
		      <input type="hidden" name="initFlag" id="initFlag" value="1"/>				
		    </table>		    
		</form>




		<form id="Form2" name="Form2" action="/system/elecUserAction_home.do" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
										<TR>
											<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">用户列表</TD>
										</TR>
			             </TABLE>
                   </td>
		
						<% if (chomds.contains("l")){%>
						<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加用户" name="BT_Add" 
						 onclick="openWindow('system/elecUserAction_add.do','700','400')">
						<%}%>
					</td>
				
					<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Export" type="button" value="导出excel" name="BT_Export" 
						 onclick="f()">
						
					</td>
					<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Improt" type="button" value="导入excel" name="BT_Improt"
						  onclick="openWindow('system/elecUserAction_importPage.do','700','400')">			
					</td>
					
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=5>			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">学号</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">用户姓名</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">性别</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">联系电话</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">是否在职</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>			
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
								
							</tr>
							
							
								 <s:if test="#request.list!=null">
								 <s:iterator value="%{#request.list}" var="inf">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#inf.logonName}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
										<a href="#" onclick="openWindow('system/elecUserAction_edit.do?userId=<s:property value="%{#inf.userId}"/>&viewFlag=1','700','400');">
											<s:property value="%{#inf.userName}"/>
											</a>
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<s:property value="%{#inf.sexId}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#inf.mobile}"/>
										</td>									
										<td style="HEIGHT:22px" align="center" width="10%">
											<s:property value="%{#inf.isDuty}"/>
										</td>
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
										  <% if (chomds.contains("n")){%>
										   <a href="#" onclick="openWindow('system/elecUserAction_edit.do?userId=<s:property value="%{#inf.userId}"/>','700','400');">
										   
										   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="CURSOR:hand"></a>													
										<%} %>
										</td>
					                      
					                     
				                     
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">
											<% if (chomds.contains("m")){%>
											<a href="system/elecUserAction_delete.do?userId=<s:property value="%{#inf.userId}"/>" onclick="return confirm('你确定要删除 <s:property value="%{#inf.userName}"/> ？')">
										
										<img src="${pageContext.request.contextPath }/images/delete.gif"  border="0" style="CURSOR:hand"></a>												
										<%} %>
										</td>
										 
									</tr>
									</s:iterator>
									</s:if>
								
						</table>					
						
					</td>
				</tr> 
				
					<tr>
				       <td width="100%" height="1" colspan=5 >
				         <table border="0" width="100%" cellspacing="0" cellpadding="0" >
				         <%PageBean pagebean=(PageBean)request.getAttribute("page");%>
				           <tr>
				             <td width="15%" align="left">总记录数：<%=pagebean.getTotalResult() %>条</td> 
				             <td width="14%" align="right"></td>      
				             <%if(pagebean.getFirstPage()){ %>           
				             <td width="8%" align="center">首页&nbsp;&nbsp;|</td>
				             <td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
				             <%}else{ %>
				             <td width="8%" align="center"><u><a href="#" onClick="gotopage('system/elecUserAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
				             <td width="10%" align="center"><u><a href="#" onClick="gotopage('system/elecUserAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
				             <%} %>
				             <%if(pagebean.getLastPage()){ %>
							 <td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
				             <td width="8%" align="center">末页</td>
				             <%}else{ %>
				             
				             <td width="10%" align="center"><a href="#" onClick="gotopage('system/elecUserAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></td>
				             <td width="8%" align="center"><u><a href="#" onClick="gotopage('system/elecUserAction_home.do','end')">末页</a></u></td>
				             <%} %>
				             <td width="6%" align="center">第<%=pagebean.getPageNo() %>页</td>
				             <td width="6%" align="center">共<%=pagebean.getSumPage() %>页</td>
				             <td width="21%" align="right">至第<input size="1" type="text" name="goPage" >页
				
				
				
				             <u><a href="#" onClick="gotopage('system/elecUserAction_home.do','go')">确定</a></u></td>
				             
				             <td><input type="hidden" name="pageNO" value="<%=pagebean.getPageNo()%>" ></td> 
				             <td><input type="hidden" name="prevpageNO" value="<%=(pagebean.getPageNo()-1)%>"></td>
				             <td><input type="hidden" name="nextpageNO" value="<%=(pagebean.getPageNo()+1)%>"></td>
				             <td><input type="hidden" name="sumPage" value="<%=pagebean.getSumPage() %>" ></td>
				             <td><input type="hidden" name="pageSize" value="" ></td> 
				           </tr>
				         </table>       
				       </td>
				     </tr>        
			</TBODY>
		</table>
		</form>
	</body>
</HTML>
