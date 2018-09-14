<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/seyeong/shelter.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
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
<script type="text/javascript">
$(document).ready(function(){
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
});
</script>
<div class="page-main-style">
	<div>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div class="titleText">회원가입</div><br>
		<div class="w500 margin0a">
			<form:form commandName="command" id="shelterRegisterForm" method="post" action="write.do" encType="multipart/form-data">
				<fieldset class="clearB">
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="s_id">아이디</label><br>
							<form:input path="s_id" class="form-control" id="s_id" name="s_id"
										style="width: 278px;" placeholder="아이디 입력"/>
						</div>
						<div class="floatL h72" style="width: 5px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button" id="confirmId" class="btn btn-primary" value="ID 중복확인">
						</div>
						<div class="floatL h72" style="width: 10px;"></div>
						<div class="floatL h72 lineh95" id="message_id"
								style="width: 90px; text-align: center;"></div>
					</div>
					<div class="form-group clearB">
						<label for="s_passwd">비밀번호</label>
						<form:password path="s_passwd" class="form-control" name="s_passwd"
										placeholder="비밀번호 입력"/>
						<form:errors path="s_passwd" cssClass="error-color"/>
					</div>
					<div class="form-group">
						<label for="s_name">보호소 명</label>
						<form:input path="s_name" class="form-control" name="s_name"
										placeholder="보호소 명 입력"/>
						<form:errors path="s_name" cssClass="error-color"/>
					</div>
					<div class="form-group">
						<label for="s_license_num">사업자 등록번호</label>
						<form:input path="s_license_num" class="form-control" name="s_license_num"
										 aria-describedby="licenseHelp" placeholder="사업자 등록번호 입력"/>
						<small id="licenseHelp" class="form-text text-muted">허위 기재 시 이용이 제한 될 수 있습니다.</small>
						<form:errors path="s_license_num" cssClass="error-color"/>
					</div>
					<div class="form-group">
						<label for="s_phone">전화번호</label>
						<form:input path="s_phone" class="form-control" name="s_phone"
										placeholder="전화번호 입력( - 포함)"/>
						<form:errors path="s_phone" cssClass="error-color"/>
					</div>
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="s_email">이메일</label><br>
							<form:input path="s_email" class="form-control" id="s_email" name="s_email"
										style="width: 278px;" placeholder="이메일 입력"/>
						</div>
						<div class="floatL h72" style="width: 5px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button" id="confirmEmail" class="btn btn-primary" value="이메일 확인">
						</div>
						<div class="floatL h72" style="width: 10px;"></div>
						<div class="floatL h72 lineh95" id="message_email"
								style="width: 85px; text-align: center;"></div>
					</div>
						
					<div class="horizontal"></div><!-- 가로 여백 -->
					
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="s_zipcode">우편번호</label><br>
							<form:input path="s_zipcode" class="form-control" name="s_zipcode"
											placeholder="우편번호 찾기 버튼 클릭" style="width:200px;" readonly="readonly"/>
							<form:errors path="s_zipcode" cssClass="error-color"/>
						</div>
						<div class="floatL h72" style="width: 5px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button"  class="btn btn-primary" value="우편번호 찾기"
						      		onclick="DaumPostcode()">
						</div>
					</div>
					<div class="form-group clearB">
						<label for="s_address1">주소</label>
						<form:input path="s_address1" class="form-control" name="s_address1"
									placeholder="주소 입력" readonly="readonly"/>
					</div>	
					<div class="form-group">
						<label for="s_address2">상세 주소</label>
						<form:input path="s_address2" class="form-control" name="s_address2"
									 placeholder="상세 주소 입력"/>
						<form:errors path="s_address2" cssClass="error-color"/>
					</div>
						
					<div class="horizontal"></div><!-- 가로 여백 -->
					
					<div class="form-group">
						<label for="s_content">보호소 소개</label>
						<form:textarea path="s_content" rows="5" cols="80" style="border: 1.5px solid #333333; resize:none;"
										placeholder="간단한 보호소 소개 입력"></form:textarea><br>
						<div class="letter-count" style="text-align:right">0 / 1000</div>
						<form:errors path="s_content" cssClass="error-color"/>
					</div>
					
					<div class="form-group">
						<label for="s_upload">이미지 첨부</label>
						<input type="file" class="form-control-file" name="s_upload" id="s_upload" aria-describedby="fileHelp">
						<small id="fileHelp" class="form-text text-muted">
							보호소 소개페이지에 들어갈 이미지를 첨부해주세요.<br>
							미첨부시 기본 이미지로 표시됩니다!
						</small>
					</div>
					
					<br><hr size="1" width="100%">
						
					<div class="w500 clearB" style="text-align:center; margin: 0;">
						<input type="submit" class="btn btn-primary" value="전송">
						<input type="button"  class="btn btn-primary" value="홈으로"
							      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
					</div>
					
					
					
					<div class="horizontal"></div><!-- 가로 여백 -->
					
				</fieldset>
			</form:form>
		</div>
	</div>
	<br>
</div>