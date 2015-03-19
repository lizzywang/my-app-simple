<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My-app-simple</title>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" href="/my-app-simple/bootstrap/css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript"  src="/my-app-simple/js/jquery.js"></script>
<style type="text/css">
.info{
  color:red;
  background-color:yellow;
}
</style>
</head>
<body>
<div class="col-sm-4">
<div class="panel panel-default">
<h3 class="info">${message} </h3>
  <div class="panel-body">
  <form class="form-horizontal" action="/my-app-simple/simple/login" method = "post">

 <div class="form-group">
    <label for="inputname" class="col-sm-3 control-label">用户名</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputname" placeholder="请输入用户名" name = "name">
    </div>
 </div>

<div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name ="pass">
    </div>
</div>

<div class="form-group">
<div class="col-sm-offset-3">
  <label class="radio-inline">
  <input type="radio" name="day" id="inlineRadio1" value="7" checked="checked"> 一周
</label>
<label class="radio-inline">
  <input type="radio" name="day" id="inlineRadio2" value="30"> 一个月
</label>
<label class="radio-inline">
  <input type="radio" name="day" id="inlineRadio3" value="90"> 三个月
</label>
</div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3">
      <div class="checkbox">
        <label>
          <input type="checkbox" name = "mark" value="mark">记住密码
        </label>
      </div>
    </div>
  </div>
 <div class="form-group">
    <div class="col-sm-offset-4">
      <button type="submit" class="btn btn-default" name ="submit">登录</button>
<a class="btn btn-default" href="/my-app-simple/simple/signup" role="button">注册</a>      
    </div>
  </div>
</form>
</div>
  </div>
</div>
<script type="text/javascript"  src="/my-app-simple/js/jquery.js"></script>
<script src="/my-app-simple/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>