$(document).ready(function(){

	   $(".tab_content").hide();
	    $(".tab_content:first").show();

	    $("ul.nav-tabs li").click(function(){
	    	var keyfield="as_location";
	    	var keyword=$(this).attr('data-name');
	    	 $("ul.nav-tabs li a").removeClass("active");
	       $(this).children('.nav-link').addClass("active");
	        $(".tab_content").hide()
	        var activeTab = $(this).attr("rel");
	        $("#" + activeTab).fadeIn();
	        
	        $.ajax({
				   type:'post',
			   	   data:{keyfield:keyfield,keyword:keyword},
			   	   url:'listTap.do',
			   	dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					readTap(data.as_list,data.goodslist);
				},error:function(){
			      	alert('새로고침 부탁드립니다.');
	           	}
			});
	    });	 
	    //탭 안의 읽어오기
	    function readTap(as_list,goodslist){
	    	alert(as_list);
	    	alert(goodslist);
	    }
});