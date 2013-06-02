

<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

</head>
<body>
<h3>New meeting</h3>
<form id="addMeeting">
 <label for="userId">Enter user id: </label>
 <input id="userId" name="userId" type="text"><br/>
 <label for="meetingStart">Enter meeting date: </label>
 <input id=meetingStart name="meetingStart" type="text"><br/>
 <label for="duration">Enter duration (hours): </label>
 <input id="duration" name="duration" type="text">
 
 <br/>
 <input type="submit"  value="add new meeting">
 </form>
  <br/>
 <p id="result_text"></p>
  <br/>
  <ul></ul>

 
 <script type="text/javascript">
 
$("#addMeeting").submit(function (e) {
	$.getJSON('${pageContext.request.contextPath}/api/add',$(this).serialize(),renewMeetingList)
			.error(renewMeetingList(null));
	e.preventDefault(); // prevent actual form submit and page reload
});

$(function(){
	$.getJSON('${pageContext.request.contextPath}/api/meetings',renewMeetingList);
});

function renewMeetingList(list){
	if(!(list instanceof Array)) $('#result_text').text("error");
	else if(list.length < 1) $('#result_text').text("no meetings");
	else{
		$('#result_text').text("");
		var ul = $('ul').empty();
		$.each(list, function(index,item) {
			var date = new Date();
			date.setTime(item.meetingStart);
			ul.append('<li> user: '+item.userId +'\t Start: ' + date+ '\tDuration: '+ item.duration+ '</li>');
		  });
	}
}
 
</script>
</body>
</html>

