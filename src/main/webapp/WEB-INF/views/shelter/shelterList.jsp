<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<body>
	<div class="container">
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div style="width:100%; height:1000px; float:none;">
			<div class="card border-primary col-md-12" style="clear: both; margin: 0 auto;">
				<form action="shelterList.do" id="shelterSearchForm" method="post">
					<div class="condition floatL">
						<div class="search margin0a">
							<label class="condition floatL" for="local"> 지역으로 찾기 |</label>
							<div class="floatL" style="width: 10px; height: 24px;"></div>
							<div class="floatL" style="padding-top: 5px;">
								<div class="floatL">&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="전체" checked>전체&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="서울">서울&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="경기">경기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="인천">인천&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="강원">강원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="충청북">충북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="세종">세종&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="충청남">충남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="대전">대전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="경상북">경북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="대구">대구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="부산">부산&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="경상남">경남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="전라북">전북&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="전라남">전남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="광주">광주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="floatL"><input type="radio" class="form-check-input" name="local" value="제주">제주</div>
							</div>
						</div>
						<div class="clearB" style="height: 50px;">
							<div class="search floatL margin0a" style="line-height:40px; width: 85%;">
								<label class="condition floatL" for="name"> 이름으로 찾기 |</label>
								<div class="floatL" style="width: 10px; height: 24px;"></div>
								<div class="floatL" style="padding-top: 7px;width: 85%;">
									<input type="text" name="name" class="floatL form-control form-control-sm">
								</div>
							</div>
							<div class="floatL" style="width: 10px; height: 24px;"></div>
							<div class="floatL searchButton">
								<p><input type="submit" class="btn btn-warning" style="width: 150px; height: 50px;" value="검색"></p>
							</div>
						</div>
					</div>
				</form>
			</div>
			<c:if test="${count == 0}">
				<div class="horizontal"></div><!-- 가로 여백 -->
				<div style="width: 90%; border: 2px solid black; text-align: center; margin: 0 auto;">
					조건을 만족하는 보호소를 찾을 수 없습니다. 다시 검색해주세요.
				</div>
			</c:if>
			
			<c:if test="${count > 0}">
			<!-- gallery -->
			<div class="gallery">
				<div class="container"> 
					<div class="gallery-grids-row">
						<c:forEach var="shelter" items="${list}">
							<div class="col-md-4 gallery-grid">
								<div class="wpf-demo-4">  
									<a href="${pageContext.request.contextPath}/shelter/shelterDetail.do?id=${shelter.s_id}" class="jzBoxLink item-hover" title="Njoy Trip">  
										<c:if test="${shelter.s_filename == null}">
											<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 기본 썸네일.png" alt=" " class="img-responsive" />
										</c:if>
										<c:if test="${shelter.s_filename != null}">
											<img src="${pageContext.request.contextPath}/shelter/imageView.do?id=${shelter.s_id}" alt=" " class="img-responsive" />
										</c:if>
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
			</c:if>
		</div>
		<div class="horizontal"></div><!-- 가로 여백 -->
	</div>
</body>
</html>