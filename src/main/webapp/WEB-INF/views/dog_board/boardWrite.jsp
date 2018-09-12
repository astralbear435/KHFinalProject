<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#an_content').summernote({
    	height:200,
    	fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
		fontNamesIgnoreCheck : [ '맑은고딕' ],
		focus: true,
		callbacks: {
				onImageUpload: function(files, editor, welEditable) {
	            for (var i = files.length - 1; i >= 0; i--) {
	            	sendFile(files[i], this);
	            }
	        }
		} 	
    });  
    function sendFile(file, el) {
		var form_data = new FormData();
      	form_data.append('file', file);
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: './imageUploader.do',
        	cache: false,
        	contentType: false,
        	enctype: 'multipart/form-data',
        	processData: false,
        	success: function(img_name) {
          		$(el).summernote('editor.insertImage', img_name);
        	}
      	});
    }
});
</script>
<style>
ul{
	list-style:none;
}
</style>
<div class="page-main-style">
	<h1>글쓰기</h1>
	<form:form commandName="boardCommand" action="write.do" id="register_form" enctype="multipart/form-data">
	    <form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>
				<label for="upload">메인이미지</label>
				<input type="file" name="upload" 
				       id="upload"/>
			</li>
			<br>
			<li>
				<label for="an_name">강아지이름</label>
				<form:textarea path="an_name"/>
				<form:errors path="an_name" 
				             cssClass="error-an_name"/>
			</li>
			<br>
			<li>
				<label for="an_species">견종</label>
				<form:textarea path="an_species"/>
				<form:errors path="an_species"
				             cssClass="error-an_species"/>
			</li>
			<br>
			<li>
				<label for="an_color">색상</label>
				<form:textarea path="an_color"/>
				<form:errors path="an_color" 
				             cssClass="error-an_color"/>
			</li>
			<br>
			<li>
				<label for="an_gender">성별</label>
				<select name="an_gender">
				<option type="checkbox" value="수컷">수컷</option>
				<option type="checkbox" value="암컷">암컷</option>
				</select>
			</li>
			<br>
			<li>
				<label for="an_operate">중성화여부</label>
				<select name="an_operate">
				<option type="checkbox" value="완료">완료</option>
				<option type="checkbox" value="미완료">미완료</option>
				</select>
			</li>
			<br>
			<li>
				<label for="an_review">접종여부</label>
				<select name="an_review">
				<option type="checkbox" value="접종완료">접종완료</option>
				<option type="checkbox" value="접종미완료">접종미완료</option>
				</select>
			</li>
			<br>
			<li>
				<label for="an_content">내용</label>
				<form:textarea path="an_content"/>
				<form:errors path="an_content" 
				             cssClass="error-an_content"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록"
			      onclick="location.href='list.do'">
		</div>
	</form:form>
</div>







