$(document).ready(function() {
	
	var id,e_id;
	
	$('#m_id').keyup(function() {
		
		id = $('#m_id').val();
		
		if(id.indexOf('@') != -1) {
			
			var output = '<input id="m_email" name="m_email" type="email" value="' + id + '"> ';
			
			$('#eid').children('input').remove();	
			$('#eid').append(output);
		}
		 
	});
	
	$('#m_id,errors').text('');
	
	$('#m_email').keyup(function() {
		
		e_id = $('#m_email').val();
		
		if(e_id.indexOf('@') == -1){
			
			var output = '<input id="m_id" name="m_id" type="text" value="' + id + '"> ';
			
			$('#eid').children('input').remove();	
			$('#eid').append(output);
		}
	});
	
});