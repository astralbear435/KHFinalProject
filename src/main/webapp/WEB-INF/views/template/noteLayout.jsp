<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<title><tiles:getAsString name="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Njoy Trip Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
<!--// css -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">
<style>
	#note_menu{
		float:left;
		width:22%;
		min-height:400px;
	}
	
	#note_body{
		float:left;
		width:1140px;
		padding:5px;
	}
</style>

<!-- //font-awesome icons -->
<!-- font -->
<!-- <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Rokkitt:100,200,300,400,500,600,700,800,900" rel="stylesheet"> -->

<!-- //font -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<!-- menu -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<!-- //menu -->
</head>
<body>
<div id="main">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="main_menu">
		<tiles:insertAttribute name="menu"/>
	</div>
	<div class="clearB" style="height: 700px;">
		<div id="note_menu">
			<tiles:insertAttribute name="noteMenu"/>
		</div>
		<div id="note_body">
			<tiles:insertAttribute name="body"/>
		</div>
	</div>
	<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/responsiveslides.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jarallax.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
        /* init Jarallax */
        $('.jarallax').jarallax({
            speed: 0.5,
            imgWidth: 1366,
            imgHeight: 768
        })
    </script>
	<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
	</script>
</body>
</html>












