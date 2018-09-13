<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- 슬라이드-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/slick/slick-theme.css"/>

<style>
img {
    width:130px;
    height:130px;
    border-radius: 75px; /* 이미지 반크기만큼 반경을 잡기*/
  margin-left:auto;
  margin-right:auto;
 
} 
#backdiv{
	  background-color:#E0E0E0;
	  width:100%;
}
 .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 30%; /* Could be more or less, depending on screen size */                          
        }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	//모달창 인스턴트 생성
	// 모달 창 여는 버튼에 이벤트 걸기
	$("#openModal").click(function() {
		$('#myModal').show(); // 모달창 보여주기
	});
	  
	// 모달 창 닫기
	$("#closeModal").click(function() {
	           $('#myModal').hide();
	      });
	//ajax로 입력내용 저장하기
	$('#add_goods').click(function(){
		var g_id=$('#g_id').val();
		var g_name=$('#g_name').val();
		var amount=$('#amount').val();
		if(name==null){
			alert('상품명을 정확하게 써주세요');
			return;
		}else if(amount==null){
			alert('신청 수량을 입력하세요');
			return;
		}else{
			$.ajax({
				type:'post',
				data:{id:g_id,name:g_name,amount:amount},
				url:'addInsert.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='success'){
					alert('요청이 완료되었습니다.');
					 $('#myModal').hide();
					}
				},error:function(){
					alert('추가 등록 에러입니다.');
					}
				});
		}		
	 });	
});	
</script>
<div class="container">
<br>
	<h2>목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul style="list-style:none; margin:0;padding:0;text-align:center">
			<li style="display:inline-block;">
				<select name="keyfield">
					<option value="as_name">보호소명</option>
					<option value="as_location">지역</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li style="display:inline-block;">
				<input type="text" name="keyword" id="keyword">
			</li>
			<li style="display:inline-block;">
				<input type="submit" value="검색">
			</li>
		</ul>
	</form>
	
  <!-- 모달 창을 여는 버튼 -->
<!-- 보호소만 볼 수 있게 걸러주세요 (나중에 꼭) -->
  <div style="float:right;">
   <button id="openModal">물품 추가 신청</button></div><br><br>
   <!-- The Modal -->
    <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content">
       <p style="text-align: center;"><span style="font-size: 14pt;"><b><span style="font-size: 24pt;">물품 추가 신청</span></b></span></p>
               <br>
                <div style="text-align: center;border: 1px solid gray;">
                <br>
                <form>
                <input type="hidden" id="g_id" value=" ${user_id}">
<b>보호소ID : ${user_id}</b><br>
상품이름 : <input type="text" placeholder="예)강아지 연고" id="g_name"><br>
판매주소 : <input type="url" placeholder="생략가능"><br>
희망수량 : <input type="number" id="amount"><br>
상세요청 : <textarea></textarea>
              
                </form>
                </div>
                <p style="text-align: center; line-height: 1.5;"><br/>
                    필요한 물품을 추가로 신청하세요. <br>
                   <a style="color:gray">(* 담당자의 확인을 거쳐 등록됩니다.)</a>
                </p>
            <div style="text-align: center; ">
             <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;"id="closeModal">
                <span class="pop_bt" style="font-size: 13pt;"><b>취소</b></span>
            </div>
            <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;" id="add_goods">
                <span class="pop_bt" style="font-size: 13pt;color:orange;"><b>신청</b></span>
            </div>
            </div>
      </div>
 
    </div>
        <!--End Modal-->


	<div>
	<c:if test="${count == 0}">
	<div class="align-center">후원할 보호소가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<table style="text-align:center; width:100%;border: 1px solid #444444;">
		<tr>
			<th>보호소ID</th>
			<th>보호소명</th>
			<th>위치</th>
			<th>배변패드</th>
			<th>개사료(전연령)</th>
			<th>고양이 사료</th>
			<th>샴푸</th>
			<th>고양이용 모래</th>
		</tr>
		<c:forEach var="as_list" items="${as_list}">
		<tr  style="text-align: center;">
			<td>${as_list.as_id}</td>
			<td>${as_list.as_name}</td>
			<td>${as_list.as_location}</td>
			<td>${as_list.pad}</td>
			<td>${as_list.dogfood}</td>
			<td>${as_list.cattoy}</td>
			<td>${as_list.shampoo}</td>
			<td>${as_list.catsand}</td>
		</tr>
		</c:forEach>
	</table>
	<br><br>

	<c:forEach var="as_list" items="${as_list}">
	
	<div id="backdiv">
		<span style="text-align:center;"><p><a style="font-size:40px;font-weight: bold;color:#3498DB">${as_list.as_name}</a><a style="font-size:15px;color:gray;">: ${as_list.as_location}</a></p></span>
	<!--슬라이드 가즈아!!! -->
	<div class="autoplay">
	    <c:forEach var="goodsList" items="${goodslist}">
	    <c:if test="${goodsList.g_id.equals('standard')||goodsList.g_id.equals(as_list.as_id)}">
		 <div style="text-align:center; display: inline-block; width:18%"><img src="${pageContext.request.contextPath}/upload/goods/${goodsList.g_photo1}" alt=""><br><a href="detail.do?g_num=${goodsList.g_num}&&as_name=${as_list.as_name}">${goodsList.g_name}<br></a>${goodsList.g_price} 원</div>
		</c:if></c:forEach>
		</div>		
  <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
  <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/slick/slick.min.js"></script>

  <script type="text/javascript">
    $(document).ready(function(){      
      $('.autoplay').slick({
    	  slidesToShow:4,
    	  slidesToScroll: 1,
    	  autoplay: true,
    	  autoplaySpeed: 2000,
    	});
    });
    
  </script>
	<!-- 슬라이드 끝났다  -->
	</div><br>
	</c:forEach>
	<div style="text-align: center;">${pagingHtml}</div>	
	</c:if>
	</div>
</div>