<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/seyeong/shelter.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var OriginInputLength = $('#s_content').val().length;
	var remain = OriginInputLength;
	remain += '/1000';
	$('.letter-count').text(remain);
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup', 'textarea', function(){
		//입력한 글자수를 구함
		var inputLength = $(this).val().length;
		
		if(inputLength > 1000){
			//1000자를 넘어선 경우 1000자 까지로 잘라버림
			$(this).val($(this).val().substring(0, 1000));
		}else{
			//1000자 이하인 경우
			var remain = inputLength;
			remain += '/1000';
		}
		$('.letter-count').text(remain);
	});
	
//따라다니는 알림창
	var layerTopOffset =50;
    var currentPosition = parseInt($("#openModal").css("top"));
    $(window).scroll(function() {
        var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
        $("#openModal").stop().animate({"top":position+currentPosition+layerTopOffset+"px"},1000);
    });
  //모달창 클릭시
    $("#openModal").click(function() {
    	 $('#myModalSoeun').show();    
    });
  //모달 창 닫기
    $("#closeModal").click(function() {
         $('#myModalSoeun').hide();
    }); 
  $("#addGoods").click(function(){
	 var pad=$('#pad').val(); 
	 var dogfood=$('#dogfood').val(); 
	 var catfood=$('#catfood').val(); 
	 var shampoo=$('#shampoo').val(); 
	 var catsand=$('#catsand').val(); 
	 var as_id=$('#s_id').val();
	 var as_name=$('#s_name').val();
	 var as_location=$('#s_address1').val(); 
		$.ajax({
			type:'post',
			data:{pad:pad,dogfood:dogfood,catfood:catfood,shampoo:shampoo,catsand:catsand,as_id:as_id,as_name:as_name,as_location:as_location},
			url:'insertgoods.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='success'){
					alert('정상적으로 고객님의 후원물품 리스트에 등록되었습니다.');
					$('#myModal').hide();
					location.reload();
				}else{
					$('#myModal').hide();
					alert('죄송합니다. 세션만료로 로그아웃 되었습니다. 다시 로그인 해주십시오.');
					location.href="../member/login.do";	
				}
			},
			error:function(){
	      	alert('(물품등록)에러입니다.');
	      	$('#myModal').hide();
	      	location.reload();
          	}
	});
	 
  });
});
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('s_zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('s_address1').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('s_address2').focus();
            }
        }).open();
    }
</script>
<!-- 등록안된 보호소만 신청하라고 뜰거에요 -->
<c:if test="${as_did==0}">
<!-- 물품신청하세요 모달창 -->
 <div style="cursor:pointer;background-color:#DDDDDD;width:10%;height:15%;position: absolute;float:right;text-align:center;padding-top: 1%;" id="openModal">
                <span class="pop_bt" style="font-size: 15pt;"> 
                <a class="agile-icon"><i class="fa fa-bell"></i></a><br><b>후원물품<br>신청하기</b></span>
</div>
</c:if>
<!-- 여는모달 -->
    <div id="myModalSoeun" class="modal">
 
<!-- 모달내용 -->
      <div class="modal-content">
                <p style="text-align: center;"><span style="font-size: 14pt;"><b><span style="font-size: 24pt;">후원 물품 신청</span></b></span></p>
                <p style="text-align: center; line-height: 1.5;"><br/>기본 물품 5개에 대해 필요수량을 입력해 주세요.<br>
                <a style="color:red;">*필요없는 물품은 비워두셔도 됩니다.<br>추후 추가신청을 통해 필요 물품을 신청하실 수 있습니다.</a></p>
          <div style="text-align:center;">
			<ul>
				<li>배변패드: <input type="number" id="pad"></li>
				<li>개사료 : <input type="number" id="dogfood"></li>
				<li>고양이사료:	<input type="number" id="catfood"></li>
				<li>샴푸(전견용): <input type="number" id="shampoo"></li>
				<li>고양이 모래: <input type="number" id="catsand"></li>
		   </ul></div>
		<div style="text-align: center;">
			<div
				style="padding-bottom: 10px; padding-top: 10px; cursor: pointer; background-color: #DDDDDD; width: 40%; display: inline-block;"
				id="closeModal">
				<span class="pop_bt" style="font-size: 13pt;"><b>취소</b></span>
			</div>
			<div
				style="padding-bottom: 10px; padding-top: 10px; cursor: pointer; background-color: #DDDDDD; width: 40%; display: inline-block;"
				id="addGoods">
				<span class="pop_bt" style="font-size: 13pt; color: black;"><b>신청</b></span>
			</div>
		</div>
	</div>
 
    </div>
<!--모달닫는다~~~~~-->
            
<div class="page-main-style">
	<div>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div class="w500 margin0a">
			<form id="shelterDeleteForm" method="post" action="delete.do">
				<input type="hidden" name="s_id" value="${shelter.s_id}">
			</form>
			<form id="shelterUpdateForm" method="post" action="update.do" encType="multipart/form-data">
				<fieldset style="clear: both;">
						<div class="w500 h72">
							<div class="form-group floatL">
								<div class="titleText">${shelter.s_id}</div><br>
								<input type="hidden" id="s_id" name="s_id" value="${shelter.s_id}">
							</div>
						</div>
						<div class="form-group clearB">
							<label for="s_passwd">비밀번호</label>
							<input type="password" class="form-control" name="s_passwd" value="${shelter.s_passwd}">
						</div>
						<div class="form-group">
							<label for="s_name">보호소 명</label>
							<input type="text" class="form-control" name="s_name" id="s_name" value="${shelter.s_name}">
						</div>
						<div class="form-group">
							<label for="s_license_num">사업자 등록번호</label>
							<input type="text" class="form-control" name="s_license_num" value="${shelter.s_license_num}">
						</div>
						<div class="form-group">
							<label for="s_phone">전화번호</label>
							<input type="text" class="form-control" name="s_phone" value="${shelter.s_phone}">
						</div>
						<div class="form-group">
							<label for="s_email">이메일</label>
							<input type="email" class="form-control" name="s_email" value="${shelter.s_email}">
						</div>
						
						<div class="horizontal"></div><!-- 가로 여백 -->
						
						<div class="w500 h72">
							<div class="form-group floatL">
								<label for="s_zipcode">우편번호</label><br>
								<input type="text" class="form-control" name="s_zipcode" id="s_zipcode"
										 value="${shelter.s_zipcode}" style="width: 200px;" readonly>
							</div>
							<div class="floatL h72" style="width: 20px;"></div>
							<div class="form-group floatL h72 lineh95">
								<input type="button"  class="btn btn-primary" value="우편번호 찾기"
							      		onclick="DaumPostcode()">
							</div>
						</div>
						<div class="form-group clearB">
							<label for="s_address1">주소</label>
							<input type="text" class="form-control" name="s_address1" id="s_address1" value="${shelter.s_address1}">
						</div>
						<div class="form-group">
							<label for="s_address2">상세 주소</label>
							<input type="text" class="form-control" name="s_address2" id="s_address2" value="${shelter.s_address2}">
						</div>
						
						<div class="horizontal"></div><!-- 가로 여백 -->
					
						<div class="form-group">
							<label for="s_content">보호소 소개</label>
							<textarea rows="5" cols="80" style="border: 1.5px solid #333333; resize:none;"  name="s_content" id="s_content"
											placeholder="간단한 보호소 소개 입력">${shelter.s_content}</textarea><br>
							<div class="letter-count" style="text-align:right">0 / 1000</div>
						</div>
						
						<div class="form-group">
							<label for="s_upload">이미지 첨부</label>
							<input type="file" class="form-control-file" id="s_upload" name="s_upload" aria-describedby="fileHelp">
							<small id="fileHelp" class="form-text text-muted">
								200px X 200px 권장<br>
								<c:if test="${empty shelter.s_filename}">
									보호소 소개페이지에 들어갈 이미지를 첨부해주세요.<br>
									미첨부시 기본 이미지로 표시됩니다!
								</c:if>
								<c:if test="${!empty shelter.s_filename}">
									(${shelter.s_filename})파일이 등록되어 있습니다.<br>
									다시 업로드하면 기존 파일은 삭제됩니다.
								</c:if>	
							</small>
						</div>
						
						<br><hr size="1" width="100%">
						
						<div class="w500 clearB" style="text-align:center; margin: 0;">
							<input type="button" id="shelterUpdateBtn" class="btn btn-warning" value="회원 정보 수정">
							<input type="button" id="shelterDeleteBtn"  class="btn btn-danger" value="회원 탈퇴">
						</div>
						<div class="horizontal"></div><!-- 가로 여백 -->
					</fieldset>
			</form>
		</div>
	</div>
	<br>
</div>