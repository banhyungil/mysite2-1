<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board?a=list"
					method="post">
					<input type="text" id="kwd" name="keyword"
						value="${param.keyword }"> <input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${status.count }</td>
							<td class="label"
								style="padding-left:${30*vo.depth-20}px;text-align:left">
								<c:if test="${vo.depth > 0}">
									<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:if>
								 <c:choose>
									<c:when test="${vo.status eq 1 }">
										<a href="${pageContext.servletContext.contextPath }/board?a=contentsform&no=${vo.no}">${vo.title }</a>
									</c:when>
									<c:otherwise>
										삭제된 게시글입니다.
									</c:otherwise>
								</c:choose>
								</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<c:if test="${authUser.no eq vo.userNo && vo.status eq 1}">
								<td><a
									href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}"
									class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a
							href="${pageContext.servletContext.contextPath }/board?a=list&currentPageBlock=${currentPageBlock }&keyword=${param.keyword }">◀</a></li>
						<c:forEach begin='${firstPage }' end='${lastPage }' step='1'
							var='page'>
							<li><a
								href="${pageContext.servletContext.contextPath }/board?a=list&currentPage=${page }">${page }</a></li>
						</c:forEach>
						<li><a
							href="${pageContext.servletContext.contextPath }/board?a=list&currentPageBlock=${currentPageBlock}&keyword=${param.keyword }">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<c:if test="${!empty authUser}">
						<a
							href="${pageContext.servletContext.contextPath }/board?a=writeform"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>