$(document).ready(function(){
	
	//새로고침해도 상태를 유지하기위해
	function roadData(){
		var t_num=$('#bookmark').attr('data-num');//글 번호	
		var t_id=$('#bookmark').attr('data-id');//버튼 누른사람의 아이디
		$.ajax({
			type:'post',
			data:{ap_book_num:t_num,ap_book_id:t_id},
			url:'checkBook.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var count=data.count;
				var booklist = data.booklist;
				if(count==1){
					//이미 좋아요 한 게시글
					var str="";
					str+='<button type="button" style="float:right;" id="bookmark" class="btn btn-secondary"><i class="fa fa-bookmark"></i></button>';
					$('#bookmark').empty();
					$('#bookmark').append(str);
				}else{
					//좋아요 안한 게시글
					var str="";
					str+='<button type="button" style="float:right;" class="btn btn-secondary"><i class="fa fa-bookmark-o"></i></button>';
					$('#bookmark').empty();
					$('#bookmark').append(str);	
				}
						
			},error:function(){
					alert('로드시 오류 ');
				}
			});
	}

	//클릭시 변환한다.
	$('#bookmark').click(function(){
		var t_num=$('#bookmark').attr('data-num');//글 번호	
		var t_id=$('#bookmark').attr('data-id');//버튼 누른사람의 아이디
		$.ajax({
			type:'post',
			data:{ap_book_num:t_num,ap_book_id:t_id},
			url:'insertBook.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var count=data.count;
				var booklist = data.booklist;
				if(count==1){
					//이미 북마크가 되어있어 db를 지우고 좋아요를 취소
					alert('북마크를 취소합니다.');
					var str="";
					str+='<button type="button" style="float:right;" class="btn btn-secondary"><i class="fa fa-bookmark-o"></i></button>';
					$('#bookmark').empty();
					$('#bookmark').append(str);
					
				}else{
					var str="";
					str+='<button type="button" style="float:right;" id="bookmark" class="btn btn-secondary"><i class="fa fa-bookmark"></i></button>';
					$('#bookmark').empty();
					$('#bookmark').append(str);
				}	
			},error:function(){
				alert('로그인 후 이용해주세요.');
			}
		 });
		});
	 roadData();
});