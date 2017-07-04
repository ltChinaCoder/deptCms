
<%@page import="cn.itcast.elec.util.PageBean"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page %>
<%String chomds=(String)request.getSession().getAttribute("chomd");%>
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
		
						
						<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加事件" name="BT_Add" 
						 onclick="openWindow('system/elecEventAction_add.do','700','400')">
						
					</td>
				
					<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Export" type="button" value="导出excel" name="BT_Export" 
						 onclick="f()">
						
					</td>
					<td>
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Improt" type="button" value="导入excel" name="BT_Improt"
						  onclick="openWindow('system/elecEventAction_importPage.do','700','400')">
						
					</td>
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=5>			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<TR>
											<TD  align="right" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">当前查询总分：</TD>
										<TD><input name="totalScore" type="text" id="totalScore"  size="10" readonly value="${request.totalScore}"></TD>
										</TR>
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">用户id</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">学时</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">内容</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">志愿时间</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">记录时间</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>			
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
								
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
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
										 
										   <a href="#" onclick="openWindow('system/elecEventAction_edit.do?eventId=<s:property value="%{#inf.eventId}"/>','700','400');">
										   
										   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="CURSOR:hand"></a>													
										
										</td>
					                      
					                     
				                     
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">
											
											<a href="system/elecEventAction_delete.do?eventId=<s:property value="%{#inf.eventId}"/>" onclick="return confirm('你确定要删除 ？')">
										
										<img src="${pageContext.request.contextPath }/images/delete.gif"  border="0" style="CURSOR:hand"></a>												
										
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
				             <td width="8%" align="center"><u><a href="#" onClick="gotopage('system/elecEventAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
				             <td width="10%" align="center"><u><a href="#" onClick="gotopage('system/elecEventAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
				             <%} %>
				             <%if(pagebean.getLastPage()){ %>
							 <td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
				             <td width="8%" align="center">末页</td>
				             <%}else{ %>
				             
				             <td width="10%" align="center"><a href="#" onClick="gotopage('system/elecEventAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></td>
				             <td width="8%" align="center"><u><a href="#" onClick="gotopage('system/elecEventAction_home.do','end')">末页</a></u></td>
				             <%} %>
				             <td width="6%" align="center">第<%=pagebean.getPageNo() %>页</td>
				             <td width="6%" align="center">共<%=pagebean.getSumPage() %>页</td>
				             <td width="21%" align="right">至第<input size="1" type="text" name="goPage" >页
				
				
				
				             <u><a href="#" onClick="gotopage('system/elecEventAction_home.do','go')">确定</a></u></td>
				             
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
