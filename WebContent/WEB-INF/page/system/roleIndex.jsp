


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<HTML>
	<HEAD>
		<title>角色权限管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript">
		
		 function saveRole(){
		 
           document.Form2.roleid.value=document.Form1.role.value;
		   document.Form2.action="system/elecUserRoleAction_save.do";
		   document.Form2.submit();
		}
		
       
       function selectRole(){
          
          if(document.Form1.role.value=="0"){
          
             document.Form1.action="system/elecUserRoleAction_home.do";
             document.Form1.submit();            
          }else{           
            Pub.submitActionWithForm('Form2','system/elecUserRoleAction_edit.do','Form1');
          }
       }
       
		function checkAllselect(oper)
		{
			var selects=document.getElementsByName("selectoper");
			for (var int = 0; int < selects.length; int++) {
				 selects[int].checked=(oper.checked);
			}
		}
		function checkAllselectuser(oper)
		{
			var selects=document.getElementsByName("selectuser");
			for (var int = 0; int < selects.length; int++) {
				 selects[int].checked=(oper.checked);
			}
		}
		</script>
	</HEAD>
		
	<body>
	 <Form name="Form1" id="Form1"  method="post" style="margin:0px;">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>角色管理</strong></font>
					</td>
				</tr>	
				<tr>
				   <td class="ta_01" colspan=2 align="right" width="100%"  height=10>
				   </td>
				</tr>		
				<tr>
					<td class="ta_01" align="right" width="35%" >角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="ta_01" align="left"  width="65%" >
					
					<s:select list="#request.roleList" name="role" cssClass="bg" headerKey="0" headerValue="请选择" cssStyle="width:180px" onchange="selectRole()"  listKey="ddlCode" listValue="ddlName"></s:select>					 					 					 					   
					  
					</td>				
				</tr>
			    
			    <tr>
				   <td class="ta_01" align="right" colspan=2 align="right" width="100%"  height=10></td>
				</tr>
				
			</TBODY>
		  </table>
 </Form>

<Form  name="Form2" id="Form2"  method="post" style="margin:0px;">
 
  <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
 <tr>
  <td>
   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left">权限分配</legend>
 
     <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">			 
			  <tr>
				 <td class="ta_01" colspan=2 align="left" width="100%" > 
			 			   
				  <s:set value="%{''}" scope="request" var="parentCode"></s:set>
				  <s:if test="%{#request.xmlList!=null}">
				  <s:iterator value="%{#request.xmlList}" var="xml">
				  <s:if test="%{#request.parentCode == #xml.parentCode}">
				   <input type="checkbox"  name="selectoper" value="<s:property value="%{#xml.code}"/>" >
				    <s:property value="%{#xml.name}"/>
				  </s:if>
				  <s:else>
				  <br>
				  <s:iterator begin="0" end="%{8-#xml.parentName.length()}" step="1">
				  &nbsp;&nbsp;&nbsp;
				  </s:iterator>
				  <s:set value="%{#xml.parentCode}" scope="request" var="parentCode"></s:set>
			        <s:property value="%{#xml.parentName}"/>:
				    <input type="checkbox"  name="selectoper" value="<s:property value="%{#xml.code}"/>" >
				    <s:property value="%{#xml.name}"/>
				  </s:else>				 
				  </s:iterator>
				  </s:if>					
				   </td>
				</tr>						
				 <input type="hidden" name="roleid" >						
		 </table>	
        </fieldset>
	  </td>
	 </tr>					
  </table>
		    				    
	</Form>
	</body>
</HTML>
