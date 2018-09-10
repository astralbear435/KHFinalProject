<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>이메일 인증</title>
	</head>
	<body>
		<script type="text/javascript">
			var userEmail = '${m_email}';
			
			alert(userEmail + '님은 정회원으로 변경되었습니다.');
			
			window.open('', '_self', ''); // 브라우저창 닫기
			
			self.location = '/Final/main/main.do';
		</script>
	</body>
</html>