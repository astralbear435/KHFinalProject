<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	li{margin-bottom: 20px;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/note.js"></script>
<div class="horizontal"></div><!-- 가로 여백 -->
<ul class="menu" style="list-style-type: none; text-align: right;">
	<li>
		<input type="button" class="btn btn-warning" value=" 쪽지 작성 "
				onclick="window.open('${pageContext.request.contextPath}/note/write.do','쪽지 작성','width=450, height=550, scrollbars=no resizable=no');"/>
	</li>
	<li><a href="${pageContext.request.contextPath}/note/receivedList.do">받은 쪽지함</a></li>
	<li><a href="${pageContext.request.contextPath}/note/sendList.do">보낸 쪽지함</a></li>
</ul>