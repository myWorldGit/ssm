<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>

	<meta charset="utf-8">
	<title>糊涂购-后台管理页面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/settings.css">
</head>
<body>
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
				<li class="aside-li aside-active">
					<a href="#">
						<span class="iconfont icon-shezhi">&nbsp;&nbsp;</span>
						界面设置
					</a>
				
				</li>
			</ul>
		
		</div>

		<div class="index-page-right">
			<div class="topbar-wrap">
				<div class="container">
						<a href="#"><i class="iconfont icon-shouye1"></i>&nbsp;首页</a>&nbsp;/&nbsp;
						<span>&nbsp;APP界面设置</span>
				</div>
			</div>
			<div class="slection-wrap">
				<div class="container">
					<div  id="application" >
						<div class="aap-model">
							<div class="sowing-map">
								<img id="sowing-map-show" :src="filepath.target">
								<a class="sowing-map-delbtn" @click.prevent="sowingdel" href="#">删除</a>	
								<a class="sowing-map-addbtn" @click.prevent="sowingadd" href="#">添加</a>
								<ul class="sowing-map-btn">
									<!-- 此处动态 行为首个确定-->
									<li @click="sowingBtn($event)" :img="p.value" :index="p.oid" :pos="index" v-for="(p,index) in filepath.sowingMap" class="sowingMap-ganeral-li"></li>
								</ul>
								<a @click.prevent="sowingleft" class="sowing-map-leftbtn" href="#"></a>
								<a @click.prevent="sowingright" class="sowing-map-rightbtn" href="#"></a>
								
							</div>
						
							<div class="special-area">
								<img @click="areaLeft" class="area-left" :src="filepath.areaLeft">
								<img @click="areaRight" class="area-right" :src="filepath.areaRight">
							</div>
							<hr class="special-line">
							<div class="system-message">
								<span class="iconfont icon-broadcast"></span>
								<span class="content-list">this is system message !!!</span>
							</div>
							<div class="img-recommend">
								<span>靓物推荐</span>&nbsp;&nbsp;&nbsp;
								<img @click="recommend1" :src="filepath.recommend1">
								<img @click="recommend2" :src="filepath.recommend2">
								<img @click="recommend3" :src="filepath.recommend3">
							</div>
							<hr class="special-line">
							<div class="phone-hot clearfix">
								<div class="hot-left">
									<img @click="hotLeft" :src="filepath.hotLeft">
								</div>
								<div class="hot-right">
									<img @click="hotRightUp"  :src="filepath.hotRightUp">
									<img @click="hotRightDown"  :src="filepath.hotRightDown">
								</div>
							</div>
							
							<div class="commodiry-recommend">
								<h1>为您推荐</h1>
								<a href="#">  <!--跳转至调整推荐商品-->
								跳转到热门推荐
								</a>
							</div>
							<form class="hidden" id="form-file">
								<input @change='fileChange' id="temp-file" type="file" >
								<input id="httpsufix" type="hidden" 
								value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}">
								<input @change='sowingChange' id="sowingMap-file" type="file">
								<input type="reset" id="temp-reset">
							</form>

						</div>

						<div class="database-operaction" >
							<div class="system-message-box">
								<h2  @click='boxTitle' class="box-title">系统信息&nbsp;<span class="iconfont icon-webicon308"></span></h2>
								<hr>

								<ul class="hidden">
									<li v-for="(item,index) in list" class="list-items">
										<input type="text" readonly="readonly" v-model="item.value" class="items-content" >
										<div class="group-btn">
											<a @click.prevent='updateInfo(item.oid,index)' href="#" class="update-btn">修改</a>
											<a @click.prevent='deleteInfo(item.oid,index)' href="#" class="delete-btn">删除</a>
										</div>
									</li>
									<li class="system-other-btn clearfix" >
										<hr>
										<a href="#" @click.prevent="addInfo" class="addContent" >添加系统信息</a>
										<a href="#" @click.prevent="clearInfo" class="clearContent">清空系统信息</a>
									</li>
								</ul>
							
							</div>
							
						</div>



					</div>
					
				</div>

			</div>
			<!--底部   -->
			<div class="footer-wrap">
				<div class="container">
				<p class="footer-descritption">糊涂购后台管理页面&copy;2019</p>
			


				</div>

			</div>


		</div>

	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/settings.js"></script>

</html>