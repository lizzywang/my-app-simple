<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page import="com.mygudou.app.model.Admin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>留言板</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script type="text/javascript"  src="../js/jsrender.min.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="../assets/css/amazeui.min.css" type="text/css"></link>

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
<div class="body">
<div class="panel panel-primary">
  <div class="panel-heading">${admin1.name }的留言板</div>
  <div class="panel-body">
   	<textarea rows="7" cols="150" id ="message"></textarea>
  </div>
  <div class="panel-footer">
  <button id="leaveMessage" class = "btn btn-default btn-lg">留言</button>
  </div>
</div>
<br/>
<br/>
<div id="MessageList">
</div>

</div>

<script id="theTmpl" type="text/x-jsrender">
<article class="am-comment">
 

  <div class="am-comment-main">
    <header class="am-comment-hd">
    
      <div class="am-comment-meta">
        <a href="#link-to-user" class="am-comment-author">{{:commitName}}</a>
        评论于 <time datetime="2013-07-27T04:54:29-07:00" title="2013年7月27日 下午7:54 格林尼治标准时间+0800">{{:timeStamp}}</time>
      </div>
    </header>

    <div class="am-comment-bd">
     {{:message}}
    </div>
	<footer class="am-comment-footer">
    	<div class="am-comment-actions">
    		<a href>
    			<i class="am-icon-thumbs-up"></i>
    		</a>
    			<a href>
    			<i class="am-icon-thumbs-down"></i>
    		</a>
    			<a href ="/my-app-simple/friend/enterMsg?id={{:from}}">
    			<i class="am-icon-reply"></i>
    		</a>
    	</div>
    </footer>
  </div>
</article>
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	updateMsg();
	
	$('#leaveMessage').on('click',function(){
		console.log('hi');
		var commitName = '${admin.name}';
		var toName = '${admin1.name}';
		var to = ${admin1.id };
		console.log(to);
		console.log("from: "+commitName);
		console.log("to: "+toName);
		
		var from = ${admin.id};
		console.log(from);
		var message = $('#message').val();
		console.log(message);
	
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
		    
			var htmlOutput = $.templates("#theTmpl").render(data);

			$("#MessageList").html(htmlOutput);
		}
		
		
	});
	setTimeout('updateMsg()',4000);
}
function json2TimeStamp(milliseconds){
    var datetime = new Date();
    datetime.setTime(milliseconds);
    var year=datetime.getFullYear();
         //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
</script>

<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"  src="../js/jsrender.min.js"></script>
</body>
</html>