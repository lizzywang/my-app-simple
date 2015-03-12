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
<title>FriendList</title>
<script type="text/javascript"  src="../js/jquery.js"></script>

</head> 
<body>
<div>
<table class="table table-striped table-bordered">
<c:forEach items="${list }" var="person"  step="1" varStatus="s">          	
    <tr><td>${person.name }</td><td><a class="btn btn-default" href="/my-app-simple/friend/add?id=${person.id }" role="button">加为好友</a></td><td><a class="btn btn-default" href="/my-app-simple/friend/enterMsg?id=${person.id }" role="button">留言</a></td></tr>	
</c:forEach> 
</table>
</div>
<script type="text/javascript">

</script>
<script type="text/javascript"  src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
