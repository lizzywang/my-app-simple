<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../assets/css/amazeui.min.css">
<script src="http://code.jquery.com/jquery-1.11.2.js"></script>
  <base href="http://www.jsviews.com/samples/"/>
     
  <link href="samples.css" rel="stylesheet"/>
  <script src="../download/jsrender.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div id="result"></div>

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
    			<a href>
    			<i class="am-icon-reply"></i>
    		</a>
    	</div>
    </footer>
  </div>
</article>
</script>

<script>
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
var data = [{"commitName":"甜甜","from":2,"message":"欣欣姐，好~","timeStamp":1426568454000,"to":1,"toName":"王欣"},{"commitName":"王欣","from":1,"message":"嘻嘻","timeStamp":1426568333000,"to":1,"toName":"王欣"},{"commitName":"王欣","from":1,"message":"哈哈，好天气","timeStamp":1426568233000,"to":1,"toName":"王欣"}];

var htmlOutput = $.templates("#theTmpl").render(data);

$("#result").html(htmlOutput);
</script>
</body>
</html>