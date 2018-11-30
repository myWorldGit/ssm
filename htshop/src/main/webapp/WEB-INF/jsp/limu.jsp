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
	<a id="http" href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>"/>
	<div class="container-wrap">
		<div class="index-page-left">
			<div class="admin-logo">
			
			</div>
			<ul>
				<li class="aside-li">
					<a href="#">
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
						<a href="#"><i class="iconfont icon-shouye1"></i>&nbsp;首页</a>&nbsp;/&nbsp;<span>&nbsp;&nbsp;立木征信&nbsp;</span>
				</div>
			</div>
			<div class="slection-wrap">
				<div class="container" id='application'>
					<modal-find :item='temp' :flag='isshow' @close-modal='closemodal'></modal-find>

					<h2 class="list-title">等待审核的列表 <a href="#" class="list-title-btn">刷新</a></h2>
					<hr>
					<ul class="list-tb" >
						<li class="list-tb-header">
							<div>征信业务</div>
							<div>下款额度</div>
							<div>管理</div>
						</li>
						<li class="list-tb-slection"  v-for="(i,index) in list">
							<div>
							<span v-if='i.alitoken!=""'>-阿里</span>
							<span v-if='i.facetoken!=""'>-人脸</span>
							<span v-if='i.phonetoken!=""'>-运营商</span>
							<span v-if='i.banktoken!=""'>-央行</span>
							</div>
							<div> <input class="money" v-model='i.money' type="text"/>元</div>
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
							<div style="height: 1000px;">
								<img id='img'>
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

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/axios.min.js"></script>
<script >
$(function(){
Vue.component('modal-find',{
	template:'#modal-find',
	props:['flag','item'],
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
		//this.http=document.getElementById('http').href;
	}
	
})

	new Vue({
		el:'#application',
		data:{
			list:[],
			isshow:false,
			http,
			temp:{}
		},
		methods:{
			closemodal:function(){
				this.isshow=false;
			},
			lookup:function(i){
				this.isshow=true;
				if(i.alitoken!=''){
					var token =i.alitoken;
					var res=null;
					var params = new URLSearchParams()
	    			params.append('Otoken', token)
	    			params.append('bizType', 'education')
					axios.post(this.http+'/limutoken/getSign',params)
		    		.then((response) => {
						if(response.data.code==1){
							res = response.data.data
							var subparams = new URLSearchParams()
							subparams.append('apiKey', res.apiKey);
							subparams.append('sign', res.sign);
							subparams.append('method', res.method);
							subparams.append('bizType', res.bizType);
							subparams.append('token', res.token);
							subparams.append('version', res.version);
							
							axios.post('https://t.limuzhengxin.cn/api/gateway',subparams)
				    		.then((response) => { 
								if(response.data.code=='0000'){ 
									var img = "data:image/png;base64,"+response.data.data.studentStatusInfo.personalPhotos
									document.getElementById('img').src=img;
									console.log(response.data.data.studentStatusInfo.personalPhotos)
								}
							}).catch(function (error) {
							    console.log(error);
							});
						}
					}).catch(function (error) { 
					    console.log(error);
					});
					
					
					
				}
				/* if(i.facetoken!='')	{
					this.temp.face={}
				}
				if(i.banktoken!=''){
					this.temp.bank={}
				}
				if(i.phonetoken!=''){
					this.temp.phone={}
				} */
				
			
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