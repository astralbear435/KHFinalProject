<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<style>
ul{
   list-style:none;
   }   
</style>

<div class="container">

	<div class="w3l-heading">
	
		<p style="text-align:center;">봉사활동 모집 안내</p>
	</div>

	<p>
		유기견 보호 센터에서는 따뜻한 마음을 나눌 봉사자분들의 참여를 기다립니다. <br> 
		자원봉사 활동시간 : 오후 1시 	~ 오후 5시		<br> 
		봉사활동 : 중학생 이상부터 신청 가능합니다. 단체봉사를 원하시는 경우 동물보호센터(042-825-1118)로 사전 신청하시기 바랍니다. 
		(단, 단체봉사는 애완동물관련 전공자 또는 관련 기술자만 가능)<br>  
		봉사신청 : 봉사일 15일전(0시) ~ 2일전까지
		동물보호센터 홈페이지에서 신청 가능합니다. <br> 
		- 1일 봉사인원은 5명 기준이며, 주말 및 공휴일에는 신청자가 	많아 신청이 어려울 수 있으니 양해바랍니다. 
		<br> 확인서 발급 : 1365자원봉사포털(www.1365.go.kr)사이트를 통해 봉사시간 확인 및 출력가능 <br> 
		1365자원봉사포털  회원가입 : 회원가입 한 분들에 한해 봉사시간 입력이 가능하며, 봉사활동 중 사고발생 시 상해보상이 가능하므로 반드시 사전가입하셔야 합니다. <br>
	</p>
 
<c:if test="${user_auth == 4}">
<div style="text-align: right;">
	<input type="button" value="봉사활동 모집 글 쓰기"
		class="btn btn-primary btn-lg"
		onclick="location.href='${pageContext.request.contextPath}/recruit/recruitWrite.do'">
</div>
</c:if>

	<form action="recruitList.do" id="search_form" method="get">
		<div class="search">
			<span><select name="keyfield">
					<option value="r_title">제목</option>
					<option value="">보호소명</option>
					<option value="r_content">내용</option>
			</select></span>
			<span><input type="text" name="keyword" id="keyword"></span>
			<span><input type="submit" class="btn btn-primary btn-lg" value="검색"></span>
		</div>
	</form>

	<hr class="my-4">
	
	<script type="text/javascript">
function showPopup(r_num){
	window.open("${pageContext.request.contextPath}/volunteer/volunteerWrite2.do?r_num="+r_num,"봉사활동 신청","width=400, height=800, left=100, top=50");
}


</script>



	<div class="row ">
		<c:forEach var="recruit" items="${list}">
			<div class="card border-primary col-sm-3.5 agile-choose-grid"
				style="max-width: 45rem; margin:10px;">
				<div class="card-header choose-info">${recruit.r_id}</div>
				<div class="card-body choose-info">
				<c:if test="${recruit.r_filename == null}">
				<img src="${pageContext.request.contextPath}\resources\images\유기견보호소.png" style="width: 300px; height: 250px;"/>
				</c:if>
				
				<c:if test="${recruit.r_filename != null}">
					<c:if test="${fn:endsWith(recruit.r_filename,'.jpg') ||
					fn:endsWith(recruit.r_filename,'.JPG') ||
					fn:endsWith(recruit.r_filename,'.gif') ||
					fn:endsWith(recruit.r_filename,'.GIF') ||
					fn:endsWith(recruit.r_filename,'.png') ||
					fn:endsWith(recruit.r_filename,'.PNG')}">
						<div class="align-center">
							<img src="imageView.do?r_num=${recruit.r_num}"
								style="width: 300px; height: 250px;">								
						</div>						
					</c:if>
					</c:if>
					<h4 class="card-title">
						<p>${recruit.r_title}</p>
					</h4>
					<c:if test="${recruit.r_status==2}">
						<p class="card-text">${recruit.r_start_date}~${recruit.r_end_date}</p>
					</c:if>
					<c:if test="${recruit.r_status==1}">
						<p class="card-text">상시 모집 중</p>
					</c:if>


				</div>
				<div class="col-md-12">

	
					<c:if test="${!empty user_id}">
												
						<div style="margin: 0 auto; width: 100%;">
							<input type="button" value="봉사활동 신청하기" class="btn btn-warning" onclick="showPopup(${recruit.r_num});">
						
					</div>
					</c:if>
					<c:if test="${empty user_id}">
				
					<div style="margin: 0 auto; width: 100%;">
						<input type="button" value="봉사활동 신청하기" class="btn btn-warning" style="margin: 0 auto;" onclick="alert('로그인이 필요한 서비스입니다.');">
					</div>
					
					</c:if>
					
					<c:if test="${!empty user_id && user_id == recruit.r_id}">		
						<input type="button" value="수정" class="btn btn-success"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitUpdate.do?r_num=${recruit.r_num}'">
				
						<input type="button" value="삭제" class="btn btn-danger"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitDelete.do?r_num=${recruit.r_num}'">
					</c:if>
					
				</div>
			</div>
		</c:forEach>
	</div>

</div>