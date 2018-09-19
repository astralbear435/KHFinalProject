$(document).ready(function(){
	var m_num='';
	var order = $("#menu_order").val();
	//메뉴 선택 변경
	$(document).on('change','.select_id1',function() {
		var str='';
		$('.select_id1 option:selected').each(function() {
			str = $( this ).val();
		});
		m_num=str;
		redetail(str);
	}).trigger('change');
	
	function redetail(str){
		if(m_num!='0'){
			$.ajax({
				type:'post',
				data:{m_num:m_num},
				url:'selectedMenu.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					var count = data.count;
					var menu= data.menu;
					order=count+1;
					var output =''
						$('#menu_detail').empty();
					output='<div class="form-group"><label for="menu_use">메뉴 활성화</label><br>'
						if(menu.menu_use=="Y"){
							output+='<input type="radio" name="menu_use" id="menu_use1" value="Y" checked="checked"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N"> 미표시 </div>';	
						}else{
							output+='<input type="radio" name="menu_use" id="menu_use1" value="Y"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N" checked="checked"> 미표시 </div>';
						}
					output+='<div class="form-group"><label for="menu_name">메뉴 명</label><input type="text" id="menu_name" name="menu_name" class="form-control" value="'+menu.menu_name+'" placeholder="메뉴명"><span class="help-block"></span></div>';
					output+='<div class="form-group"><label for="menu_url">메뉴 주소</label> <input type="text" id="menu_url" name="menu_url" class="form-control" value="'+menu.menu_url+'" placeholder="/main/main.do"><span class="help-block"></span></div>';
					output+='<div class="form-group"><label for="menu_order">메뉴 순서</label> <input type="number" id="menu_order" name="menu_order" class="form-control" value="'+menu.menu_order+'" placeholder="0"><span class="help-block"></span></div>';
					output+='<div class="form-group"><label for="menu_dd">드롭다운 메뉴 사용</label><br>';
					if(menu.menu_dropdown=='N'){
						output+='<input type="radio" name="menu_dd" id="menu_dd1" value="Y"> 사용 <input type="radio" name="menu_dd" id="menu_dd2" value="N" checked="checked"> 미사용</div>';	
					}else{
						output+='<input type="radio" name="menu_dd" id="menu_dd1" value="Y" checked="checked"> 사용 <input type="radio" name="menu_dd" id="menu_dd2" value="N" > 미사용 </div>';
					}
					output+='<div class="form-group"><label>상위 메뉴</label><select id="select_id2" name="parent_num" class="form-control"><option selected="selected" value="0">-선택-</option></select></div>'
						output+='<button type="button" id="menu_update" class="btn btn-info" data-num="'+menu.menu_num+'">수정</button>';
					$('#menu_detail').append(output);
				},error:function(){
					alert('네트워크 에러!!');
				}
			});
		}else{
			var output2='';
			$('#menu_detail').empty();
			output2='<div class="form-group"><label for="menu_use">메뉴 활성화</label><br>'
				output2+='<input type="radio" name="menu_use" id="menu_use1" value="Y" checked="checked"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N"> 미표시 </div>';	
			output2+='<div class="form-group"><label for="menu_name">메뉴 명</label><input type="text" id="menu_name" name="menu_name" class="form-control" placeholder="메뉴명"><span class="help-block"></span></div>';
			output2+='<div class="form-group"><label for="menu_url">메뉴 주소</label> <input type="text" id="menu_url" name="menu_url" class="form-control" placeholder="/main/main.do"><span class="help-block"></span></div>';
			output2+='<div class="form-group"><label for="menu_order">메뉴 순서</label> <input type="number" id="menu_order" name="menu_order" class="form-control" value='+order+' placeholder="0"><span class="help-block"></span></div>';
			output2+='<div class="form-group"><label for="menu_dd">드롭다운 메뉴 사용</label><br>';
			output2+='<input type="radio" name="menu_dd" id="menu_dd1" value="Y"> 사용 <input type="radio" name="menu_dd" id="menu_dd2" value="N" checked="checked"> 미사용</div>';	
			output2+='<div class="form-group"><label>상위 메뉴</label><select id="select_id2" name="parent_num" class="form-control"><option selected="selected" value="0">-선택-</option></select></div>';
			output2+='<input class="btn btn-info" type="submit" value="추가">'
				$('#menu_detail').append(output2);

		}
	}
	//메뉴 선택창 초기화
	function renewal(t_msg){
		alert(t_msg);
		$.ajax({
			type:'post',
			url:'renewalMenu.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var mcount = data.count;
				var list = data.list;
				var output =''
				$('#menu_box').empty();
				output='<select id="select_id1" name=s1 size=15 class="form-control select_id1" style="font-size: 20px;">'
					output+='<option value=0>-메뉴 추가-</option>'
						if(mcount < 0 || list == null){
							alert('목록 호출 오류 발생');
						}else{
							$(list).each(function(index,item){
								if(item.menu_num==m_num){
									if(item.menu_depth>1){
										output+='<option selected="selected" value="'+item.menu_num+'">ㅡ '+item.menu_name+'</option>';
									}else{
										output+='<option selected="selected" value="'+item.menu_num+'">'+item.menu_name+'</option>';
									}
								}else{
									if(item.menu_depth>1){
										output+='<option value="'+item.menu_num+'">ㅡ '+item.menu_name+'</option>';
									}else{
										output+='<option value="'+item.menu_num+'">'+item.menu_name+'</option>';
									}
								}
								
							});
						}
				$('#menu_box').append(output);
			},error:function(){
				alert('네트워크 에러!!');
			}
		});
	}
	
	$(document).on('click','#menu_up',function(){
		$.ajax({
			type:'post',
			url:'upOrderMenu.do',
			data:{m_num:m_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var msg= data.msg;
				if(msg=="more"){
				}else{
					alert("더 이상 못 올립니다.");
				}
			},error:function(){
				alert('네트워크 에러!!');
			}
		});
		
		renewal("순서 변경");
		redetail(m_num);
	});

	$(document).on('click','#menu_down',function(){
		$.ajax({
			type:'post',
			url:'downOrderMenu.do',
			data:{m_num:m_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var msg= data.msg;
				if(msg=="more"){
				}else{
					alert("더이상 메뉴가 없습니다.");
				}
			},error:function(){
				alert('네트워크 에러!!');
			}
		});
		
		renewal("순서 변경!");
		redetail(m_num);
	});
	
	$('#menu_delete').click(function(){
		$.ajax({
			type:'post',
			url:'deleteMenu.do',
			data:{m_num:m_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var msg= data.msg;
				if(msg=="success"){
					renewal("삭제 완료");
				}else{
					alert("삭제오류");
				}
			},error:function(){
				alert('네트워크 에러!!');
			}
		});
		
		
		
	});
	$(document).on('click','#menu_update',function(){
		var menu_num = $(this).attr("data-num");
		var menu_use =  $(":input:radio[name=menu_use]:checked").val()
		var menu_name =  $("input[name=menu_name]").attr("value");
		var menu_url =  $("input[name=menu_url]").attr("value");
		var menu_order =  $("#menu_order").val(); 
		var menu_dd = $(":input:radio[name=menu_dd]:checked").val();
		var parent_num = $("#select_id2").val(); 
		alert(menu_num+","+menu_use+","+menu_name+","+menu_url+","+menu_order+",dd="+menu_dd+","+parent_num+","); 
		$.ajax({
			type:'post', 
			data:{menu_num:menu_num,menu_use:menu_use,menu_name:menu_name,menu_url:menu_url,menu_order:menu_order,menu_dd:menu_dd,parent_num:parent_num},
			url:'updateMenu.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var msg= data.msg;
				if(msg=="success"){
					renewal("수정 완료");
				}else{
					alert("수정 오류");
				}
			},error:function(){
				alert('네트워크 에러!!');
			}
		})
		
	});
	
	$('#writeMenu').submit(function(){
		//=======오류창=======
		if($('#menu_name').val()==''){
			$('#menu_name').parent().addClass('has-error');
			$('#menu_name').siblings('span').text('메뉴명을 입력하세요.');
			if($('#menu_url').val()==''){
				$('#menu_url').parent().addClass('has-error');
				$('#menu_url').siblings('span').text('메뉴URL을 입력하세요.');
			};
			return false;
		};
		if($('#menu_url').val()==''){
			$('#menu_url').parent().addClass('has-error');
			$('#menu_url').siblings('span').text('메뉴URL을 입력하세요.');
			if($('#menu_name').val()==''){
				$('#menu_name').parent().addClass('has-error');
				$('#menu_name').siblings('span').text('메뉴명을 입력하세요.');
			};
			return false;
		};
		//=======오류창=======
	});
	//====span 초기화 ====
	$('#menu_name').keyup(function () {
		$(this).parent().removeClass('has-error');
		$(this).siblings('span').text('');
	});
	$('#menu_url').keyup(function () {
		$(this).parent().removeClass('has-error');
		$(this).siblings('span').text('');
	});
})