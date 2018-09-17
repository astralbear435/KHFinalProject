<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 슬라이드-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/slick/slick-theme.css"/>

<style>
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
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/soeun/goodsPrice.js"></script> <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
 <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/slick/slick.min.js"></script>

<script type="text/javascript">
function gologin_click(){
	realtrue=confirm('결제는 로그인 후 이용해 주십시오. 로그인 창으로 이동하시겠습니까?');
	if(realtrue){
	//가능하지만 >>replace한 페이지에서 백버튼을 눌렀을때 -2페이지 해버림:location.replace("${pageContext.request.contextPath}/member/login.do");
		location.href="${pageContext.request.contextPath}/member/login.do";
	}else{
		alert('찾아주셔서 감사합니다. ^3^');
	}
}
$(document).ready(function(){
	var IMP = window.IMP; // 생략해도 괜찮습니다.
	IMP.init("imp62212373"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.
//바로결제
	$('#paynow').click(function(event){
		if($('#order_quantity').val()==''){
			alert('수량을 입력하세요! (최소 수량 1개)');
			$('#order_quantity').focus();
			return false;
		}
		else{
			$('#myModal').show(); // 모달창 보여주기
		}
	});
	$("#closeModal").click(function() {
        $('#myModal').hide();
   });
	$("#closeModal2").click(function() {
        $('#myModal2').hide();
   });
//다음창
$('#nextInfo').click(function(event){
	 $('#myModal').hide();
	 $('#myModal2').show();	
});
	
//결제창
$('#buyMeNow').click(function(event){
	var ptotal=Number($('#sumPrice').val())*Number($('#order_quantity').val());
	var goodsName=$('#p_goodsname').val();//상품명
	var goodsNum=$('#p_num').val();//상품번호
	var dona_id=$('#p_id').val();//구매자 아이디
	var amount=$('#order_quantity').val();//갯수
	var dona_asname=$('#p_name').val();//보호소명
	var dona_name=$('#dona_name').val();//기부자	
	var dona_message=$('#dona_message').val();//기부내용
	var as_id=$('#as_id').val();//보호소 아이디
	requestPay(ptotal,goodsName,goodsNum,dona_id,amount,dona_name,dona_asname,dona_message,as_id);
});

function requestPay(ptotal,goodsName,goodsNum,dona_id,amount,dona_name,dona_asname,dona_message,as_id) {
    // IMP.request_pay(param, callback) 호출
    IMP.request_pay({ // param
        pg: "inicis",
        pay_method: "card",
        merchant_uid: "ORD20180131-0001116",
        name: goodsName,
        amount:ptotal,
        buyer_email: "gildong@gmail.com",
        buyer_name:dona_name,
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    }, function (rsp) { // callback
        if(rsp.success) {
        	$.ajax({
				type:'post',
				data:{dona_id:dona_id,dona_name:dona_name,dona_asname:dona_asname,goodsNum:goodsNum,ptotal:ptotal,amount:amount,dona_message:dona_message,as_id:as_id},
				url:'order.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='success'){
					 alert('결제를 성공했습니다. 마이페이지에서 확인해주세요.');
				     $('#myModal2').hide();
				     location.reload();
					}
				},
				error:function(){
		      	alert('(주문DB)에러입니다.');
		      	 $('#myModal2').hide();
	          	}
		});       
        }else{
        	 alert('결제를 취소했습니다.');
        	  $('#myModal2').hide();
        }
    });
}
//슬라이드
$('.slider-for').slick({
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  arrows: false,
	  fade: true,
	  asNavFor: '.slider-nav'
	});
	$('.slider-nav').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  asNavFor: '.slider-for',
	  dots: false,
	  centerMode: true,
	  focusOnSelect: true
	});  
});
</script>
<div>
<!-- 결제 확인 모달 -->
    <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content">
                <p style="text-align: center;"><b><span style="font-size: 20pt;"> 해당 물품 기부를 바로 진행하시겠습니까?</span></b></p>
                <p style="text-align: center; line-height: 1.5;"><br/>
                 아래 정보를 확인해 주세요.
                </p>
                <!-- 정보 확인 창  -->
                <div style="text-align: center;border: 1px solid gray;">
                <ul style="list-style:none; font-size:20px;">
                	<li>기부지 : ${as_detail.as_name}</li>
                	<li>물품명: ${goods.g_name}</li>
                	<li id="modalAmount"><span> </span></li>
                	<li id="modalPrice"><span> </span></li>
                	<li>아이디 : ${user_id}</li>
                </ul>
                </div>
                <br><br>
           <div style="text-align: center; ">
             <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;"id="closeModal">
                <span class="pop_bt" style="font-size: 13pt;"><b>취소</b></span>
            </div>
            <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;" id="nextInfo">
                <span class="pop_bt" style="font-size: 13pt;color:orange;"><b>다음</b></span>
            </div>
            </div>
      </div>
 
    </div>
<!--End Modal-->

<!-- 정보전달 모달2 -->
    <div id="myModal2" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content">
                <p style="text-align: center;"><b><span style="font-size: 20pt;"> 해당 물품 기부를 바로 진행하시겠습니까?</span></b></p>
                <p style="text-align: center; line-height: 1.5;"><br/>
                기부자 성명과 보호소에 보낼 메세지를 작성해 주세요</p>
                <!-- 정보 확인 창  -->
                <div style="text-align: center;border: 1px solid gray;">
                <ul style="list-style:none; font-size:20px;">
                	<li>기부자 :<input type="text"  class="form-control" id="dona_name"></li>
                	<li><a style="color:gray">(해당 이름으로 기부됩니다.)</a></li>
                	<li>메세지:<textarea rows="10"  class="form-control" cols="30" id="dona_message" placeholder="공란도 괜찮습니다."></textarea></li>
                </ul>
                </div>
                <br><br>
           <div style="text-align: center; ">
             <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;"id="closeModal2">
                <span class="pop_bt" style="font-size: 13pt;"><b>취소</b></span>
            </div>
            <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#DDDDDD;    width: 40%;
    display: inline-block;" id="buyMeNow">
                <span class="pop_bt" style="font-size: 13pt;color:orange;"><b>결제</b></span>
            </div>
          </div>
      </div>
 
    </div>
<!--End Modal-->



<!-- 내용 시작 -->    
<div class="container"><br><br>
<img src="${pageContext.request.contextPath}/upload/goods/mong.jpg" alt="" style="width:100%">


<div style="width:90%;">
<br>
	<h4><b></b><a style="color:blue" href="${pageContext.request.contextPath}/shelter/shelterDetail.do?id=${as_detail.as_id}">${as_detail.as_name}</a><a href="${pageContext.request.contextPath}/goods/list.do">/후원하기</a></b></h4>
<br>
<!-- 이미지 슬라이드 가자!! -->
<div style="margin-bottom: 10%;">
<div style="display:inline-block; width:40%;">
		<div class="slider-for" style="border:1px solid gray;">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo1}" alt="">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo2}" alt="">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo3}" alt="">
		</div>&nbsp;&nbsp;&nbsp;
		<div class="slider-nav">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo1}" alt="">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo2}" alt="">
		<img src="${pageContext.request.contextPath}/upload/goods/${goods.g_photo3}" alt="">
		</div></div>
	<div style="display:inline-block;margin-left: 5%;">
		<h1>${goods.g_name}</h1><hr>
		<p>원산지 : ${goods.g_origin}</p>
		<p style="width:500px">상품 설명 : ${goods.g_content}</p>
		<hr>						
		<c:if test="${goods.g_num==1}"><p>현재 해당 보호소에서 <b style="color:orange">${as_detail.pad}</b>  개 만큼 필요로 하고 있습니다.</p></c:if>	
		<c:if test="${goods.g_num==2}"><p>현재 해당 보호소에서 <b style="color:orange">${as_detail.dogfood}</b>  개 만큼 필요로 하고 있습니다.</p></c:if>				
		<c:if test="${goods.g_num==3}"><p>현재 해당 보호소에서 <b style="color:orange">${as_detail.catfood}</b>  개 만큼 필요로 하고 있습니다.</p></c:if>				
		<c:if test="${goods.g_num==4}"><p>현재 해당 보호소에서 <b style="color:orange">${as_detail.shampoo}</b>  개 만큼 필요로 하고 있습니다.</p></c:if>				
		<c:if test="${goods.g_num==5}"><p>현재 해당 보호소에서 <b style="color:orange">${as_detail.catsand}</b>  개 만큼 필요로 하고 있습니다.</p></c:if>							
		<form id="cart_order">
		<p style="display: inline-block;">
		수량 :<input type="number" class="form-control" name="p_amount" id="order_quantity"/><b><div id="total" style="display: inline-block; float:right; color:#B60000;font-size: 25px;"><span id="item_total_txt">합계 : 0원</span>
		 </div></b></p>
		<br>
		<!-- 장바구니 버튼 -->
		   <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;text-align:center;width:44%;border:1px solid #4285F4;display: inline-block;"id="gocart">
                <span class="pop_bt" style="font-size: 13pt;color:#4285F4;"><b>장바구니</b></span>
            </div>
       
		<!-- <button type="button" id="gocart">장바구니</button>	 -->
         
         <input type="hidden" name="p_price" id ="sumPrice" value="${goods.g_price}"/>
	     <input type="hidden" id="p_name" name="p_name" value="${as_detail.as_name}"/>
		 <input type="hidden" id="p_id" name="p_id" value="${user_id}"/>
		 <input type="hidden" id="p_num" name="p_num" value="${goods.g_num}"/> 
		 <input type="hidden" id="p_goodsname" name="p_goodsname" value="${goods.g_name}">
		 <input type="hidden" id="p_goodsphoto" name="p_goodsphoto" value="${goods.g_photo1}">
		 <input type="hidden" id="as_id" name="as_id" value="${as_detail.as_id}">
		
		<c:if test="${user_id=='not'}">
			<!-- <button type="button" id="gologin" onclick="gologin_click();">바로결제</button> -->
			     <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#4285F4;text-align:center;width:44%; display: inline-block;color:write;"  onclick="gologin_click();">
                <span class="pop_bt" style="font-size: 13pt;color:white;"><b>바로결제</b></span>
            </div>
			<div style="width:9%;height:50%;text-align:center;display:inline-block;border:1px solid #4285F4;padding:2%;cursor:pointer;" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			<span class="glyphicon glyphicon-home" aria-hidden="true"></span></div>
		</c:if>
		<c:if test="${user_id!='not'}">
			<!-- <button type="button" id="paynow">바로결제</button>	 -->
			   <div style="padding-bottom: 10px;padding-top: 10px;cursor:pointer;background-color:#4285F4; text-align:center;width:44%; display: inline-block; color:write;" id="paynow">
                <span class="pop_bt" style="font-size: 13pt;color:white;"><b>바로결제</b></span>
            </div>
           <div style="width:9%;height:50%;text-align:center;display:inline-block;border:1px solid #4285F4;padding:2%;cursor:pointer;" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></div>
		</c:if>
		</form>		

	</div>
</div>
</div>
<br><br><br><br>
</div>

