<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function agree(){
		var okCheck = $("input:checkbox[name='agree']").is(":checked");
		
		if (!okCheck) {
			alert("개인정보 약관에 동의하셔야 합니다.");
			return false;
		}else{
			document.location.href='apForm1.do';
		}
		
	}
</script>
<!-- jQuery -->
<div class="container">
	<div style="margin: 50px 0 0 0; text-align:center;">
		<span>
				CNA과 함께라면 국내 최고의 동물 봉사자가 될 수 있습니다<br>
				반려동물을 향한 따뜻한 마음을 가진 분과 함께하고 싶습니다<br>
				그 외의 다른 전문적인 부분은 국내최고 반려동물 전문가들과 함께 도와드리겠습니다<br>
		</span>
	</div>
	<div style="margin: 50px 0 0 0; text-align:center;">
		<span>
				&lt; 지원 전 확인 사항 &gt;
				<br><br>
				**********************************<font size="2.5em" color="red"> 무료봉사입니다.</font>**********************************<br>
				********************<font size="2.5em"> 여기서 작성한 것을 기반으로 목록에 보여집니다.</font>*******************<br>
				**********************************<font size="2.5em"> 추후 수정 가능 </font>**********************************<br>
		</span>
	</div>
	<div style="margin: 50px 0 0 0; text-align:center;">
		<span>
				<font size="4.0em">1. 지원 자격 요건</font><br>
				- ***** 반려동물을 5년이상 키워본 경험 보유<br>
					
				- 직업의식을 가지고 열심히 하실 분<br>
					
				- ***** 서울, 경기, 인천 수도권에 거주<br>
					
				- 25세 이상 60세 미만<br>
		</span>
	</div>
	<div style="margin: 50px 0 0 0; text-align:center;">
		<span>
				<font size="4.0em">2. 우대사항</font><br>
				반려동물 관련 자격증 소지자, 관련학과 전공, 펫시터 경력자<br>
		</span>
	</div>
	<div style="margin: 50px 0 0 0; text-align:center;">
		<span>
				<font size="4.0em">3. 펫시터 활동 절차</font><br>
				1차 (서류심사)<br>
				2차 (대면 면접)<br>
				3차 온라인 교육 4시간 이수 및 온라인 테스트 통과<font size="2em" color="red">(*70점 이상)</font><br>
				4차 오프라인 교육 4시간 이수 및 계약서 작성<br>
				5차 임시보호자 최종 등록<br>
		</span>
	</div>
	<div style="margin: 20px 0 0 0; text-align:center;">
		<span>
				* 교육생 혜택 : 프로필 영상 및 사진 촬영, 명함, 교육책자, 서비스매뉴얼 무상 지원<br>
		</span>
	</div>
	<div style="margin: 20px 0 0 0; text-align:center;">
		<span>
				*지원결과 안내<br>
				1차 서류심사와 3차 테스트 합격자 분들에 한해 평일 기준 3일 이내에 개별 연락드립니다.<br>
		</span>
	</div>
	<div style="margin: 20px 0 0 0; text-align:center;">
		<span>
				---------------------------------------------------------------------------------------------------------------------------------------------------------------<br>
		</span>
	</div>
	<div style="margin: 20px 0 50px 0; text-align:center;">
		<span>
				<font size="4.0em">이런 분들은 지원하기 어려워요</font><br>
				1. 미성년자 및 25세 이하<br>
				2. 임산부<br>
				3. 15세 미만의 아동과 함께 거주하시는 분<br>
				4. 실내 흡연자 또는 실내 흡연자와 거주하는 경우<br>
		</span>
	</div>
	<div style="margin: 20px 0 0 0; text-align:center;">
		<span>
				---------------------------------------------------------------------------------------------------------------------------------------------------------------<br>
		</span>
	</div>
	<div style="margin: 20px 0 50px 0; text-align:center;">
		<span>
			 <font size="4.0em">온란인 이론 교육(4시간)</font><br>
			 - 반려견 첫만남 시 주의사항 및 친화방법<br>
			 - 체중, 나이에 알맞은 배식방법<br>
			 - 카밍시그널, 반려견의 몸짓 제대로 알기<br>
			 - 질병과 예방접종(예방법봉 및 중성화 시기 및 유의사항, 전염질병 등)<br>
			 - 기본 그루밍(눈물정리, 발톱/발바닥털 정리, 귀청소, 항문낭, 빗질 등)<br>
			 - 응급처치법 (CPR 및 찰과성, 화상, 타박상 대처법 등)<br>
		</span>
	</div>
	<div style="margin: 20px 0 50px 0; text-align:center;">
		<span>
			 <font size="4.0em">오프라인 실습 교육(4시간)</font><br>
			 - 반련견 문제행동(마킹, 짖음, 공격성, 식분증, 물어뜯기)증세와 행동관찰<br>
			 - 문제행동에 대한 대처방법 및 2마리 이상 돌봄 시 주의사항<br>
			 - 스트레스 해소에 좋은 반려견 놀이법<br>
			 - 안전하고 편안한 산책 방법 그리고 펫티켓<br>
		</span>
	</div>
	<hr>
	<div class="form-group" style="margin: 20px 0 50px 0;">
		<div class="custom-control custom-checkbox">
			<input type="checkbox" class="custom-control-input" id="okCheck" name="agree" value="true">
				<label class="custom-control-label" for="okCheck">이용 약관에 동의 합니다.</label>
				<button type="button" class="btn btn-primary" style="float:right;" onclick="javascript:agree();">다음</button>
		</div>
		
	</div>
</div>







