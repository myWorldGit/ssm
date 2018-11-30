<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="utf-8">
	<title>糊涂购-后台管理页面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/commodiry.css">
</head>
<body>
	<input id="http" type="hidden" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}">
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
				<li class="aside-li aside-active">
					<a href="#">
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
						<a href="#"><i class="iconfont icon-shouye1"></i>&nbsp;首页</a>&nbsp;/&nbsp;<span>&nbsp;&nbsp;商品管理&nbsp;</span>
				</div>
			</div>
			<div class="slection-wrap" id='application'>
				<div class="container" >
					<div class="from-search">
						<form id="search-form" @submit.prevent="onSubmit">
								<label><a href="#" @click.prevent='addCommodriy'><i class="iconfont icon-webicon308"  ></i>添加商品</a></label>
								<select class="form-select-optional" name="tid" @change="Tooption">
									<option v-for="type in classifys" :value="type.tid">{{type.tname}}</option>
									
								</select>
								<input type="text" name="keyword" value=""  
									placeholder="请输入查询关键字" ><a href="#" @click.stop.prevent='searchBtn'>
									<span class="iconfont icon-chazhao"></span>
								</a>
						</form>   
					</div>
					<hr style="border-color: skyblue;">
					<div class="commdiry-list" >
					
						<ul id="commodiry-list">
							<li v-for="(item,index) in commodiry_List" class="commodiry-list-item clearfix">
								<img :src="item.photo" >
								<div>
									<ul class="commodiry-infomation">
										<li>{{item.cname}}</li>
										<li>机型：&nbsp;{{item.ctype}}</li>
										<li>￥: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.price}}</li>
										<li>销量：&nbsp;{{item.counts}}</li>
										<li><a href="#" @click.prevent='modCommodiry(item.cid,index)' class="commodiry-modify-btn">修改商品</a></li>
										<li><a href="#" @click.prevent='detCommodiry(item.cid)' class="commodiry-detail-btn">详情图片</a></li>
										<li><a href="#" @click.prevent='delCommodiry(item.cid,index)' class="commodiry-delete-btn">删除商品</a></li>
									</ul>
								</div>
							</li>
						</ul>
						<div class="btn-group-pages">
							<ul v-if="sumPage>1">
								<li><a href="#" @click.prevent='prev($event)'>上一页</a></li>
								<li v-for="(btn,index) in sumPage" >
								<a :class="{'bypage-btn':!index}" class='nbtnflag' name="number"  href="#" @click.prevent='number(btn,$event)'>{{btn}}
								</a>
								</li>
								
								
								<li><a href="#" @click.prevent='next($event)'>下一页</a></li>
							</ul>
						</div>
						<v-dialog-modify   :flag="modal.showDialogmod" :temp="temp.mod"
						:classifys="classifys"  @close-dialogmod="closeDialogmod"
												 @modify-dialogmod="modifyDig"
						> </v-dialog-modify>
						<v-dialog-add   :flag="modal.showDialogadd" :classifys="classifys"
						@close-dialogadd="closeDialogadd" @insert-dialogadd="addDialogadd"> 
						</v-dialog-add>
						<v-dialog-detail :http="http" :flag="modal.showDialogdetail" @del-color='delColor' 
						 :temp="temp.det" @close-dialogdetail="closeDialogdetail"> </v-dialog-detail>

					</div>
				
					
				</div>

			</div>

			<div class="footer-wrap">
				<div class="container" >					
						<p class="footer-descritption">糊涂购后台管理页面&copy;2019</p>


				</div>

			</div>



				<!--详情定义的模板   -->
			<template id="dialog-detail" >
		        <div class="dialog" v-show='flag'>
		            <div class="dialog_mask"></div>
		            <div class="dialog_container">
		            	<h3>详情图片<a href="javascript:;" @click='close'>×</a></h3>
		                <div class="dialog_content" style="	background-color: #eeb1b1;">
		                	<form class="detail-form">
		                		<input type="file" @change='fileBtn' id="detailsFile">
		                		<input type="reset" name="">
		                	</form>
		                	<ul class="sowingimg-list">
		                		<li style="font-size: 30px; text-align: center;" v-if='temp==""'>赞无图片</li>
		                		<li v-for='(t,index) in temp'>
	                		<img :src="t.image">
	                		<a href="#" class="replace" @click.prevent='replace(t.image,t.did,index)'>替换</a>
	                		<a href="#" class="delete" @click.prevent='deletes(t.image,t.did,index)'>删除</a>
		                		</li>		            
		                	</ul>
		                	
		                    <div class="dialog_btn" style="margin-bottom: 20px;">
		                        <a href="javascript:;" class="btn" @click="append">添加图</a>
		                        <a href="javascript:;" class="btn" @click="close">取消</a>
		                    </div>
		                   	<hr/>
		                    <div class="color-list-wrap">
		                		<input  type="text"  v-model='text' />
		                		<a class='add' href="#" @click.prevent='addColor'>添加颜色</a >
		                		<a href="#" class='save' @click.prevent='saveColor'>保存颜色</a>
		                		<div>
		                		<select multiple="multiple" class='dispalySelect' >
		                			<option   @click="delcolor(index)" v-for="(c ,index) in temp.color" >
		                				{{c}}
		                			</option>
		                		</select>
		         		
		                		</div>
		                		
		                	</div>
		                </div>
		            </div>
		        </div>
		    </template>


			<!--定义的模板   -->
			<template id="dialog-update">
		        <div class="dialog" v-show='flag'>
		            <div class="dialog_mask"></div>
		            <div class="dialog_container" style="background-color:rgb(224, 230, 210);" >
		            	<h3>修改商品<a href="javascript:;" @click='close'>×</a></h3>
		                <div class="dialog_content">
		                	<div class="form-wrap">
		                		<img :src="temp.photo"  >
		                		<form class="form-style" id="form-modify">
		                		
		                			<input type="hidden" :value="temp.cid" name="cid">
		                			<div class="form-gorup">
		                				<label>商品名字</label>
		                				<input type="text" name="cname" :value="temp.cname" placeholder="请输入商品名">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品价格</label>
		                				<input type="text" name="price" :value="temp.price" placeholder="请输入商品价格">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品型号</label>
		                				<input type="text" name="ctype" :value="temp.ctype" placeholder="请输入商品型号">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品类别</label>
		                				<select name="classify.tid" :value="temp.tid" >
		                					<option  v-for="type in classifys" :value="type.tid">{{type.tname}}</option>
		                				</select>
		                			</div>
		                			<div class="form-gorup" >
		                				<label>商品销量</label>
		                				<input type="number" name="counts" :value="temp.counts" placeholder="请输入销量">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品图片</label>
		                				<input  name="file" style="font-size: 10px;" @change='filechangemod' type="file"  >
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品描述</label>
		                				<textarea name="description" :value="temp.description" style="vertical-align: middle;" type="text" name="name" rows="6" placeholder="请输入描述信息"></textarea>
		                			</div>
		                			

		                		</form>
		                		
		                	</div>
		                
		                    <div class="dialog_btn">
		                        <a href="javascript:;" class="btn" @click="modify">修改</a>
		                        <a href="javascript:;" class="btn" @click="close">取消</a>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </template>

		    	<!--add定义的模板   -->
			<template id="dialog-add" >
		        <div class="dialog" v-show='flag'>
		            <div class="dialog_mask"></div>
		            <div class="dialog_container" style="background-color:rgb(251, 209, 240);">
		            	<h3>添加商品<a href="javascript:;" @click='close'>×</a></h3>
		                <div class="dialog_content">
		                	<div class="form-wrap">
		                		<img :src="urlPath" >
		                		<form class="form-style" id="add-form" >
		                			<div class="form-gorup">
		                				<label>商品名字</label>
		                				<input type="text" name="cname" value="" placeholder="请输入商品名">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品价格</label>
		                				<input type="text" name="price" value="0.00" placeholder="请输入商品价格">
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品型号</label>
		                				<input type="text" name="ctype" value="" placeholder="请输入商品型号">
		                			</div>
		                			
		                			<div class="form-gorup">
		                				<label>商品类别</label>
		                				<select name="classify.tid" >
		                					<option v-for="type in classifys" :value="type.tid">{{type.tname}}</option>
		                				</select>
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品销量</label>
		                				<input type="number" value="0" name="counts"placeholder="请输入销量">
		                			</div>
		                			
		                			<div class="form-gorup">
		                				<label>商品图片</label>
		                				<input  style="font-size: 10px;" @change="fileChange" type="file" name="photo" >
		                			</div>
		                			<div class="form-gorup">
		                				<label>商品描述</label>
		                				<textarea style="vertical-align: middle;" type="text" name="description" 
		                				rows="6" value="" placeholder="请输入描述信息"></textarea>
		                			</div>
		                			

		                		</form>
		                		
		                	</div>
		                    <div class="dialog_btn">
		                        <a href="javascript:;" class="btn" @click="insert">确定</a>
		                        <a href="javascript:;" class="btn" @click="close">取消</a>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </template>



		</div>

	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Vue.v2.5.17.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/axios.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commodiry.js"></script>

</html>