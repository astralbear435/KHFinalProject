$(document).ready(function(){
	//수량 변경
	$('#order_quantity').on('keyup mouseup',function(){
		changeQuantity();
		
	});	
	function changeQuantity(){
		if(isNaN($('#order_quantity').val())||$('#order_quantity').val()<=0){
			alert("0보다 큰 숫자만 입력해 주세요!");
			$('#order_quantity').val('');
			return;
	}
		var sum=Number($('#sumPrice').val())*Number($('#order_quantity').val());
		var modify='합계 : ';
		modify+=sum+'원';
		//있던값초기화 후 이어붙이기
		$('#total').text(modify);
		$('#modalPrice').text(modify);
		var Mamount=$('#order_quantity').val();
		$('#modalAmount').text('수량 : '+Mamount);
		
}
	//장바구니 담기 유효성 체크
	$('#cart_order').on('click','#gocart',function(event){
		if($('#order_quantity').val()==''){
			alert('수량을 입력하세요! (최소 수량 1개)');
			$('#order_quantity').focus();
			return false;
		}
		else{
			var p_num=$('#p_num').val();//상품번호
			var p_id=$('#p_id').val();//아이디
			var p_name=$('#p_name').val();//보호소명
			var p_price=$('#sumPrice').val();//총합계
			var p_amount=$('#order_quantity').val();//갯수
			var p_goodsname=$('#p_goodsname').val();//물품명
			var p_goodsphoto=$('#p_goodsphoto').val();	//물품사진

			$.ajax({
				type:'post',
				data:{p_num:p_num,p_id:p_id,p_name:p_name,p_price:p_price,p_amount:p_amount,p_goodsname:p_goodsname,p_goodsphoto:p_goodsphoto},
				url:'insertCart.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='logout'){
						alert('로그인해야 장바구니를 이용 할 수 있습니다.');
					}else if(data.result=='success'){
						move=confirm('장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?');
						if(move){
							location.href="../goods/cartList.do";
						}else{
							alert('장바구니로 이동이 취소 되었습니다. 천천히 둘러보세요');
						}
					}else{
						alert('<오류> 다시시도해 주십시오.');
						}
				},
				error:function(){
		      	alert('(장바구니)에러입니다.');
	          	}
		});
	}
	});	
});