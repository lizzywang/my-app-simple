<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page import="com.mygudou.app.model.Admin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>留言板</title>
<script type="text/javascript"  src="../js/jquery.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
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
      <a class="navbar-brand" href="#">${admin.name }的主页</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
        <li class="active"><a href="/my-app-simple/friend/getList">我的好友 <span class="sr-only">(current)</span></a></li>
        
       
      </ul>
      <form class="navbar-form navbar-left" role="search" action ="/my-app-simple/friend/search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" name = "name">
        </div>
        <button type="submit" class="btn btn-default" id ="search">找人</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="panel panel-primary col-sm-5 col-sm-offset-3">
  <div class="panel-heading">${admin1.name }的留言板</div>
  <div class="panel-body">
   	<textarea rows="7" cols="50" id ="message"></textarea>
  </div>
  <div class="panel-footer">
  <button id="leaveMessage">留言</button>
  </div>
</div>
<div id="MessageList">
</div>
<script type="text/javascript">
$(document).ready(function(){
	updateMsg();
	$('#leaveMessage').on('click',function(){
		console.log('hi');
		var to = ${admin1.id };
		console.log(to);
		var from = ${admin.id};
		console.log(from);
		var message = $('#message').val();
		console.log(message);
	
		$.ajax({
			url:"/my-app-simple/message/add",
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data:JSON.stringify({"from":from,"to":to,"message":message}),
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
  
function updateMsg(){
	var html='';
	var id = ${admin1.id};
	$.ajax({
		url:"/my-app-simple/message/getMessageList",
		type:"get",
		data:{"id":id },
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:function(data){
			$.each(data,function(index,note){
				var id = note.from_id;
				var message = note.message;
				html+='<div class="comment"><h6>'+id+':</h6><p class="para">'+message+'</p></div>';
				
			});
			$("#MessageList").html(html);
		}
		
		
	});
	setTimeout('updateMsg()',4000);
}
</script>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>