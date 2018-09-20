<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#an_content').summernote({
    	height:800,
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
	align:center;
}
</style>
<div class="container">
	<br><br>
	<h3 align="center">(보호소) 동물 등록페이지</h3>
	<form:form commandName="boardCommand" action="write.do" id="register_form" enctype="multipart/form-data">
	    <form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>	
		<br><br>
		<ul>
			<li>
				<label for="upload">메인이미지</label>
				<input type="file" name="upload" 
				       id="upload"/>
			</li>
			<br>
			<li>
				<label for="an_name">강아지이름</label>
				<form:input  path="an_name" class="form-control" placeholder="이름을 입력해 주세요!"/>
				<form:errors path="an_name" 
				             cssClass="error-an_name"/>
			</li>
			<br>
			<li>
				<label for="an_species">묘종/견종</label>
				<form:input  path="an_species" class="form-control" placeholder="묘/견종을 입력해주세요!"/>
				<form:errors path="an_species"
				             cssClass="error-an_species"/>
			</li>
			<br>
			<li>
				<label for="an_color">모색</label>
				<form:input  path="an_color" class="form-control" placeholder="유기견/묘 의  모색을 입력해주세요!"/>
				<form:errors path="an_color" 
				             cssClass="error-an_color"/>
			</li>
			<br>
			<li>
				<label for="an_gender">성별</label>
				<select name="an_gender" class="form-control">
				<option type="checkbox" value="남아">남아</option>
				<option type="checkbox" value="여아">여아</option>
				</select>
			</li>
			<br>
			<li>
				<label for="an_operate">중성화여부</label>
				<select name="an_operate" class="form-control">
				<option type="checkbox" value="완료">완료</option>
				<option type="checkbox" value="미완료">미완료</option>
				</select>
			</li>
			<br>
			<li>
				<label for="an_review">접종여부</label>
				<select name="an_review" class="form-control">
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
		<div align="center">
			<input type="submit" value="전송" class="btn btn-warning">
			<input type="button" value="목록"
			      onclick="location.href='list.do'" class="btn btn-primary">
		</div>
	</form:form>
</div>







