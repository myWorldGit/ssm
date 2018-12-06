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
					<ul class="header-box">
						<li v-for="(order,index) in orderlist" >
						{{order.oid}}
						</li>
							
					</ul>
					<div class="btn-group" v-show='btn>1'>
						<a href="#" @click.prevent='preBtn'>上一页</a>
						<a class="numbtn" href="#" @click.prevent='numberBtn(d)'  v-for='(d,index) in btn' :class="{'btn-active':!index}">{{d}}</a> 
						<a href="#" @click.prevent='nextBtn'>下一页</a>
					</div>
				</div>
			</div>
			<div class="footer-wrap">
				<div class="container" >			
						<p class="footer-descritption">糊涂购后台管理页面&copy;2019</p>
				</div>
			</div>


		</div>

	</div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/axios.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/first.js"></script>
</html>