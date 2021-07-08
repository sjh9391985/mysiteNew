<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath }/ejs/ejs.js" type="text/javascript"></script>

<script type="text/javascript">
/* 
	[ gusetbook application based on jQuery ]
 	과제: 
 	ex1) 리스트 구현 
 	(no 기준으로 정렬, 3개씩 가져와서 append 하기 => list 여러개 랜더링 작업: append)
 	1. button event 구현 -> scroll event로 바꾼다.
 	2. no 기준으로 동적 쿼리를 repository에 구현한다.
 	렌더링 참고: ch08/test/gb/ex1
 		
 	ex2) add message register
 	1. form submit depense
 	2. data 하나를 렌더링작업(prepand)
 	3. validation(메세지 다이얼로그 plugin 사용법)
 	렌더링 참고: ch08/test/gb/ex2
 	
 	ex3) delete 메세지 삭제
 	1. a tag 기본동작 막기
 	2. form 기반 다이얼로그 plugin 사용법
 	3. live event
 	4. 응답에 대해서 해당 li element 삭제
 	5. 비밀번호가 틀린 경우 처리(삭제 실패, no=0) 사용자한테 알려주는 UI
 	   삭제가 성공한 경우(no > 0), ex) data-no=10인 li element를 삭제
 	렌더링 참고: ch08/test/gb/ex3
 	
*/
var lastScrollTop = 0;
$(function(){
	$(window).scroll(function(){
		var $window = $(this);
		var scrollTop = $window.scrollTop(); // 스크롤의 현재 위치
		var windowHeight = $window.height(); // 윈도우창 가변높이 
		var documentHeight = $(document).height(); // 도큐먼트 높이로 고정
		if(scrollTop > lastScrollTop){
			if(scrollTop >= documentHeight - windowHeight){
				var no = $("#list-guestbook li:last").data("no");
				fetch(no);
			}
		}
	})
})

var listItemEJS = new EJS({
	url: "${pageContext.request.contextPath }/ejs/listitem-template.ejs"
 });
 
var listEJS = new EJS({
	url: "${pageContext.request.contextPath }/ejs/list-template.ejs"
});
 	   
var fetch = function(no){
	$.ajax({
		url: "${pageContext.request.contextPath }/guestbook/api/list/"+no,
		dataType: "json",  // 받을 때 포맷
		type: "get",      // 요청 method  
		success: function(response){
			var html = listEJS.render(response);
			$("#list-guestbook").append(html);
		}
	});
}

var valid = function(str){
	$("#valid-message").text(str);
	$("#dialog-message").dialog({
		title: '알림',
		modal: true,
		buttons: {
			"확인": function(){
				$(this).dialog("close");
			}
		}				
	});
}
var add = function(){
	$("#add-form").submit(function(event){
		event.preventDefault();
		vo = {}
		
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.message = $("#tx-content").val();
		
		// validation name
		if(vo.name == "") {
			valid("이름이 비어 있습니다.");
			return;
		}
		
		// validation password
		if(vo.password == "") {
			valid("비밀번호가 비어 있습니다.");
			return;
		}
		
		// validation message
		if(vo.message == "") {
			valid("내용이 비어 있습니다.");
			return;
		}		
		
		// 데이터 등록
		$.ajax({
			url: "${pageContext.request.contextPath }/guestbook/api/add",
			dataType: "json",
			type: "post",
			contentType: "application/json",   
			data: JSON.stringify(vo),
			success: function(response){
				var html = listItemEJS.render(response.data);
				$("#list-guestbook").prepend(html);
			}
		});		
		
	})
}

$(function(){
	$("#btn-fetch").click(function(){
		var no = $("#list-guestbook li:last").data("no");
		fetch(no);
	})
	fetch(0);
	add();
	
	$(document).on("click", "#list-guestbook li a", function(event){
		event.preventDefault();
		let no = $(this).data("no");
		$("#hidden-no").val(no);
		
		deleteDialog.dialog("open");
	});
	
	// 삭제 다이알로그 만들기
	const deleteDialog = $("#dialog-delete-form").dialog({
		autoOpen: false,
		width: 300,
		height: 220,
		modal: true,
		buttons: {
			"삭제": function(){
				const no = $("#hidden-no").val();
				const password = $("#password-delete").val();
				$.ajax({
					url: "${pageContext.request.contextPath }/guestbook/api/delete/"+no,
					dataType: "json",
					type: "post",
					data: "password=" + password,
					success: function(response){
						if(response.data == -1){
							// 비밀번호가 틀린경우.
							$("#password-delete").val("");
							$(".validateTips.error").show();
							return;
						}						
						
						$("#list-guestbook li[data-no=" + response.data + "]").remove();
						deleteDialog.dialog('close');
					}
				});					
			},
			"취소": function(){
				$("#password-delete").val("");
				$(this).dialog("close");
			}
		},
		close: function(){
			//1. password 비우기
			$("#password-delete").val("");
			//2. no 비우기
			$("#hidden-no").val("");
			//3. error message 숨기기.
			$(".validateTips.error").hide();
			console.log("다이알로그 폼 데이터 정리 작업");
			
		}
	});
});
</script>

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				
				<ul id="list-guestbook"></ul>
				
				<div style="margin: 20px 0 0 0 ";>
					<button id="btn-fetch">다음 가져오기</button>
				</div>
			</div>
			
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>