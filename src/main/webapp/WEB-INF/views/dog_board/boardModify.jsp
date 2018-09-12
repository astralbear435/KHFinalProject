<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
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
<div class="page-main-style">
	<h1>글수정</h1>
	<form:form commandName="command" action="update.do"
	    id="register_form"
	    enctype="multipart/form-data">
	    <form:hidden path="id"/>
	    <form:hidden path="num"/>
	    <form:hidden path="filename"/>
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>  
				<label for="upload">메인이미지</label>
				<input type="file" name="upload" 
				       id="upload"/>
				<c:if test="${!empty command.filename}">
				<br>
				<span>(${command.filename})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>       
			</li>
			<li>
				<label for="an_name">강아지 이름</label>
				<form:input path="an_name"/>
				<form:errors path="an_name" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="species">견종</label>
				<form:input path="species"/>
				<form:errors path="species" 
				             cssClass="error-color"/>
			</li>
				<li>
				<label for="color">색상</label>
				<form:textarea path="color"/>
				<form:errors path="color" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="gender">성별</label>
				<select name="gender">
				<option type="checkbox" value="수컷">수컷</option>
				<option type="checkbox" value="암컷">암컷</option>
				</select>
			</li>
			<li>
				<label for="operate">중성화여부</label>
				<select name="operate">
				<option type="checkbox" value="완료">완료</option>
				<option type="checkbox" value="미완료">미완료</option>
				</select>
			</li>
			<li>
				<label for="review">접종여부</label>
				<select name="operate">
				<option type="checkbox" value="접종완료">접종완료</option>
				<option type="checkbox" value="접종미완료">접종미완료</option>
				</select>
			</li>
			<li>
				<label for="an_content">내용</label>
				<form:textarea path="an_content"/>
				<form:errors path="an_content" 
				             cssClass="error-color"/>
			</li>
			
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록"
			      onclick="location.href='list.do'">
		</div>
	</form:form>
</div>







