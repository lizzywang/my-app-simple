<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My-app-Simple</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	updateMsg();
	$("#chatform").click(function(){
		
		$.post("/my-app-simple/simple/chat",{
			message:$("#msg").val(),
			name:$("#author").val(),
		},function(data){
			if(data.check==1){
				alert("success");
			}
		});
		return false;
	});
});


function updateMsg(){

	$.getJSON("/my-app-simple/simple/getchat",function(data){
		console.log(data);
		var html = $("#messagewindow").html();
		console.log(html);
		$.each(data,function(commentIndex,comment){
			
			var name = comment.name;
			var content = comment.content;
			
			html+='<div class="comment"><h6>'+name+':</h6><p class="para">'+content+'</p></div>';
		console.log(html);
		});
		$("#messagewindow").html(html);
	
		console.log(html);
	});
	setTimeout('updateMsg()',4000);
}

</script>
</head>
<body>
<h1>${message }</h1>
<div id="wrapper">

	<div id="messagewindow"></div>
	<form>
		姓名：<input type="text" id="author" size="50"/><br>
		内容：<input type="text" id="msg" size="50"/><br>
		<input type="button" value="发送" id="chatform"/><br>
	</form>
</div>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>