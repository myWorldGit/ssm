<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="utf-8">
	<title>糊涂购-后台管理页面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/limu.css">
</head>
<body>
	<a  id="http" href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>"></a>
	<div class="container-wrap">
		<div class="index-page-left">
			<div class="admin-logo">
			
			</div>
			<ul>
				<li class="aside-li">
					<a href="${pageContext.request.contextPath }/admin/first">
					<span class="iconfont icon-shouye1">&nbsp;&nbsp;</span>
						首页
					</a>
				</li>
				<li class="aside-li">
					<a href="${pageContext.request.contextPath }/admin/commodiry">
					<span class="iconfont icon-shangping ">&nbsp;&nbsp;</span>
						商品信息
					</a>
			
				</li>
				<li class="aside-li">
					<a href="#"><span class="iconfont icon-lixifuji">&nbsp;&nbsp;</span>
						分期利率
					</a>
			
				</li>
				<li class="aside-li">
					<a href="#"><span class="iconfont icon-tui">&nbsp;&nbsp;</span>
						商品退货
					</a>
				
				</li>
				<li class="aside-li  aside-active"><a href="#"><span class="iconfont icon-shimingrenzheng">&nbsp;&nbsp;
				</span>立木征信</a></li>
				<li class="aside-li"><a href="#"><span class="iconfont icon-shezhi">&nbsp;&nbsp;
				</span>未开发的</a></li>
				<li class="aside-li">
					<a href="${pageContext.request.contextPath }/admin/settings">
						<span class="iconfont icon-shezhi">&nbsp;&nbsp;</span>
						界面设置
					</a>
				</li>
			</ul>
		
		</div>

		<div class="index-page-right">
			<div class="topbar-wrap">
				<div class="container">
						<a href="${pageContext.request.contextPath }/admin/first"><i class="iconfont icon-shouye1"></i>&nbsp;首页</a>&nbsp;/&nbsp;<span>&nbsp;&nbsp;立木征信&nbsp;</span>
				</div>
			</div>
			<div class="slection-wrap">
				<div class="container" id='application'>
					<modal-find :item='temp' :flag='isshow' :limu='limuinfo' @close-modal='closemodal'></modal-find>

					<h2 class="list-title">等待审核的列表 <a href="#" class="list-title-btn">刷新</a></h2>
					<hr>
					<ul class="list-tb" >
						<li class="list-tb-header">
							<div>用户名</div>
							<div>手机号码</div>
							
							<div>下款额度</div>
							<div>管理</div>
						</li>
						<li class="list-tb-slection"  v-for="(i,index) in list">
							<div v-text='i.user.username'></div>
							<div v-text='i.user.phone'></div>
							<div> <input  class="money" v-model='i.money' type="text"/>元</div>
							<div class="operativebtn" ><a class="find" href="#" @click.prevent='lookup(i)'>查看</a>
								<a @click.prevent='nopass(index,i)' class="nopass" href="#">不通过</a>
								<a @click.prevent='pass(index,i)' class="pass" href="#">通过</a></div>
							
						</li>
					
				

					</ul>
					<div class="page-btn">
						<a href="#">上一页</a>
						<a href="#">1</a>
						<a href="#">3</a>
						<a href="#">4</a>
						<a href="#">5</a>
						<a href="#">6</a>
						<a href="#">上一页</a>
					</div>
				</div>

			</div>
			<!--底部   -->
			<div class="footer-wrap">
				<div class="container" >
					

						<p class="footer-descritption">糊涂购后台管理页面&copy;2019</p>


				</div>

			</div>

			<template id='modal-find'>
				<div class="dialog-wrap" v-if="flag" >

					<div class="dialog-container"    >
						<h3 class="dialog-title">查看信息 <a href="#"  @click.prevent='close'>×</a></h3>
 						<div class="dialog-content">
 							
 				
 						
 							<div v-if='limu.taobao.code==0000'>
 								<h3 class="info-item-title" >淘宝基本信息</h3>
 								<div class="info-item-wrap clearfix" v-for='(lv1,key,index) in limu.taobao.data.basicInfo'>
									<span class="info-item-left" v-text="key"></span>
									<span class="info-item-right" v-text="lv1"></span>
								</div>
								<hr/>
								<h3 class="info-item-title" >淘宝地址信息</h3>
 								<div class="info-item-wrap clearfix" v-for='lv2 in limu.taobao.data.addresses'>
									<div>
									<div>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>名字</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv2.name}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>地址</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv2.address}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>手机</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv2.mobile}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>编码</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv2.zipCode}}</span><br>	
										<hr/>								
									</div>
									</div>
								</div>
								<hr/>
								<h3 class="info-item-title" >淘宝真实信息</h3>
								
 								<div class="info-item-wrap clearfix" v-for='(lv3,key,index) in limu.taobao.data.alipayInfo'>
									<div>
									<div>
										<span class="info-item-left" v-text="key"></span>
										<span class="info-item-right" v-text="lv3"></span>							
									</div>
									</div>
								</div>
								<h3 class="info-item-title" >淘宝订单信息</h3>
 								<div class="info-item-wrap clearfix" v-for='(lv4,index) in limu.taobao.data.orderDetails' >
										<div>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>订单编号</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv4.orderId}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>订单时间</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv4.orderTime}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>订单额度</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">{{lv4.orderAmt}}</span><br>
										&nbsp;&nbsp;&nbsp;&nbsp;<span>交易状态</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{lv4.orderStatus}}</span><br>	
										<hr/>								
										</div>
								</div>
								<hr>
 							</div>
 							<div v-if='limu.mobile.code==0000'>
 								<h3 class="info-item-title" >手机运营基本信息</h3>
 								<div class="mrgl-20" >
	 								<span>真实姓名：</span><span v-text='limu.mobile.data.realName'></span>	
								</div>
								<div class="mrgl-20" v-for="(b2,key,index) in limu.mobile.data.basicInfo">
	 								{{key}}：<span v-text='b2'></span>	
								</div>
								
	 							
 							
 								<h3 class="info-item-title" >手机运营来电信息</h3>
 								<div v-for='b1 in limu.mobile.data.callRecordInfo'>
 								<div>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>来电地址</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.callAddress}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>通话时间</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.callDateTime}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>通话时长</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.callTimeLength}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>来电号码</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.mobileNo}}</span><br>	
									&nbsp;&nbsp;&nbsp;&nbsp;<span>呼叫类型</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.callType}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>呼叫费用</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b1.cost}}</span><br>	
									<hr/>								
								</div>	
								</div>
								
									<h3 class="info-item-title" >手机运营通话次数统计</h3>
 								<div v-for='b3 in limu.mobile.data.stati'>
 								<div>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>手机号</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.mobileNo}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>通话次数</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.callCount}}</span><br>
									<hr/>								
								</div>	
								</div>
								
								<h3 class="info-item-title" >手机运营通账单</h3>
 								<div v-for='b3 in limu.mobile.data.bill'>
 								<div>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>手机号</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.mobileNo}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>开始时间</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.startTime}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>月租</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.comboCost}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>总费用</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.sumCost}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>实际费用</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.realCost}}</span><br>
									<hr/>								
								</div>	
								</div>
								
								
								<h3 class="info-item-title" >手机运营短信内容</h3>
 								<div v-for='b3 in limu.mobile.data.smsInfo'>
 								<div>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>手机号码</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.mobileNo}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>发送号码</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.sendSmsToTelCode}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>发送地址</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.sendSmsAddress}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>发送时间</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.sendSmsTime}}</span><br>
									&nbsp;&nbsp;&nbsp;&nbsp;<span>发送类型</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{b3.sendType}}</span><br>
									<hr/>								
								</div>	
								</div>
								
								
								<h3 class="info-item-title" >用户画像分析</h3>
 								<div >
 								<span v-for='b in limu.mobile.data.analysisInfo'></span>
								</div>
								
								
								
								
 							</div>
 								
							
						</div>
						<div class="dialog-btn">
							<a href="#" @click.prevent='close'>确认</a>
							<a href="#" @click.prevent='close'>关闭</a>
						</div>
					</div>
				</div>
			</template>

		</div>	
	</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/axios.min.js"></script>
<script >
$(function(){
Vue.component('modal-find',{
	template:'#modal-find',
	props:['flag','item','limu'],
	data:function(){
		return{
		}
	},
	methods:{
		close:function(){
			this.$emit("close-modal");
		}
	},
	mounted:function(){
	
	}
})

	new Vue({
		el:'#application',
		data:{
			list:[],
			isshow:false,
			http:'',
			temp:{},
			limuinfo:{taobao:{code:'',data:[]},mobile:{code:'',data:[]}}
		},
		methods:{
			closemodal:function(){
				this.isshow=false;
			},
			lookup:function(i){
				this.isshow=true;
				  
				var params = new FormData();
				params.append('alitoken',i.alitoken);
				params.append('banktoken',i.banktoken);
				params.append('phonetoken',i.phonetoken);
				params.append('lid',i.lid);
				axios.post(this.http+'/limutoken/getlimuInfo',params)
				.then((response) => {   
					if(response.data.code==1){  
						var taobao = JSON.parse(response.data.data.taobao);
						var mobile = JSON.parse(response.data.data.mobile)
						this.limuinfo.taobao.code = taobao.code;
 						this.limuinfo.taobao.data = taobao.data;
 						this.limuinfo.mobile.code = mobile.code;
 						this.limuinfo.mobile.data = mobile.data;
 						
 						console.log(mobile.data.businessInfo);
						console.log(mobile.data.analysisInfo);
					}
				}).catch(function (error) {
				    console.log(error);
				});	
			
			},
			nopass:function(index,i){
				this.list.splice(index,1)
				//2审核失败
				alert(i)
			},
			pass:function(index,i){
				this.list.splice(index,1)
				//3审核通过    （1 已下款    2审核失败  退款 3 通过
				alert(i.money)
			}
		},
		watch:{

		},
		mounted:function(){
			this.http=document.getElementById('http').href
			axios.post(this.http+'/limutoken/ispass')
    		.then((response) => {
				if(response.data.code==1){
	    			this.list=response.data.data.items
				}
			}).catch(function (error) {
			    console.log(error);
			});
		}
	})



})


</script>

</html>