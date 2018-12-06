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
					<modal-find :item='temp' :flag='isshow' @close-modal='closemodal'></modal-find>

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
				
				var params = new FormData();
				params.append('alitoken',i.alitoken);
				params.append('banktoken',i.banktoken);
				params.append('phonetoken',i.phonetoken);
				params.append('lid',i.lid);
				axios.post(this.http+'/limutoken/getlimuInfo',params)
				.then((response) => { 
					if(response.data.code==1){
 						alert()
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
	    			console.log(JSON.stringify(this.list))
				}
			}).catch(function (error) {
			    console.log(error);
			});
		}
	})



})


</script>

</html>