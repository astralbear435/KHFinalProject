<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#re_content').summernote({
       height:500,
       fontNames : ['맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',],
      fontNamesIgnoreCheck : [ '맑은고딕' ],
      focus: true,
      callbacks: {
            onImageUpload: function(files, editor, welEditable){
               for (var i = files.length - 1; i >= 0; i--) {
                  sendFile(files[i], this);
               }
           }
      }    
    });  
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

</script>

<div class="container">
<p/><p/><br><br>

	<div style="width:80%; margin-left:10%">
	<h1 style="color:pink;text-align:center;">♡후원 인증 글♡</h1>
	<p style="text-align:center;">:인증글은 후원자님과의 신뢰감을 높여줍니다. 수시로 업데이트 해주세요^^!</p>
	<br><br>
	<form:form commandName="review" action="shelterReview.do" enctype="multipart/form-data">
	<form:hidden path="re_id"/>
	<form:hidden path="re_auth"/>
	<form:hidden path="re_asname"/>
		<form:errors element="div" cssClass="error-color"/>	
	    <ul style="list-style:none;">
			<li style="text-align:center;" > 제목: 
				<form:input path="re_title" class="form-control" style="width:80%"/>
				<form:errors path="re_title" cssClass="error-color"/>
			</li>
			<li>
			<br><br>
				<form:textarea path="re_content"/>
				<form:errors path="re_content" cssClass="error-re_content"/>
			</li>						
		</ul><br><br>
		<div style="text-align:center;">
			<input type="button" value="취소" class="btn" onclick="history.go(-1)">
			<input class="btn btn-warning" type="submit" value="작성">
		</div>
	</form:form>
<br><br>
</div>
</div>