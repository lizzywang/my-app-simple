<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="../js/jquery.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
</head>
<body>
<center>
<h1>用户注册</h1>

<form id = "myform">
 <div class="form-group">
    <label for="inputname" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputname"  name = "name">
    </div>
 </div>

<div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3" name ="pass">
    </div>
</div>
<button class="btn btn-default" id = "signup">注册</button>
</form>
</center>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#signup').on('click',function(){
		$.ajax({
			url:"/my-app-simple/simple/sign",
			data:$('#myform').serialize(),
			type:"post",
			success:function(data){
				if(data.check == 1){
					console.log(data.check);
				}
			}
		});
	});
});
</script>
</body>
</html>


