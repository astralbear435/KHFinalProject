<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/css/layout.css"></script>
<style>
ul{
   list-style:none;
   text-align:center;
   align:center;
   }
form{
   width:50%;
   height:10%;
   align:center;
}
img.abc{
	max-width: 100%;
	max-height: 100%;
	width: 250px;
	height: 300px;
}

</style>
<div class="container">
	<div align=center>
	<br>
	<br>
	<h2><b>보호동물(입양페이지)</b></h2><br>
	<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
	</div>
	<hr>
	<br>
	<form style="margin-left:25%;" action="list.do" id="search_form" method="get">
		<ul style="list-style:none; margin:0;padding:0;text-align:center">
			<li style="display:inline-block;">
				<select name="keyfield">
					<option value="id">보호소명</option>
					<option value="an_name">동물이름</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li style="display:inline-block;">
				<input type="text" name="keyword" id="keyword">
			</li>
			<li style="display:inline-block;">
				<input type="submit" value="search" class="btn btn-outline-info">
			</li>
			<li style="display:inline-block;">
				<input type="button" value="reset" class="btn btn-outline-info" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<br><br>
	<c:if test="${count == 0}"><!--  게시물이 없을때 -->
	<div align=center>등록된 게시물이 없습니다.</div>
	<c:if test="${!empty user_id}">
		<div align="right">
		<input type="button" value="글쓰기"  class="btn btn-info"
		       onclick="location.href='write.do'">
		 </div>
    </c:if>
	</c:if>
	<c:if test="${count > 0}"><!-- 게시물이 존재할때 -->
	<c:forEach var="board" items="${list}">	
		<div style="display:inline-block;"> 
			<a href="detail.do?num=${board.num}"><img class="abc" style="display:inline-block;" src="imageView.do?num=${board.num}" onerror="this.src='../resources/images/not_image.png'" alt=''></a>
			<br><font style="font-size:1em;" color="#17BEFF">${board.an_sheltername}</font>&nbsp;&nbsp;${board.an_species}&nbsp;${board.an_name}<br><br>
			<img id="message" src="../resources/images/message.png" style="max-width:20px">&nbsp;${board.re_cnt}<br><br>
			${board.reg_date} &nbsp;<font style="font-size:0.75em;">조회 ${board.an_hit}</font><br><br><br>
		</div>
	</c:forEach>
	<div align="right">
		<c:if test="${!empty user_id && user_auth == 4}">
		<input type="button" value="글쓰기"  class="btn btn-info"
		       onclick="location.href='write.do'">
		</c:if>
	<div style="margin-left:45%;">${pagingHtml}</div>
	</div>

	</c:if>
	
</div>