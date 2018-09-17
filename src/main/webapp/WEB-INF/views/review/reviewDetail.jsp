<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<div class="container">
<br><br>
<h4> REVIEW / ${review.re_asname}</h4>
<div style="text-align:center;">
<br><br>
<h1>${review.re_title}</h1>
<p style="float:right;">��ȸ�� : ${review.re_hit}</p>
<br><hr><br>
<span>${review.re_content}</span>
<br><br><hr>

<c:if test="${user_id.equals(review.re_id)}">
<div  style="float:right;">
<button class="btn">�����ϱ�</button>
<button class="btn btn-danger">�����ϱ�</button>
</div></c:if>
<div style="float:left;color:gray">�ۼ���: ${review.re_id} / �ۼ��� : ${review.re_date} </div>
<br><br>
</div>
<c:if test="${user_id==null}">
	<div id="replyNo">
	<textarea class="form-control disable" placeholder="�α��� �� �̿��� �ֽʽÿ�." style="width:80%;display:inline-block"></textarea>
	<button class="btn">SAND</button>
	</div>
</c:if>
<c:if test="${user_id!=null}">
	<div id="reply" style="width:100%; text-align:center;">
	<textarea class="form-control disable" style="width:80%;display:inline-block;margin-top: 3%; padding-top: 2%;"></textarea>
		<span style="cursor: pointer; border: 3px solid black;padding: 2% 2% 2% 2%;margin-bottom: 1%; display: inline-block;">SAND</span>
	</div>
</c:if>
<hr>
</div>