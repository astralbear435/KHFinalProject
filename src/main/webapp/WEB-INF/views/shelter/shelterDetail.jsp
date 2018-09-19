<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<body>
	<input type="hidden" id="s_address1" value="${shelter.s_address1}">
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div class="bigSquare">
		<c:if test="${user_id == shelter.s_id}">
		<p align="right" style="margin:0;">
			<input type="button" value="수정하기" class="btn btn-warning"
					onclick="location.href='${pageContext.request.contextPath}/shelter/shelterConfirm.do'">
		</p>
		</c:if>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div class="mainPhoto">		
			<div class="titleDetail">${shelter.s_name}</div>
		</div><br>
		
		<!-- 명언 -->
		<%
			int result = (int) (15 * Math.random());
			
			String[] array = {"개는 자기 자신보다 당신을 더 사랑하는 이 세상의 유일한 생명체일 것이다. - 조쉬 빌링스",
							  "강아지가 우리의 얼굴을 핥아 주는 것보다 훌륭한 정신 치료사는 없다. - 번 윌리암스",
							  "행복은 포근한 강아지다. - 찰스 슐츠",
							  "만약 천국에 개가 없다면 나는 천국에 가고 싶지 않다. 그들이 있는 곳으로 가고 싶다. - 윌 로져스",
							  "인생의 시름을 달래주는 두 가지가 있다면 그것은 음악과 고양이이다. - 알버트 슈바이처",
							  "고양이가 있는 집에는 특별한 장식물이 필요 없다. - 웨슬리 베이츠",
							  "고양이는 편안함을 주는 것에 전문가다. - 제임스 헤리엇",
							  "사람 사이의 신뢰는 깨어지기 쉽다. 그러나 충직한 개는 결코 우리를 배신하지 않는다. - 콘라드 로렌츠",
							  "고양이는 신이 빚어낸 최고의 걸작품이다. - 레오나르도 다빈치",
							  "사람에게는 동물을 다스릴 권한이 있는 것이 아니라 모든 생명체를 지킬 의무가 있는 것이다. - 제인 구달",
							  "고양이 한 마리를 기르면 또 한 마리를 기르게 된다. - 어니스트 헤밍웨이",
							  "한 나라의 위대함과 도덕성은 동물을 대하는 태도로 판단할 수 있다. - 마하트마 간디",
							  "동물은 정말 유쾌한 친구다. 질문도 비판도 하지 않으므로. - 조지 앨리엇",
							  "인생에 고양이를 더하면 그 힘은 무한대가 된다. - 라이너 마리아 릴케",
							  "세상에 평범한 고양이는 단 한 마리도 없다. - 콜테트"};
		%>
		<div style="text-align:center;">
			<%= array[result] %>
		</div>
		<!-- 명언 끝 -->
		
		<hr size="1" width="100%"><br>
		
		<div style="margin:0 auto; float:none;">
		
			<!-- 소개 -->
			<div style="float: left;width:45%;height:350px;">
				${shelter.s_content}
			</div>
			<!-- 소개 -->
			
			<div style="float: left;width:5%;height:350px;"></div><!-- div 사이 빈칸 -->
			
			<!-- 지도시작 -->
			<div id="map" style="float: left;width:50%;height:350px;"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2614c9140bb26e90bc3a420f88712052&libraries=services"></script>
			<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  
				
				// 지도를 생성합니다    
				var map = new daum.maps.Map(mapContainer, mapOption); 
				
				// 마우스 드래그와 모바일 터치를 이용한 지도 이동을 막는다
				//map.setDraggable(false);		

				// 마우스 휠과 모바일 터치를 이용한 지도 확대, 축소를 막는다
				//map.setZoomable(false);   
				
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new daum.maps.services.Geocoder();
				
				// 회원의 주소값
				var address = document.getElementById('s_address1').value;
				
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(address, function(result, status) {
					
				    // 정상적으로 검색이 완료됐으면 
				     if (status === daum.maps.services.Status.OK) {
				
				        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
				
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new daum.maps.Marker({
				            map: map,
				            position: coords
				        });
				        
				     	// 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new daum.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">${shelter.s_name}</div>'
				        });
				        infowindow.open(map, marker);
			
				
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});  
			</script>
			<!-- 지도 끝 -->
			
			<div class="horizontal"></div><!-- 가로 여백 -->
		</div>
		
		
		<hr size="1" width="100%"><br><br>
		
		<!-- 링크2 시작 -->
		<c:if test="${recruitCount == 0}">
		<div>
			<div style="width: 1000px; margin: 0 auto;">
				<div style="text-align: center;">
					<div  class="link2blank" style="width:160px;"></div>
					<div style="float:left">
						<img src="${pageContext.request.contextPath}\resources\images\sy\빨간강아지.png" class="square">
						<div class="linkSquare">
							<div style="font-size:30px;">동물 보호 현황</div>
							<div style="font-size:17px;">
								200여 마리의 동물이<br>
								여러분의 도움을<br>
								필요로 하고 있습니다.<br>
							</div><br>
							<input type="button" value="보러가기 >" class="btn btn-danger"
									onclick="location.href='${pageContext.request.contextPath}/dog_board/list.do?keyfield=id&keyword=${shelter.s_id}'">

						</div>
					</div>
					<div  class="link2blank" style="width: 145px;"></div>
					<div style="float:left">
						<img src="${pageContext.request.contextPath}\resources\images\sy\파란고양이.png" class="square">
						<div class="linkSquare">
							<div style="font-size:30px;">물품 후원하기</div>
							<div style="font-size:17px;">
								보호소에<br>
								필요한 물품을 <br>
								후원하실 수 있습니다.<br>
							</div><br>
							<input type="button" value="후원하기 >" class="btn btn-info"
										onclick="location.href='${pageContext.request.contextPath}/goods/list.do?keyfield=id&keyword=${shelter.s_id}'">
						</div>
					</div>
					<div class="horizontal"></div><!-- 가로 여백 -->
				</div>
			</div>
		</div>
		</c:if>
		<!-- 링크2 끝 -->
		
		<!-- 링크3 시작 -->
		<c:if test="${recruitCount > 0}">
		<div style="width: 1000px; margin: 0 auto;">
			<div style="float:left">
				<img src="${pageContext.request.contextPath}\resources\images\sy\빨간강아지.png" class="square">
				<div class="linkSquare">
					<div style="font-size:30px;">동물 보호 현황</div>
					<div style="font-size:17px;">
						200여 마리의 동물이<br>
						여러분의 도움을<br>
						필요로 하고 있습니다.<br>
					</div><br>
					<input type="button" value="보러가기 >" class="btn btn-danger"
							onclick="location.href='${pageContext.request.contextPath}/dog_board/list.do?keyfield=id&keyword=${shelter.s_id}'">
				</div>
			</div>
			<div class="link3blank"></div>
			<div style="float:left">
				<img src="${pageContext.request.contextPath}\resources\images\sy\파란고양이.png" class="square">
				<div class="linkSquare">
					<div style="font-size:30px;">물품 후원하기</div>
					<div style="font-size:17px;">
						보호소에<br>
						필요한 물품을 <br>
						후원하실 수 있습니다.<br>
					</div><br>
					<input type="button" value="후원하기 >" class="btn btn-info"
							onclick="location.href='${pageContext.request.contextPath}/goods/list.do?keyfield=id&keyword=${shelter.s_id}'">
				</div>
			</div>
			<div class="link3blank"></div>
			<div style="float:left">
				<img src="${pageContext.request.contextPath}\resources\images\sy\노란개.png" class="square">
				<div class="linkSquare">
					<div style="font-size:30px;">자원봉사 신청</div>
					<div style="font-size:17px;">
						보호소에 직접 방문하여<br>
						도움을 주실 분들을<br>
						모집합니다.<br>
					</div><br>
					<input type="button" value="참여하기 >" class="btn btn-warning"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitList.do?keyfield=id&keyword=${shelter.s_id}'">
				</div>
			</div>
		</div>
		</c:if>
		<!-- 링크3 끝 -->
	</div>
</body>
</html>
