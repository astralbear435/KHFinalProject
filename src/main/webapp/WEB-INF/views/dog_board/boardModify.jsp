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
    	width:700,
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
	<h3 align="center">글수정</h3>
	<br><br>
	<form:form commandName="boardCommand" action="update.do" id="register_form"
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
				<span>(${command.filename})메인이미지가 등록되어 있습니다.
				다시 업로드하면 기존 메인 이미지는 삭제됩니다.</span>
				</c:if>       
			</li>
			<li>
				<label for="an_name">강아지 이름</label>
				<form:input path="an_name" class="form-control" placeholder="이름을 입력해 하세요!"/>
				<form:errors path="an_name" 
				             cssClass="error-an_name"/>
			</li>
			<li>
				<label for="an_species">묘종/견종</label>
				<form:input path="an_species" class="form-control" placeholder="묘/견종을 입력하세요!"/>
				<form:errors path="an_species" cssClass="error-an_species"/>
			</li>
				<li>
				<label for="an_color">모색</label>
				<form:input path="an_color" class="form-control" placeholder="유기견/묘 의 컬러을 입력하세요!"/>
				<form:errors path="an_color" cssClass="error-an_color"/>
			</li>
			<li>
				<label for="an_gender">성별</label>
				<select name="an_gender" class="form-control">
				<option type="checkbox" value="남아">남아</option>
				<option type="checkbox" value="여아">여아</option>
				</select>
			</li>
			<li>
				<label for="an_operate">중성화여부</label>
				<select name="an_operate" class="form-control">
				<option type="checkbox" value="완료">완료</option>
				<option type="checkbox" value="미완료">미완료</option>
				</select>
			</li>
			<li>
				<label for="an_review">접종여부</label>
				<select name="an_review" class="form-control">
				<option type="checkbox" value="접종완료">접종완료</option>
				<option type="checkbox" value="접종미완료">접종미완료</option>
				</select>
			</li>
			<li>
				<label for="an_content">내용</label>
				<form:textarea path="an_content"/>
				<form:errors path="an_content"  cssClass="error-an_content"/>
			</li>
			
		</ul>
		<div align="center">
			<input type="submit" value="전송" class="btn btn-outline-info">
			<input type="button" value="목록"
			      onclick="location.href='list.do'" class="btn btn-outline-info">
		</div>
	</form:form>
</div>







