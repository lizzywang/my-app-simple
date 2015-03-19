<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script type="text/javascript"  src="../js/jquery-impromptu.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link> 
<link rel="stylesheet" href="../css/jquery-impromptu.css" type="text/css"></link> 
</head>
<body>
<center>
<div class="col-sm-5">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">用户注册</h3>
  </div>
<div class="panel-body">
	<form id = "myform">
 		<div class="form-group">
    		<label for="inputname" class="col-sm-3 control-label">用户名</label>
    	<div class="col-sm-9">
     	 	<input type="text" class="form-control" id="inputname"  name = "name">
    	</div>
 </div>

<div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPassword3" name ="pass">
    </div>
</div>

<input type ="button" class="btn btn-default" id = "signup" value="注册">
</form>
</div>
</div>
</div>
</center>

<script type="text/javascript">
$(function(){
	$('#signup').on('click',function(){
		var name = $('#inputname').val();
		var pass = $('#inputPassword3').val();
		$.ajax({
			url:"/my-app-simple/simple/sign",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			type:"post",
			data:JSON.stringify({"name":name,"pass":pass}),
			success:function(data){
				if(data.check == 1){
					alert("注册成功，请登录~");
					$.prompt("注册成功，请登录~");
					window.location.href="/my-app-simple/simple/login";	
					
				}
			}
		});
	});
});
</script>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script type="text/javascript"  src="../js/jquery-impromptu.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>


