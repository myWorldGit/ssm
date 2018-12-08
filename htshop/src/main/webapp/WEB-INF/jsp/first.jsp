<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>糊涂购-后台管理页面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/first.css">
</head>
<body>
<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" style="display: none;" id="http" ></a>
	<div class="container-wrap">
		
		<div class="index-page-left">
			<div class="admin-logo">
			
			</div>
			<ul>
				<li class="aside-li aside-active">
					<a href="${pageContext.request.contextPath }/admin/first">
					<span class="iconfont icon-shouye1 ">&nbsp;&nbsp;</span>
						首页
					</a>
				</li>
				<li class="aside-li">
					<a href="${pageContext.request.contextPath }/admin/commodiry">
					<span class="iconfont icon-shangping">&nbsp;&nbsp;</span>
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
				<li class="aside-li"><a href="${pageContext.request.contextPath }/admin/limu"><span class="iconfont icon-shezhi">&nbsp;&nbsp;
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<i class="iconfont icon-shouye1" ></i>&nbsp;首页
				</div>
			</div>
			<div class="header-wrap" id="application">
				<div class="container">
				<h1 style="color: skyblue; margin-left: 40px;">待发货列表</h1>
					<ul class="header-box">
						<li v-for="(order,index) in orderlist" style="position: relative;" >
							<div>商品名字：{{order.cname}}</div>
							<div style="color: red;">商品价格：{{order.price}}</div>
							<div>商品型号：{{order.ctype}}</div>
							<div>商品颜色：{{order.color}}</div>
							<div>买家地址：{{order.address}}</div>
							<div>买家号码：{{order.phone}}</div>
							<a   @click.prevent='toCommodiry(order,index)'    href="#" style="position: absolute; right: 3px; top: 3px; font-size: 30px;"  >发货</a>
						</li>
							
					</ul>
					<div class="btn-group" v-show='btn>1'>
						<a href="#" @click.prevent='preBtn'>上一页</a>
						<a class="numbtn" href="#" @click.prevent='numberBtn(d)'  v-for='(d,index) in btn' :class="{'btn-active':!index}">{{d}}</a> 
						<a href="#" @click.prevent='nextBtn'>下一页</a>
					</div>
					<button-counter @vbox-post='postBox' @vbox-close='closeBox' :flag='flagbox' :order='order'></button-counter>
				</div>
			</div>
			<div class="footer-wrap">
				<div class="container" >			
						<p class="footer-descritption">糊涂购后台管理页面&copy;2019</p>
				</div>
			</div>


		</div>
		
		<template id="postExpress" >
		        <div id="dialog" v-show='flag'>
		        <div class="dialog-wrap"> 
		           	<h3 class="dialog-title">
		           		编辑发货信息   <a href="#" @click.prevent='close'  class="close">×</a>
		            </h3>	
		           	<div class="dialog-content">
		           		<table class='order-table'>
		           			<tr class="order-tb-tr"> 
		           				<td>商品:</td>
		           				<td v-text='order.cname'></td>
		           				<td>订单编号:</td>
		           				<td v-text='order.oid'></td>
		           			</tr>
		           			<tr class="order-tb-tr">
		           				<td>价格:</td>
		           				<td v-text='order.price'></td>
		           				<td>用户名字:</td>
		           				<td v-text='order.rname'></td>
		           			</tr>
		           			<tr class="order-tb-tr">
		           				<td>型号:</td>
		           				<td v-text='order.ctype'></td>
		           				<td>用户地址:</td>
		           				<td v-text='order.address'></td>
		           			</tr>
		           			<tr class="order-tb-tr">
		           				<td>颜色:</td>
		           				<td v-text='order.color'></td>
		           				<td>用户号码:</td>
		           				<td v-text='order.phone'></td>
		           			</tr>
		           		
		           		</table>
		           		
		           		<div class="input-group" >
		           			<label>快递编号</label>
		              		<input  type="text" class="control" v-model='express'>     			
		           		</div>
		           		<div class="input-group" >
		           			<label>快递公司</label>
		              		<select  class="control-select" v-model="company">
		              			<option value="SF">顺丰</option>
		              			<option value="ZTO">中通</option>
		              			<option value="STO">申通</option>
		              			<option value="YTO">圆通</option>
		              			<option value="YD">韵达</option>	
		              			<option value="YZPY">邮政平邮</option>	
		              			<option value="QFKD">全峰</option>	
		              			<option value="GTO">国通</option>	
		              			<option value="UC">优速</option>	
		              			<option value="DBL">德邦</option>		              			
		              		</select>      			
		           		</div>
		           	</div>	
		           	<div class="dialog-btn">
		           		<a href="#" @click.prevent='Post'  >发送</a>
		           		<a  href="#" @click.prevent='close'  >关闭</a>
		           		
		           	</div>	
		     	</div>           
		        </div>
		</template>

	</div>
	
	
	
	
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/axios.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/first.js"></script>
</html>