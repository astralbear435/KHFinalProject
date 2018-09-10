<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<style type="text/css">
	.floatL{float:left;}
	
	.condition{margin: 5px 2px 5px 8px;}
	
	.search{
   		background-color: #bcbcbc;
   		margin:5px 0px 5px 10px;
   		width:1120px; height: 50px;
   		line-height:40px;
	}
	
	.searchButton{
		width:70px; height:70px;
		padding-top: 5px;
	}
</style>
<body>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="width:100%; height:1000px; float:none;">
		<div style="width: 1300px; height:125px; clear: both; margin: 0 auto;">
			<div class="condition floatL">
				<div class="search">
					<label class="condition floatL" for="local"> 지역으로 찾기 </label>
					<div class="floatL" style="width: 30px; height: 24px;"></div>
					<div class="condition floatL">
						<div class="floatL"><input type="checkbox" value="서울">서울&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="경기">경기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="인천">인천&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="강원">강원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="충북">충북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="세종">세종&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="충남">충남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="대전">대전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="경북">경북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="대구">대구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="부산">부산&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="경남">경남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="전북">전북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="전남">전남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="광주">광주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="floatL"><input type="checkbox" value="제주">제주</div>
					</div>
				</div>
				<div class="search" style="line-height:40px;">
					<label class="condition floatL" for="name"> 이름으로 찾기</label>
					<div class="floatL" style="width: 30px; height: 24px;"></div>
					<div class="floatL" style="padding-top: 9px;">
						<input type="text" name="name" class="floatL form-control form-control-sm" style="width: 950px; ">
					</div>
				</div>
			</div>
			<div class="condition floatL searchButton">
				<p><input type="button" class="btn btn-primary btn-lg" style="height: 100px;"
							value="검색하기" onclick="location:href='#'"></p>
			</div>
		</div>
		
		<!-- gallery -->
	<div class="gallery">
		<div class="container"> 
			<div class="gallery-grids-row">
				<c:forEach var="shelter" items="${list}">
					<div class="col-md-4 gallery-grid">
						<div class="wpf-demo-4">  
							<a href="${pageContext.request.contextPath}/shelter/shelterDetail.do?id=${shelter.s_id}" class="jzBoxLink item-hover" title="Njoy Trip">  
								<img src="/tem/resources/images/시험용 썸네일.png" alt=" " class="img-responsive" />
								<div class="view-caption">
									<p>${shelter.s_name}</p>
								</div> 
							</a>    		
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
</body>
</html>