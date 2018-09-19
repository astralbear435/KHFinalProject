<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/acodian.css">
<script>
$(function() {

	  var group = $(".group");

	  group.each(function() {
	    var _group = new GroupBox(this);
	  });

	  // 사용자 정의 생성자 함수 정의
	  function GroupBox(groupElement) {

	    var box = $(groupElement).find(".box");
	    var title = $(groupElement).find(".box .title a");

	    box.each(function(idx) {
	      var newBox = new RootBox(this);
	      if (idx > 0) {
	        newBox.siblingsClose();
	      }
	    });
	  }

	  // 사용자 정의 생성자 함수 정의
	  function RootBox(boxElement) {
	    var _this = this;
	    var boxEl = $(boxElement);
	    var target = $(boxEl).find(".title a");
	    var cont = $(boxEl).find(".cont");

	    // _groupParent = $(boxEl).parent();

	    target.on("click", anchorClickEvent);

	    function anchorClickEvent() {

	      if (cont.is(':hidden')) {
	        _this.open();
	      } else {
	        _this.close();
	      }
	    }

	    _this.siblingsClose = function() {
	      cont.css('display', 'none');
	    };

	    _this.open = function() {
	      cont.slideDown();
	    };
	    _this.close = function() {
	      cont.slideUp();
	    }
	  }
	});
</script>
<div class="container">
	<br><br>
	<div class="floatL">&nbsp;&nbsp;</div>
	
	<h1 style="float:center;">FAQ</h1>
	
	<div class="col-md-12" style="margin:0 0 50px 0;">
	
		<div class="group g1 panel panel-default">
		  <div class="box">
		    <strong class="title title1"><a href="#">예방 접종은 꼭 실시해야하나요?</a></strong>
		    <div class="cont table-warning">
		    	접종은 주인들의 선택사항이지만 실시해야하는 필수 사항 입니다.<br>
		    	<font size="4em" color="red">예방접종은 반드시 실시하는 것이 좋으며, 분양 후 부터 예정일을 잘 준수하여 접종을 완료하도록 합니다.</font><br>
		    	예방접종은 견주가 반려동물에게 선물하는 최고의 선물입니다.<br>
		    </div>
		  </div>
		  <div class="box">
		    <strong class="title"><a href="#">분양 후 목욕은 언제부터 하며 몇일마다 하는 것이 좋은가요?</a></strong>
		    <div class="cont table-warning">
		    	어린강아지에게 잦은 목욕은 <font size="4em" color="red">심한 스트레스, 피부병 및 감기</font> 등을 유발 할 수 있습니다.<br>
		    	분양후 3일~7일 정도의 적응기간을 가지고 난 후 목욕을 해주는 것이 좋으나,<br>
		    	몸에 배변이 묻을 경우에는 부분적으로 씻겨 주는 것이 좋습니다.<br>
		    	(피부병이 유발될 수 있습니다.)<br>
		    	<br>
		    	성견이 되었을 때는 1~2주 간격이 적당한 목욕 주기입니다.<br>
		    </div>
		  </div>
		  <div class="box">
		    <strong class="title"><a href="#">밥을 먹지 않을 때는 어떻게 하나요?</a></strong>
		    <div class="cont table-warning">
		    	분양 후 어린강아지, 고양이들이 밥을 먹지 않는 것은 중요하게 체크를 해주어야 합니다.<br>
		    	<font size="4em" color="red">대다수의 어린강아지가 환경적인 변화로써 식욕이 떨어지지만</font><br>
		    	(이동간 스트레스, 변화된 환경, 사료 급여방법 등..)<br>
		    	기생충, 바이러스 장염 등 질병에 의한 식욕부진도 있을 수 있습니다.<br>
		    	아이의 식욕이 떨어졌다면 변사진을 체크하여 담당 플래너에게 바로 연락주시기 바랍니다.<br>
		    </div>
		  </div>
		  <div class="box">
		    <strong class="title"><a href="#">분양 후 외출은 언제부터 가능한가요?</a></strong>
		    <div class="cont table-warning">
		    	강아지들이 1~2주 정도의 적응기간을 보내고나면 집에서 활발한 운동력을 보이게 됩니다.<br>
		    	이때 견주들은 산책에 대하여 준비를 하게됩니다.<br>
		    	<font size="4em" color="red">외출은 4차 접종이상 완료한 뒤,</font> 산책을 하는 것이 좋습니다.<br>
		    	<br>
		    	&lt; 접종 미완료시 외출할 때 주의할 점! &gt;<br>
		    	1. 개들이 있는 곳과 전봇대를 피한다.<br>
		    	2. 차들이 다니는 곳을 피한다.<br>
		    	3. 풀밭이나 물가를 피한다.<br>
		    	<br>
		    	
		    </div>
		  </div>
		  <div class="box">
		    <strong class="title"><a href="#">대변이 묽은데 어떻게 하는 것이 좋을까요?</a></strong>
		    <div class="cont table-warning">
		    	어린 강아지, 고양이들의 건강 체크를 할 때 가장 우선시 되는 것이 대변활동에 대한 부분인데요.<br>
		    	설사의 원인으로 환경변화에 의한 스트레스, 기생충, 바이러스, 이물 섭취 등 여러 가지의 요소들이 있습니다.<br>
		    	이때 견주들은 산책에 대하여 준비를 하게됩니다.<br>
		    	<br>
		    	&lt; 변상태에 따른 자가진단 법 &gt;<br>
		    	건강 체크를 할 때에는 대변의 <font size="4em" color="red">형태, 색상, 냄새, 횟수</font> 등으로 자가진단을 합니다.<br>
		    	위 4가지를 꾸준히 체크하여 담당 플래너와의 상담으로 반려 동물의 건강을 지켜주세요.<br>
		    	<br>
		    	
		    </div>
		  </div>
		</div>

	</div>
	
</div>

