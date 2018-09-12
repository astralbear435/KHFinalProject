<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

alert("관리자 아이디로만 로그인 가능합니다.");
location.href="${pageContext.request.contextPath}/admin/login.do" 
</script>