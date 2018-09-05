$(document).ready(function() {
    var background = {
            type: 'linearGradient',
            x0: 0,
            y0: 0,
            x1: 0,
            y1: 1,
            colorStops: [{ offset: 0, color: '#d2e6c9' },
                         { offset: 1, color: 'white' }]
        };
   //获取库存基础数据
	Ajax.custom({
		url : config.getBaseNum,
		data : {

		},//传递参数
		type : 'GET'
	}, function(data) {
		if(data.data != null){
		$("#baseNum").html(
				template.render("baseNum-data", {
					baseData : data.data
				}));
		}
	}); 
	//获取馆藏量统计数据
	Ajax.custom({
		url : config.getAmsSatas,
		data : {
			
		},//传递参数
		type : 'GET'
	}, function(data) {
		var inflag = new Array();var security = new Array();var archivesType = new Array();var keepyear = new Array();var wmsStore = new Array();
		if(data.data != null){
			if(data.data.inflag!=null){//在库状态
					var list=new Array(0,0);
					list[0]="在库"
						if(data.data.inflag.atstore!=null)list[1]=data.data.inflag.atstore
					inflag[0]=list
					var list1=new Array(0,0);
					list1[0]="离库"
						if(data.data.inflag.outstore!=null)list1[1]=data.data.inflag.outstore
					inflag[1]=list1
			}		
			if(data.data.security!=null){//保密等级
				var list2=new Array(0,0);
				list2[0]="普通"
					if(data.data.security.common!=null)list2[1]=data.data.security.common
				security[0]=list2
				var list3=new Array(0,0);
				list3[0]="秘密"
				if(data.data.security.cerecet!=null)list3[1]=data.data.security.cerecet
				security[1]=list3
				var list4=new Array(0,0);
				list4[0]="机密"
					if(list4[1]=data.data.security.confidential!=null)list4[1]=data.data.security.confidential
				security[2]=list4
				var list4=new Array(0,0);
				list4[0]="绝密"
					if(data.data.security.supersecret!=null)list4[1]=data.data.security.supersecret
				security[3]=list4
			}		
			if(data.data.archivesType.length>0){//档案类型
				for(var i = 0;i<data.data.archivesType.length;i++){
					var list5=new Array(0,0);
					list5[0]=data.data.archivesType[i].typeName
					list5[1]=data.data.archivesType[i].count
					archivesType[i]=list5
				}

			}		
			if(data.data.wmsStore.length>0){//库房占比
				for(var i = 0;i<data.data.wmsStore.length;i++){
					var lista=new Array(0,0);
					lista[0]=data.data.wmsStore[i].storeName
					lista[1]=data.data.wmsStore[i].count
					wmsStore[i]=lista
				}
				
			}		
			if(data.data.keepyear!=null){//保管年限
				var list6=new Array(0,0);
				list6[0]="永久"
					if(data.data.keepyear.permanent!=null)list6[1]=data.data.keepyear.permanent
				keepyear[0]=list6
				var list7=new Array(0,0);
				list7[0]="长期"
					if(data.data.keepyear.longTime!=null)list7[1]=data.data.keepyear.longTime
				keepyear[1]=list7
				var list8=new Array(0,0);
				list8[0]="短期"
					if(data.data.keepyear.shortTime!=null)list8[1]=data.data.keepyear.shortTime
				keepyear[2]=list8
				var list9=new Array(0,0);
				list9[0]="10年"
					if(data.data.keepyear.tenYears!=null)list9[1]=data.data.keepyear.tenYears
				keepyear[3]=list9
				var list0=new Array(0,0);
				list0[0]="30年"
					if(data.data.keepyear.thirtyYears!=null)list0[1]=data.data.keepyear.thirtyYears
				keepyear[4]=list0
			}		
		}
		//组卷方式
		$('#archivesType').jqChart({
			noDataMessage: {
                text: '无数据',
                font: '20px sans-serif'
            },
            /*title: { text: 'Pie Chart Labels' },*//*图标上方标题*/
            legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
            	location: 'bottom',
            	font: '10px sans-serif',},
            	animation: { duration: 1 },/*图片动画*/
                border: { strokeStyle: '#6ba851' },
                background: background,
                shadows: {
                    enabled: true
                },
            series: [{ type: 'pie',
	                   labels: {
	                            stringFormat: '%.1f%%',
	                            valueType: 'percentage',
	                            font: '18px sans-serif',
	                            fillStyle: 'black'
	                        },
	                    data: archivesType,
	                    labelsPosition: 'outside', // inside, outside 文字位置
	                    labelsAlign: 'circle', // circle, column 数字位置
	                    labelsExtend: 10,//线长度
	                    leaderLineWidth: 2,//线粗细
	                    leaderLineStrokeStyle: 'red' //线颜色
	                  }]
        });
		//密级
		$('#security').jqChart({
			noDataMessage: {
				text: '无数据',
				font: '20px sans-serif'
			},
			/*title: { text: 'Pie Chart Labels' },*//*图标上方标题*/
			legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
				location: 'bottom',
				font: '10px sans-serif',},
				animation: { duration: 1 },/*图片动画*/
				border: { strokeStyle: '#6ba851' },
				background: background,
				shadows: {
					enabled: true
				},
				series: [{ type: 'pie',
					labels: {
						stringFormat: '%.1f%%',
						valueType: 'percentage',
						font: '18px sans-serif',
						fillStyle: 'black'
					},
					data: security,
					labelsPosition: 'outside', // inside, outside 文字位置
					labelsAlign: 'circle', // circle, column 数字位置
					labelsExtend: 10,//线长度
					leaderLineWidth: 2,//线粗细
					leaderLineStrokeStyle: 'red' //线颜色
				}]
		});
		//保管年限
		$('#keepyear').jqChart({
			noDataMessage: {
				text: '无数据',
				font: '20px sans-serif'
			},
			/*title: { text: 'Pie Chart Labels' },*//*图标上方标题*/
			legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
				location: 'bottom',
				font: '10px sans-serif',},
				animation: { duration: 1 },/*图片动画*/
				border: { strokeStyle: '#6ba851' },
				background: background,
				shadows: {
					enabled: true
				},
				series: [{ type: 'pie',
					labels: {
						stringFormat: '%.1f%%',
						valueType: 'percentage',
						font: '18px sans-serif',
						fillStyle: 'black'
					},
					data: keepyear,
					labelsPosition: 'outside', // inside, outside 文字位置
					labelsAlign: 'circle', // circle, column 数字位置
					labelsExtend: 10,//线长度
					leaderLineWidth: 2,//线粗细
					leaderLineStrokeStyle: 'red' //线颜色
				}]
		});
		//在库状态
		$("#inflag").jqChart({
	 		noDataMessage: {
                text: '无记录',
                font: '20px sans-serif'
            },
	 		 animation: { duration: 1 },
             shadows: {
                 enabled: true
             },
             legend: {location: 'bottom',font: '10px sans-serif'},
             axes: [{location: 'bottom',}],
             series: [
                 {
                     type: 'bar',
                     labels: {
                         stringFormat: '%d件',
                         font: '18px sans-serif',
                         fillStyle: 'blue'
                     },
                     title: '在库状态',
                     fillStyle: '#FCB441',
                     data: inflag
                 }
             ]
            });
		//位置分布
		$("#wmsStore").jqChart({
			noDataMessage: {
				text: '无记录',
				font: '20px sans-serif'
			},
			animation: { duration: 1 },
			shadows: {
				enabled: true
			},
			legend: {location: 'bottom',font: '10px sans-serif'},
			axes: [{location: 'bottom',}],
			series: [
			         {
			        	 type: 'bar',
			        	 labels: {
			        		 stringFormat: '%d件',
			        		 font: '18px sans-serif',
			        		 fillStyle: 'blue'
			        	 },
			        	 title: '档案位置',
			        	 fillStyle: '#FCB441',
			        	 data: wmsStore
			         }
			         ]
		});
		
	}); 
	
	
});