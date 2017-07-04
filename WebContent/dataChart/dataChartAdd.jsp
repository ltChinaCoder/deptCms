



<%@ page language="java" pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>上传文件</title>
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/public.js"
			charset="gb2312"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"
			charset="gb2312"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/calendar.js"
			charset="gb2312"></script>
		<script type='text/javascript' src="${pageContext.request.contextPath }/script/validate.js"
			charset="gb2312"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"
			charset="gb2312"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/GB2312Codec.js"
			charset="gb2312"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/changePageBackUp.js"
			charset="gb2312"></script>
		<script language="javascript">
	 function init(){
	   document.getElementById("BT_Submit").disabled=true;
	 }
		function insertRows(){ 
          document.getElementById("BT_Submit").disabled=false;
		  var tempRow=0; 
		  var tbl=document.getElementById("filesTbl");
		  tempRow=tbl.rows.length; 
		  var Rows=tbl.rows;//类似数组的Rows 
		  var newRow=tbl.insertRow(tbl.rows.length);//插入新的一行 
		  var Cells=newRow.cells;//类似数组的Cells 
		  for (i=0;i<3;i++)//每行的3列数据 
		  { 
			 var newCell=Rows(newRow.rowIndex).insertCell(Cells.length); 
			 newCell.align="center"; 
			 switch (i) 
			{ 
			  case 0 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"15%\">"+tempRow+"</td>";break; 
			  case 1 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"60%\"><input name=\"files"+tempRow+"\"  type=\"file\" id=\""+tempRow+"\" size=\"45\"></td>";break; 
			  case 2 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"25%\"><a href='javascript:delTableRow(\""+tempRow+"\")'><img src=\"${pageContext.request.contextPath }/images/delete.gif\" width=15 height=14 border=0 style=CURSOR:hand></a></td>";break;
			} 
		  } 
		 } 


		function delTableRow(rowNum){ 

		   var tbl=document.getElementById("filesTbl");
			
			if (tbl.rows.length >rowNum){ 
			  
			   tbl.deleteRow(rowNum); 
			 
			  for (i=rowNum;i<tbl.rows.length;i++)
			   {
				 tbl.rows[i].cells[0].innerText=i;
				 tbl.rows[i].cells[2].innerHTML="<a href='javascript:delTableRow(\""+i+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";      
			  }
		   }
		   if(tbl.rows.length <=1)
		   {
		     document.getElementById("BT_Submit").disabled=true;
		   }
		} 

		function addFileList(){
			
			var tbl=document.getElementById("filesTbl");
		      for (i=1;i<tbl.rows.length;i++){
		   	  	var filePath = tbl.rows[i].cells[1].children.item(0).value;
		   	  	if(filePath == ""){
		   	  		 alert("请选择第"+ i +"行的文件路径！");
		   	  		 return false;
		   	  	}
		   	  }
		   	  if ( check() && overWriteFile("filesTbl","filesTb2") )
		   	  {		   	  
						document.Form1.action = "infAndPaperAddSave.do";			
				     	document.Form1.submit(); 
				         alert("保存成功！");
				         window.opener.onloadForm2();
				         window.close();
				
			  }
			  else
			  		return false ;
		   	 
                 
		}
		function check() {
		 if(document.getElementById("ssdw").value == 0)
		  {
		      alert("请选择所属单位！");
		      document.getElementById("ssdw").focus();
		      return false;
		  }
		  if(document.getElementById("belongTo").value == 0)
		  {
		    alert("请选择图纸分类！");
		    document.getElementById("belongTo").focus();
		    return false;
		  } 
		  return true;
		}
		function overWriteFile(tabName1,tabName2)
		{
			var tbl=document.getElementById(tabName1);
			var tb2=document.getElementById(tabName2);			
			var fileName1,fileName2 ;
			
		
			//1.检查"添加上传文件列表"中的文件是否有重名


			for (i=1;i<tbl.rows.length;i++)		
			{
				fileName1 = tbl.rows[i].cells[1].children.item(0).value;	//准备上传的完整文件路径
				fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
				if (fileName1.lastIndexOf("'")!=-1)
				{
						alert("上传文件名带有'错误字符'") ;
						return false ;
				}
				for (j=i+1;j<tbl.rows.length;j++)
				{
					fileName2 = tbl.rows[j].cells[1].children.item(0).value;	
					fileName2 = fileName2.substr(fileName2.lastIndexOf("\\")+1 ) ;
					
					if (Trim(fileName1) == Trim(fileName2))
					{
						alert("添加上传文件列表中存在与\""+fileName1+"\"的重名文件") ;
						return false ;
					}
				}
			}
			
			//2.检查"添加上传文件列表"与"已上传文件列表"中是否有重名文件			
			for (i=1;i<tbl.rows.length;i++)
			{				
				fileName1 = tbl.rows[i].cells[1].children.item(0).value;	//准备上传的完整文件路径
				fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
				for (j=1;j<tb2.rows.length;j++)
				{
					fileName2= tb2.rows[j].cells[1].children.item(0).innerText;	//已经上传的文件名
					if (Trim(fileName1) == Trim(fileName2))	//存在重名文件
					{
						if ( confirm("待上传的文件\""+fileName1+"\"已经存在，是否覆盖") )
							continue ;
						else
							return false ;
					}
					else
						continue ;
				}
			}
			
			return true ;
		}
		
		function getUrl(ssdw, filename){
			var strUrl = "";
			strUrl = "${pageContext.request.contextPath }/UploadFile/Paper/"+ssdw+"/" ;
			strUrl = strUrl + filename;	
			OpenWindow("new",strUrl,800,450);
		}
		
		function changeList() {
		 var ssdw = document.getElementById("ssdw").value;
		 var belongTo = document.getElementById("belongTo").value;
		 var str = 'showInfAndPaper.do?ssdw='+ssdw+'&belongTo='+belongTo;
		 Pub.submitActionWithForm('Form2',str,'Form1');
		}	
	
		
		</script>
	</HEAD>
	<body onload="init();">
		<br>
		
			<table cellSpacing="0" cellPadding="0" width="700" align="center"
				bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"
							colspan=3>
							<font face="宋体" size="2"><strong>文件上传管理</strong> </font>
						</td>
					</tr>
					<form name="Form1" method="post" enctype="multipart/form-data">
					<tr height="50">
						<td colspan="3">
						
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
										所属单位：
									</td>
									<td class="ta_01">
										<select name="ssdw" id="ssdw" style="width:160px"
											onchange="changeList();">
											
												<option value="0">
													全部
												</option>
											
											
												<option value="1">
													540
												</option>
											
												<option value="2">
													560
												</option>
											
												<option value="3">
													成都台
												</option>
											
												<option value="4">
													厦门台
												</option>
											
												<option value="5">
													553台
												</option>
											
												<option value="6">
													201台
												</option>
											
												<option value="7">
													202台
												</option>
											
												<option value="8">
													203台
												</option>
											
												<option value="9">
													哈尔滨台
												</option>
											
												<option value="10">
													西安台
												</option>
											
												<option value="11">
													中心
												</option>
											
												<option value="12">
													北京台
												</option>
											
												<option value="13">
													海南台
												</option>
											
										</select>
									</td>
									<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
										height="22">
										图纸类别：
									</td>
									<td class="ta_01">
										<font face="宋体" color="red"> <select name="belongTo"
												id="belongTo" style="WIDTH: 160px" onchange="changeList();">
												<option value="0">
													全部
												</OPTION>
												
													<option
														value="1">
														国内图书
													</option>
												
													<option
														value="2">
														香港图书
													</option>
												
											</select> </font>

									</td>

								</tr>
							</table>
						</td>
					<tr>
						
							<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif">
								<img src="${pageContext.request.contextPath }/images/yin.gif" width="15">
							</TD>
							<TD class="DropShadow" align="left"
								background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>
								添加上传文件列表
							</TD>
						
						
						<TD width="80%" align="right">
							
								<input type="button" name="BT_Add" value="添加" id="BT_Add"
									onclick="insertRows();"
									style="font-size:12px; color:black; height=20">
							
							
								<input type="button" name="BT_Submit" value="上传" id="BT_Submit"
									onclick="addFileList();"
									style="font-size:12px; color:black; height=20">
							
							<input type="button" value="关闭" onClick="window.close();"
								style="font-size:12px; color:black; height=20">
						</TD>
					</tr>
					
						<tr>
							<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>
								<table cellspacing="0" cellpadding="1" rules="all"
									bordercolor="gray" border="1" id="filesTbl"
									style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
									<tr
										style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
										<td class="ta_01" align="center" width="20%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											编号
										</td>
										<td class="ta_01" align="center" width="60%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											选择待上传文件
										</td>
										<td class="ta_01" align="center" width="20%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											删除
										</td>
									</tr>

								</table>
							</td>
						</tr>
					
					
					<tr height=10>
						<td colspan=3 bgcolor="#ffffff"></td>
					</tr>
					</form>
					<tr>
						<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif">
							<img src="${pageContext.request.contextPath }/images/yin.gif" width="15">
						</TD>
						<TD class="DropShadow" align="left"
							background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>
							已上传文件列表
						</TD>
						<TD width="80%"></TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>
						<form name="Form2"  id="Form2" style="margin:0px;">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="filesTb2"
								style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">

								<tr
									style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
									<td class="ta_01" align="center" width="20%"
										background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
										编号
									</td>
									<td class="ta_01" align="center" width="60%"
										background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
										已上传文件
									</td>
									
										<td class="ta_01" align="center" width="20%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
										&nbsp;
										</td>
									
								</tr>
								   
							</table>
						</form>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
</HTML>

