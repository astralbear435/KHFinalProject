<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/soeun/cartAndOrder.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
td{
border:1px solid lightgray;
    padding: 10px;
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
<script>
/* 체크박스 전체선택, 전체해제 */
function checkAll(){
      if( $("#th_checkAll").is(':checked') ){
        $("input[name=select_me]").prop("checked", true);
      }else{
        $("input[name=select_me]").prop("checked", false);
      }
}
$(document).ready(function(){
	var order=0;
	var IMP = window.IMP; // 생략해도 괜찮습니다.
	IMP.init("imp62212373");
	var p_num="";//상품번호
	var p_name="";//기부지
	var p_amount="";//수량
	var cartnum=""; //장바구니 고유번호
	
	$('.test').click(function(){
		  $( "input[name='select_me']:checked" ).each(function(){
			  cartnum+=""+$(this).val()+",";
			  p_num+=""+$('#goodsnum').val()+",";
			  p_name+=""+$('#p_name').val()+",";
			  p_amount+=""+$('#amount').val()+",";			  
		  });
		  cartnum=cartnum.substring(0,cartnum.lastIndexOf(","));
		  p_num=p_num.substring(0,p_num.lastIndexOf(","));
		  p_name=p_name.substring(0,p_name.lastIndexOf(","));
		  p_amount=p_amount.substring(0,p_amount.lastIndexOf(","));
			   $.ajax({
				   type:'post',
			   	   data:{num:cartnum,p_num:p_num,p_name:p_name,p_amount:p_amount},
			   	   url:'orderprice.do',
			   	dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					requestPay(data.pay,data.email,data.user_name,data.tel,data.addr,data.postcode,data.user_id,data.p_name,data.p_num,data.p_amount);					
				},error:function(){
			      	alert('결제 상품을 선택해주세요.');
	           	}	
			   });		  
	});
	function requestPay(pay,email,user_name,tel,addr,postcode,user_id,p_name,p_num,p_amount) {
	    // IMP.request_pay(param, callback) 호출
	    IMP.request_pay({ // param
	        pg: "inicis",
	        pay_method: "card",
	        merchant_uid: "ORD20180131-0007892",
	        name: "<주문서>",
	        amount:pay,
	        buyer_email: email,
	        buyer_name: user_name,
	        buyer_tel: tel,
	        buyer_addr: addr,
	        buyer_postcode: postcode
	    }, function (rsp) { // callback
	        if (rsp.success) {
	        	//결제성공시 DB에 저장
	         	$.ajax({
					type:'post',
					data:{dona_id:user_id,dona_asname:p_name,goodsNum:p_num,dona_price:pay,amount:p_amount},
					url:'multiOrder.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						if(data.result=='success'){
							order=data.order_num;
						$('#myModal2').show();
					   }
					},
					error:function(){
			      	alert('(주문DB)에러입니다.');
		          	}
			}); 
	        	
	        }else {
	        	 alert('결제가 취소되었습니다.');     	
	        	 //location.reload();
	        }
	    });
	}
	//기부자와 보낼메세지 업로드
	$('#buyMeNow').click(function(event){
		var dona_name=$('#dona_name').val();//기부자	
		var dona_message=$('#dona_message').val();//기부내용
		
		$.ajax({
			type:'post',
			data:{dona_username:dona_name,dona_message:dona_message,order_num:order},
			url:'orderUpdate.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='success'){				
				 alert('기부가 완료되었습니다. 참여해 주셔서 감사합니다.');
				 deleteCart(); //카트삭제
			   }
			},
			error:function(){
	      	alert('(멀티 업데이트)에러입니다.');
	      	location.reload();
          	}
	  }); 
	});
	$("#closeModal2").click(function() {
        $('#myModal2').hide();
   });
	//선택삭제 삭제눌렀을때
	$('#delete_cart').click(function(){
	 var selectNum="";
		  $( "input[name='select_me']:checked" ).each(function (){
		     selectNum+=""+$(this).val()+","; //여러개선택으로 배열만들기
		  });
		  selectNum=selectNum.substring(0,selectNum.lastIndexOf(","));
		  var id=$('#id').val();
					$.ajax({
						type:'post',
						data:{selectNum:selectNum,id:id},
						url:'deleteCart.do',
						dataType:'json',
						cache:false,
						timeout:30000,
						success:function(data){
							if(data.result=='logout'){
								alert('로그인 후 이용해 주세요.');
							}else if(data.result=='success'){
								alert('장바구니 삭제가 완료 되었습니다..');
								location.reload();
							}else{
								alert('세션이 만료되었습니다. 다시 시도해주세요.');
							}							
						},error:function(){
							location.reload();
						}
				     });	  
		  });
	
});
//결제 후 장바구니 삭제	
function deleteCart(){
	 var selectNum="";
	  $( "input[name='select_me']:checked" ).each(function (){
	      var selectNum=""+$(this).val()+","; //여러개선택으로 배열만들기
	  });
	      var id=$('#id').val();
				$.ajax({
					type:'post',
					data:{selectNum:selectNum,id:id},
					url:'deleteCart.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						location.reload();				
					},error:function(){
						location.reload();
		           	}					
			     });
	  		
	  location.reload();
   }
//분할 카운팅 테스트
function nanoCount(){
	$.ajax({
		type:'post',
		url:'CountMinus.do',
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){
			if(data.result=='success'){				
			 alert('컨트롤가서 확인해보세염');				
		   }else if(data.result=='no'){
			 alert('결제내역없다는듯');
			}else{
				 alert('이해불가');
			}
		},error:function(){
      	 alert('걍 실패 너');
      	}
  }); 
}	
</script>

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
                	<li>기부자 :<input type="text" id="dona_name"></li>
                	<li><a style="color:gray">(해당 이름으로 기부됩니다.)</a></li>
                	<li>메세지:<textarea rows="10" cols="30" id="dona_message" placeholder="공란도 괜찮습니다."></textarea></li>
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


<div class="container">
<img src="${pageContext.request.contextPath}/upload/goods/dori.jpg" alt="" style="width:100%">
	<br>
<c:if test="${cartList==null}">
	<div class="align-center">장바구니에 담긴 물품이 없습니다.</div>
	</c:if>
<c:if test="${cartList != null}">
	<table  style="text-align:center; width:100%;border: 1px solid #444444;">
		<tr>
		<!-- 전체 선택용 체크박스  -->
        <th><input type="checkbox" name="checkAll" id="th_checkAll" onclick="checkAll();" checked/></th>
			<th>photo</th>
			<th>product</th>
			<th>amount</th>
			<th>donation</th>
			<th>price</th>	
		</tr> 
		<c:forEach var="cartList" items="${cartList}">
			<form>
			<input type="hidden" id="id" value="${cartList.p_id}">
			<input type="hidden" id="amount" value="${cartList.p_amount}">
			<input type="hidden" id="price" value="${cartList.p_price}">
			<input type="hidden" id="goodsnum" value="${cartList.p_num}">
			<input type="hidden" id="p_name" value="${cartList.p_name}">
		    </form>
		<tr  style="text-align: center; background-color:lightgray;">
		   <td><input type="checkbox" name="select_me" id="select_me" value="${cartList.p_cartnum}" checked/></td>
		   <td><img src="${pageContext.request.contextPath}/upload/goods/${cartList.p_goodsphoto}" alt="" style="    width: 120px;
    height:100px;
    border-radius:20px;
    border:0;"></td>
			<td><a href="${pageContext.request.contextPath}/goods/detail.do?g_num=${cartList.p_num}&&as_name=${cartList.p_name}">${cartList.p_goodsname}</a></td>
			<td><fmt:formatNumber value="${cartList.p_amount}" pattern="#,###"/></td>
			<td>${cartList.p_name}</td>
			<td>
			<fmt:formatNumber value="${cartList.p_price}" pattern="#,###"/></td>	
		</tr>
		</c:forEach>
		
	</table><br>
	<div style="float:left;">
	
	<button class="btn btn-primary" id="delete_cart">DELETE</button>
	<button class="test btn">SELECT</button>
	</div>
<!-- 전체 결제 버튼  -->
&nbsp;<button style="float:right;" class="btn btn-warning test">PAYMENT</button>
	<div style="float:right;"id="allTotal">
		<span><b style="color:red">TOTAL : <fmt:formatNumber value="${sum}" pattern="#,###"/> won &nbsp;</b></span>
	</div>
	
</c:if>
<br><br><br><br>
<div style="text-align:center">
<button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">HOME</button>
<button class="btn btn-warning" onclick="nanoCount();">분할카운팅</button>
</div>
<br><br><br>
</div>