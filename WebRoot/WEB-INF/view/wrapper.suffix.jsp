<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${!isAjax}">
            	<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
		    </section>
	        <!-- <aside class="bg-light lter b-l aside-md hide" id="notes">
	            <div class="wrapper">Notification</div>
	        </aside> -->
        </section>
    </section>
    <!-- left start-->
</section>

<!--[if lt IE 9]>
<script src="${STATIC_URL}/plugins/ie/html5shiv.js" cache="false"></script>
<script src="${STATIC_URL}/plugins/ie/respond.min.js" cache="false"></script>
<script src="${STATIC_URL}/plugins/ie/excanvas.js" cache="false"></script>
<![endif]-->
<script src="${STATIC_URL}/plugins/zeed.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-form/3.03/jquery.form.js"></script>
<script src="${STATIC_URL}/plugins/jquery-pjax/jquery.pjax.js"></script>
<script src="${STATIC_URL}/plugins/charts/sparkline/jquery.sparkline.min.js"></script>

<!-- Bootstrap -->
<script src="${STATIC_URL}/plugins/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/bootstrap/3.1.0/js/bootstrap-multiselect.js"></script>

<!-- App -->
<script src="${STATIC_URL}/plugins/app.js"></script>
<script src="${STATIC_URL}/plugins/app.plugin.js"></script>
<script src="${STATIC_URL}/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<script src="${STATIC_URL}/plugins/fuelux/fuelux.js" cache="false"></script>

<!-- PNotify -->
<script src="${STATIC_URL}/plugins/jquery-pnotify/pnotify.custom.min.js" cache="false"></script>

<!-- highcharts -->
<script type="text/javascript" src="${STATIC_URL}/plugins/highcharts4.1.9/highcharts.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/highcharts4.1.9/modules/exporting.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/highcharts4.1.9/highcharts-3d.js"></script>

<!-- bootbox -->
<script type="text/javascript" src="${STATIC_URL}/plugins/bootbox/bootbox.js"></script>

<script src="${STATIC_URL}/js/utils.js"></script>
<script src="${STATIC_URL}/js/common.js"></script>
</c:if>
<script type="text/javascript">
 var OPERATE='${OPERATE}';
 	if(OPERATE==3){
		$('input').attr('disabled','disabled');
		$('select').attr('disabled','disabled');
    }else if(OPERATE==4){
		$('input').attr('disabled','disabled');
		$('select').attr('disabled','disabled');
		$('.bmpms-through').removeClass('hide');
		$('.bmpms-rejected').removeClass('hide');
    }else if(OPERATE==2){
    	$('input').attr('disabled','disabled');
		$('select').attr('disabled','disabled');
    	$('.bmpms-save').removeClass('hide');
    }else{
    	$('.bmpms-save').removeClass('hide');
    }
    
</script>