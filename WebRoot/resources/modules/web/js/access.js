jQuery(function($) {
	initNyr();
	getData();
	
});
//初始化年月日
function initNyr(){
	 var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	 if (m >= 1 && m <= 9) {
         m = "0" + m;
     }
     if (d >= 0 && d <= 9) {
         d = "0" + d;
     }
	var ss =y+"-"+m+"-"+d;
	$("#nyr").val(ss);
};
function go2rfidinout(){
	  window.location.href='${contentpath}/login.do?openPage=acsInoutLog';
}
function getData(){
	//本日数据
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getDataForDay.do',
        dataType:'json',
        async:false,
        success:function(data){
    		var a=0;
    		var b=null;
		 	var c=null;
		 	var ss;
    		if(data.record.length>0){
    		 	 a =data.record[0].count;//总数
    		 	if(data.rk.length>0){
    		 		b =data.rk[0].count;//有效
    		 	}else {
    		 		b=0;
    		 	}
    		 	if(data.ck.length>0){
    		 		c =data.ck[0].count;//无效
    		 	}else {
    		 		c=0;
    		 	}
    		
    		 	ss="<b>"+a+"</b>次";
    		 	$("#ldaytext").html(ss);
    		 	$("#lday").jqChart({
    		 		 	noDataMessage:{
    	                    text: '今日无刷卡记录',
    	                    font: '20px sans-serif'
    	                },
    		 			title: { text: '刷卡统计'},
    	                legend: { title: { text: '状态分类' },
    		 			font: '15px sans-serif',
    	                },
    	                animation: { duration: 1 },
    	                tooltips: { //提示数据 
    	                	font: '20px sans-serif',
                            fillStyle: 'white'
    	    	        } , 
    	                series: [
    	                    {
    	                        type: 'pie',
    	                        labels: {
    	                            stringFormat: '%d次',
    	                            font: '20px sans-serif',
    	                            fillStyle: 'white'
    	                        },
    	                        data: [['有效', b], ['无效', c],]
    	                    }
    	                ]	
    		 	}
    		
    		 	);
    		}else{
    			ss="<b>"+a+"</b>次";
    		 	$("#ldaytext").html(ss);
    		 	$("#lday").jqChart({
    		                noDataMessage: {
    		                    text: '今日无刷卡记录',
    		                    font: '20px sans-serif'
    		                },
    		                series: [
    		                    {
    		                        type: 'column'
    		                    }
    		                ]
    		            });
    		}
    		
        },error:function(a,b,c){
        	//alert(c);
        }
    });
	
	//本月数据
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getDataForMonth.do',
        dataType:'json',
        async:false,
        success:function(data){
        	var a=0;
        	var b=0;
		 	var c=0 ;
		 	var ss;
        	if(data.record.length>0){
        		a =data.record[0].count;//总数
    		 	if(data.rk.length>0){
    		 		b =data.rk[0].count;//有效
    		 	}else {
    		 		b=0;
    		 	}
    		 	if(data.ck.length>0){
    		 		c =data.ck[0].count;//无效
    		 	}else {
    		 		c=0;
    		 	}
        	
    			ss=a;
    		 	$("#lmontext").html(ss);
    		 	$("#lmon").jqChart({
    		 		noDataMessage: {
	                    text: '本月无刷卡记录',
	                    font: '20px sans-serif'
	                },
    		 		 animation: { duration: 1 },
 	                shadows: {
 	                    enabled: true
 	                },
 	                axes: [
							{
   							 location: 'bottom',
  							 minimum: 0,
   							 maximum: (b>c)?c:b,
							}
 	                       ],
 	                series: [
 	                    
 	                    {
 	                        type: 'bar',
 	                        labels: {
 	                            stringFormat: '%d次',
 	                            font: '15px sans-serif',
 	                            fillStyle: 'blue'
 	                        },
 	                        title: '无效',
 	                        fillStyle: '#FCB441',
 	                        data: [['', parseInt(c)]]
 	                    },
 	                    {
 	                        type: 'bar',
 	                        labels: {
 	                            stringFormat: '%d次',
 	                            font: '15px sans-serif',
 	                            fillStyle: 'blue'
 	                        },
 	                        title: '有效',
 	                        fillStyle: '#418CF0',
 	                        data: [['', b]]
 	                    },
 	                ]
    	            });
        	}else{
        		ss="<b>"+a+"</b>";
    		 	$("#lmontext").html(ss);
    		 	$("#lmon").jqChart({
    		                noDataMessage: {
    		                    text: '本月无刷卡记录',
    		                    font: '20px sans-serif'
    		                },
    		                series: [
    		                    {
    		                        type: 'column'
    		                    }
    		                ]
    		            });
    		}
        		
        },error:function(a,b,c){//alert(c);
        }
    });
	
	//本年数据
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getDataForYear.do',
        dataType:'json',
        async:false,
        success:function(data){
        	var a=0;
        	var b=0;
        	var c=0;
        	if(data.record.length>0){
        	 	 a =data.record[0].count;
    		 	if(data.rk.length>0){
    		 		b =data.rk[0].count;//入库
    		 	}else {
    		 		b=0;
    		 	}
    		 	if(data.ck.length>0){
    		 		c=data.ck[0].count;//出库
    		 	}else {
    		 		c=0;
    		 	} 
        	
    		 	ss=a;
    		 	$("#lyeatext").html(ss);
    		 	$("#lyea").jqChart({
    		 			noDataMessage: {
	                   		text: '本年无刷卡记录',
	                    	font: '20px sans-serif'
	                	},
    	                animation: { duration: 1 },
    	                shadows: {
    	                    enabled: true
    	                },
    	                axes: [
   							{
      							 location: 'bottom',
     							 minimum: 0,
      							 maximum: (b>c)?b:c,
   							}
    	                       ],
    	                series: [
    	                    
    	                    {
    	                        type: 'bar',
    	                        labels: {
    	                            stringFormat: '%d次',
    	                            font: '15px sans-serif',
    	                            fillStyle: 'blue'
    	                        },
    	                        title: '无效',
    	                        fillStyle: '#FCB441',
    	                        data: [['', parseInt(c)]]
    	                    },
    	                    {
    	                        type: 'bar',
    	                        labels: {
    	                            stringFormat: '%d次',
    	                            font: '15px sans-serif',
    	                            fillStyle: 'blue'
    	                        },
    	                        title: '有效',
    	                        fillStyle: '#418CF0',
    	                        data: [['', b]]
    	                    },
    	                ]
    	            });
        	}else{
        		ss="<b>"+a+"</b>";
    		 	$("#lyeatext").html(ss);
    		 	$("#lyea").jqChart({
    		                noDataMessage: {
    		                    text: '本年无刷卡记录',
    		                    font: '20px sans-serif'
    		                },
    		                series: [
    		                    {
    		                        type: 'column'
    		                    }
    		                ]
    		            });
        	}
        },error:function(a,b,c){
        	
        }
    });
	
	//默认第一次分页查询
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getPrereturnData.do',
        dataType:'json',
        data:{"date":$("#nyr").val()},
        async:false,
        success:function(data){
        	//console.log(data);
        	$("#showTable tr").remove();
        	var totalCount =0;
        	var ss="<tr><th>类型</th><th>设备名称</th><th>人员姓名</th><th>卡号</th><th>刷卡时间</th><th>部门名称</th> <th>事件说明</th></tr>";
        	if(data.rows.length>0){
        		var type="";
        		for(var i=0;i<10&&i<data.rows.length;i++){
        			if(data.rows[i].type==='1'){
        				type='门禁';
            		}else if(data.rows[i].type==='2'){
            			type='进场';
            		}else if(data.rows[i].type==='3'){
            			type='出场';
            		}else if(data.rows[i].type==='4'){
            			type='电梯';
            		}else{
            			type="";
            		}
        			ss+=" <tr><td>"+type+"</td><td>"+data.rows[i].deviceName+"</td><td>"+data.rows[i].inoutUser+"</td><td>"+data.rows[i].cardId+"</td><td>"+data.rows[i].inoutDate+"</td>";
        			ss+="<td>"+data.rows[i].department+"</td><td>"+data.rows[i].opendoorType+"</td></tr>";
        		}
        		$("#showTable").html(ss);
        		 totalCount=data.rows.length;
            	if (data.rows.length%10==0) {
    				 totalPage =data.rows.length/10;
    			}else {
    				totalPage=parseInt(data.rows.length/10)+1;
    			}
            	$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val("1");
        	}else{
        		$("#showTable").html(ss);
        		$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val("1");
        	}
        },error:function(a,b,c){
        	//alert(c);
        }
    });

	
	//上一页
	$(".arc_b_prev").click(function(){
		prePageAction();
	});
	//下一页
	$(".arc_b_next").click(function(){
		nextPageAction();
	});
	//首页
	$("#firstpage").click(function(){
		sy();
	});
	//尾页
	$("#lastpage").click(function(){
		wy();
	});
	
	
	//分页框回车事件
	$("#curPage").bind('keypress',function(event){
        if(event.keyCode == "13"){
            curPageEnter();
        }
    });
	
}

function searchData(){
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getPrereturnData.do',
        dataType:'json',
        data:{"date":$("#nyr").val()},
        async:false,
        success:function(data){
        	//console.log(data);
        	$("#showTable tr").remove();
        	var totalCount =0;
        	var ss="<tr><th>类型</th><th>设备名称</th><th>人员姓名</th><th>卡号</th><th>刷卡时间</th><th>部门名称</th> <th>事件说明</th></tr>";
        	if(data.rows.length>0){
        		var type="";
        		for(var i=0;i<10&&i<data.rows.length;i++){
        			if(data.rows[i].type==='1'){
        				type='门禁';
            		}else if(data.rows[i].type==='2'){
            			type='进场';
            		}else if(data.rows[i].type==='3'){
            			type='出场';
            		}else if(data.rows[i].type==='4'){
            			type='电梯';
            		}else{
            			type="";
            		}
        			ss+=" <tr><td>"+type+"</td><td>"+data.rows[i].deviceName+"</td><td>"+data.rows[i].inoutUser+"</td><td>"+data.rows[i].cardId+"</td><td>"+data.rows[i].inoutDate+"</td>";
        			ss+="<td>"+data.rows[i].department+"</td><td>"+data.rows[i].opendoorType+"</td></tr>";
        		}
        		$("#showTable").html(ss);
        		 totalCount=data.rows.length;
            	if (data.rows.length%10==0) {
    				 totalPage =data.rows.length/10;
    			}else {
    				totalPage=parseInt(data.rows.length/10)+1;
    			}
            	$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val("1");
        	}else{
        		$("#showTable").html(ss);
        		$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val("1");
        	}
        },error:function(a,b,c){
        	//alert(c);
        }
    });
}


var currentPage=1;//当前页
var totalPage=1;//总页数
//获取分页数据
function getPageData(str){
	$.ajax({
        type:'POST',
        url:'${contentpath}/front/acs/getPrereturnData.do',
        dataType:'json',
        data:{"date":$("#nyr").val()},
        async:false,
        success:function(data){
        	//console.log(data);
        	$("#showTable tr").remove();
        	var totalCount =0;
        	var ss="<tr><th>类型</th><th>设备名称</th><th>人员姓名</th><th>卡号</th><th>刷卡时间</th><th>部门名称</th> <th>事件说明</th></tr>";
        	if(data.rows.length>0){
        		var type="";
        		for(var i=str;i<(str+10)&&i<data.rows.length;i++){
        			if(data.rows[i].type==='1'){
        				type='门禁';
            		}else if(data.rows[i].type==='2'){
            			type='进场';
            		}else if(data.rows[i].type==='3'){
            			type='出场';
            		}else if(data.rows[i].type==='4'){
            			type='电梯';
            		}else{
            			type="";
            		}
        			ss+=" <tr><td>"+type+"</td><td>"+data.rows[i].deviceName+"</td><td>"+data.rows[i].inoutUser+"</td><td>"+data.rows[i].cardId+"</td><td>"+data.rows[i].inoutDate+"</td>";
        			ss+="<td>"+data.rows[i].department+"</td><td>"+data.rows[i].opendoorType+"</td></tr>";
        		}
        		$("#showTable").html(ss);
        		 totalCount=data.rows.length;
            	if (data.rows.length%10==0) {
    				 totalPage =data.rows.length/10;
    			}else {
    				totalPage=parseInt(data.rows.length/10)+1;
    			}
            	$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val(currentPage);
        	}else{
        		$("#showTable").html(ss);
        		$("#totlePageCount").html("共计"+totalPage+"页&nbsp "+totalCount+"条数据");
            	$("#nowpage").val(currentPage);
        	}

        },error:function(a,b,c){
        	//alert(c);
        }
    });
}
//上一页
function prePageAction(){
	var curpage = parseInt($("#nowpage").val());
	if(isNaN(curpage)){
		alert("请输入数字");
		return;
	}
	if(curpage <= 1){
		return;
	}
	if(curpage > totalPage){
		alert("超过总页数");
		return;
	}
	$("#nowpage").val(curpage - 1);
	currentPage=curpage-1;
	var i = (currentPage-1)*10;
	getPageData(i);
}

//下一页
function nextPageAction(){
	var curpage = parseInt($("#nowpage").val());
	if(isNaN(curpage)){
		alert("请输入数字");
		return;
	}
	if(curpage >= totalPage||curpage<0){
		alert("超过总页数或页数为负");
		return;
	}
	$("#nowpage").val(curpage + 1);
	currentPage=curpage+1;
	var i = curpage*10;
	getPageData(i);
}

//首页
function sy(){
	if(currentPage==1){
		return;
	}else {
		currentPage=1;
		$("#nowpage").val(1);
		getPageData(0);
	}
}
//尾页
function wy(){
	if(currentPage==totalPage){
		return;
	}else {
		currentPage=totalPage;
		$("#nowpage").val(totalPage);
		var i = (currentPage-1)*10;
		getPageData(i);
	}
}

//回车
function curPageEnter(){
	var curpage = parseInt($("#nowpage").val());
	if(isNaN(curpage)){
		alert("请输入数字");
		return;
	}
	if(curpage > totalPage || curpage < 1){
		alert("输入的页码不正确");
		return;
	}
	currentPage=curpage;
	
	getPageData();
}

