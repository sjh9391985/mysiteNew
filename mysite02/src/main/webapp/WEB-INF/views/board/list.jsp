<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite02</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="hidden" name="a" value="search">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
			
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>			
					
	  				<c:set var="count" value="${totalCount}" />
					<c:forEach begin="0" end="5" items="${boardList }" var="board" varStatus="boardNo">
					<tr>
						<td>${count - boardNo.index-(pageInfo.currentPage-1)*5 }</td>
						<td style="text-align:left padding-left:0px"><a href="${pageContext.request.contextPath }/board?a=view&no=${board.no}">${board.title }</a></td>
						<td>${board.userName }</td>
						<td>${board.hit }</td>
						<td>${board.regDate }</td>
						<c:if test="${board.userName == authUser.name }">
						<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${board.no}" class="del">삭제</a></td>
						</c:if>
					</tr>
					</c:forEach>					
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${pageInfo.nowPage > 0 }">
						<li><a href="${pageContext.request.contextPath }/board?p=${pageInfo.prevPage}">◀</a></li>
						</c:if>
						<c:forEach begin="${pageInfo.firstPage }" end="${pageInfo.lastPage }" var="page">
							
						<c:if test="${page<=pageInfo.totalPage && page!=pageInfo.currentPage}">
						<li><a href="${pageContext.request.contextPath }/board?p=${page }">${page }</a></li>
						</c:if>
						</c:forEach>

						<li><a href="${pageContext.request.contextPath }/board?p=${pageInfo.nextPage}">▶</a></li>
					</ul>
				</div>			
				
						
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>