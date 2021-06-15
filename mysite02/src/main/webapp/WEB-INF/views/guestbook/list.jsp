<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%pageContext.setAttribute("newLine", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>mysite02</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
					<li>
					<c:forEach items="${list }" var="vo" varStatus="status" >
						<table>
							<tr>
								<td>${vo.no}</td>
								<td>${vo.name}</td>
								<td>${vo.redDate}</td>
								<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=${vo.no}">삭제</a></td>
							</tr>
							
							<tr>
							<td colspan=4>${fn:replace(vo.message , newLine , "<br>") }</td>
							</tr>
							
						</table> <br>
						</c:forEach>
						
					</li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>