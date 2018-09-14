$(document).ready(function(){
	var m_num='';
	$('#select_id1').change(function() {
		var str='';
	    $('#select_id1 option:selected').each(function() {
	    	str = $( this ).val();
	    });
	    m_num=str;
	    $.ajax({
	    	type:'post',
			data:{m_num:m_num},
			url:'selectedMenu.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var menu= data.menu;
				var output =''
				$('menu_detail').empty();
				output='<div class="form-group"><label for="menu_use">메뉴 활성화</label><br>'
				if(menu.menu_use=='Y'){
					output+='<input type="radio" name="menu_use" id="menu_use1" value="Y" checked="checked"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N"> 미표시 </div>';	
				}else{
					output+='<input type="radio" name="menu_use" id="menu_use1" value="Y"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N" checked="checked"> 미표시 </div>';
				}
				output+='<div class="form-group"><label for="menu_name">메뉴 명</label><input type="text" id="menu_name" name="menu_name" class="form-control" value="'+menu.menu_name+'" placeholder="메뉴명"><span class="help-block"></span></div>'
				output+='<div class="form-group"><label for="menu_url">메뉴 주소</label> <input type="text" id="menu_url" name="menu_url" class="form-control" value="'+menu.menu_url+'" placeholder="/main/main.do"><span class="help-block"></span></div>'
				output+='<div class="form-group"><label for="menu_order">메뉴 순서</label> <input type="number" id="menu_order" name="menu_order" class="form-control" value="'+menu.menu_order+'" placeholder="0"><span class="help-block"></span></div>'
				output+='<div class="form-group"><label for="menu_dd">드롭다운 메뉴 사용</label><br>';
				if(menu.menu_dropdown=='N'){
					output+='<input type="radio" name="menu_dd" id="menu_dd1" value="Y"> 사용 <input type="radio" name="menu_dd" id="menu_dd2" value="N" checked="checked"> 미사용</div>';	
				}else{
					output+='<input type="radio" name="menu_use" id="menu_use1" value="Y" checked="checked"> 표시 <input type="radio" name="menu_use" id="menu_use2" value="N" > 미표시 </div>';
				}
				output+='<div class="form-group"><label>상위 메뉴</label><select id="select_id2" name="parent_num" class="form-control"><option selected="selected" value=null disabled="disabled">-선택-</option></select></div>'
			},error:function(){
				alert('네트워크 에러!!');
			}
	    });
	}).trigger('change');  
	
	
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