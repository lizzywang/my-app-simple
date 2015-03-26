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
<script src="../bootstrap/js/bootstrap.min.js"></script>
<title>个人主页</title>
<script type="text/javascript">

$(document).ready(function(){
	getUncheckedMsg();
	
});
function refreshPage(){
 	 window.location.href="/my-app-simple/friend/enterMsg?id=${admin1.id}";
 	
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
				console.log(id);
				console.log(count);
				
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
    <tr><td><a href="/my-app-simple/friend/enterMsg?id=${person.id }">${person.name }<span class="badge">3</span></a></td><td><a class="btn btn-default" role="button">留言</a></td></tr>	
</c:forEach> 
</table>
</div>
</div>

<div>
	<div class = ""></div>
</div>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>