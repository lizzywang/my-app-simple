<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script type="text/javascript"  src="../js/jsrender.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<title>个人主页</title>
<style type="text/css">
.bubble { margin:0 auto; width:500px; }
.demo {
    margin-bottom:20px;
    padding-left:50px;
    position:relative;
}
 
.triangle {
    position:absolute;
    top:50%;
    margin-top:-8px;
    left:42px;
    display:block;
    width:0;
    height:0;
    overflow:hidden;
    line-height:0;
    font-size:0;
    border-bottom:8px solid #FFF;
    border-top:8px solid #FFF;
    border-left:none;
    border-right:8px solid #3079ED;
}
 
.demo .article {
    float:left;
    color:#FFF;
    display:inline-block;
    *display:inline; zoom:1;
    padding:5px 10px;
    border:1px solid #3079ED;
    background:#eee;
    border-radius:5px;
    background-color: #4D90FE;
    background-image:-webkit-gradient(linear,left top,left bottom,from(#4D90FE),to(#4787ED));
    background-image:-webkit-linear-gradient(top,#4D90FE,#4787ED);
    background-image:-moz-linear-gradient(center top , #4D90FE, #4787ED);
    background-image:linear-gradient(top,#4D90FE,#4787ED);
}
 
.fr { padding-left:0px; padding-right:50px; }
 
.fr .triangle {
    left:auto;
    right:42px;
    border-bottom:8px solid #FFF;
    border-top:8px solid #FFF;
    border-right:none;
    border-left:8px solid #3079ED;
}
 
.fr .article {
    float:right;
}
.dialog{
width:540px;
}
.send{
position:relative;
right:-450px;
}

.newMsg{background-color:Yellow!important;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	getUncheckedMsg();
	
	$('#sendMsg').click(function(){
		$.ajax({
			url:"/my-app-simple/message/add",
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data:JSON.stringify({"from":from,"to":to,"message":message,"commitName":commitName,"toName":toName}),
			success:function(data){
				if(data.check == 1){
					refreshPage();	
					
				}
			}
		});
	});
	
});
function refreshPage(){
 	 window.location.href="/my-app-simple/friend/enterMsg?id=${admin1.id}";
 	
}

function getConversation(to_id,from_id){
	$.ajax({
		url:"/my-app-simple/message/updateChecked",
		type:"post",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify({"from_id":from_id,"to_id":to_id}),
		success:function(){
			$('#friend_'+from_id+' span').remove();
		}
	});
	$.ajax({
		
		url:"/my-app-simple/message/getConversation2",
		type:"post",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data:JSON.stringify({"from_id":from_id,"to_id":to_id}),
		success:function(data){
			console.log(data);
			
			var app = {
					    "conversation" : data
					  };
			var htmlOutput = $.templates("#theTmpl").render(app);
			$("#MessageList").html(htmlOutput);
		}
	});
}

function getUncheckedMsg(){
	var id = ${admin.id}
	$.ajax({
		url:"/my-app-simple/message/getuncheckedMessage",
		type:"get",
		data:{"id":id },
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:function(data){
			$.each(data,function(commentIndex,message){
				
				var id = message.id;
				var count = message.count;
				$('#friend_'+id+' span').remove();
				$('#friend_'+id).append('<span class="badge">'+count+'</span>');
				
			});
		}
		
	});
	setTimeout('getUncheckedMsg()',4000);
}

</script>

</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/my-app-simple/simple/login">${admin.name }的主页</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
     
      <form class="navbar-form navbar-left" role="search" action ="/my-app-simple/friend/search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" name = "name">
        </div>
        <button type="submit" class="btn btn-default" id ="search">找人</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">退出登录</a></li>
        
      </ul>
    </div>
  </div>
</nav>
<div style = "width:20%">
<div class="panel panel-default">
<div class="panel-heading">我的好友</div>

	<table class="table table-striped table-bordered">
	<c:forEach items="${friendslist }" var="person"  step="1" varStatus="s">          	
    <tr><td id="friend_${person.id }"><a href="/my-app-simple/friend/enterMsg?id=${person.id }">${person.name }</a></td>
    <td><button class="btn btn-default"  onclick="getConversation(${admin.id},${person.id} )">留言</button></td></tr>	
</c:forEach> 
</table>
</div>
</div>

<div id="MessageList"></div>
<script id="theTmpl" type="text/x-jsrender">

<div class = "dialog">
<div class="panel panel-primary">
  <div class="panel-heading">对话框</div>
  <div class="panel-body">
   <div class="bubble">
		{{for conversation}}
		{{if to == ${admin.id}}}
		 <div class="demo clearfix">
            <span class="triangle"></span>
            <div class="article">{{:message}}</div>
        </div>
		{{else}}
		<div class="demo clearfix fr">
            <span class="triangle"></span>
            <div class="article">{{:message}}</div>
        </div>
		{{/if}}
       {{/for}}
</div>
  </div>
    <div class="panel-footer">
    <textarea rows="7" cols="60"></textarea>
	<div>
    <button class="btn btn-default send" type="submit" id="sendMsg">发送</button>
</div>
    </div>
  
</div>
</div>
</script>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"  src="../js/jsrender.min.js"></script>
</body>
</html>