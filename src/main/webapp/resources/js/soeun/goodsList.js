$(document).ready(function(){

	   $(".tab_content").hide();
	    $(".tab_content:first").show();

	    $("ul.nav-tabs li").click(function(){
	    	var keyword=$(this).attr('data-name');
	    	 $("ul.nav-tabs li a").removeClass("active");
	  
	       $(this).children('.nav-link').addClass("active") .css({"color": "green"});
	     
	        $(".tab_content").hide()
	        var activeTab = $(this).attr("rel");
	        $("#" + activeTab).fadeIn();
	    });
	  
});