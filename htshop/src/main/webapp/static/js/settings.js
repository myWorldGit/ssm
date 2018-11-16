$(function(){
	new Vue({
		el:'#application',
		data:{
			list:[{name:'readonlyre',oid:1},{name:'readddddonlyre',oid:4}],
		sowingMap:['1','1','1','1','1'],
		tempFile:null,
		location:''
		},
		mounted:function(){
			//渲染后调用生命周期函数
			$('.sowingMap-ganeral-li').eq(0).addClass("sowing-map-active")
		},
		methods: {
			fileChange:function(e){
				var targetFile = e.target.files[0];
				var formData =new FormData();
				formData.append(this.location,targetFile);
				this.location='';
				$.ajax({
					url:'test/upload',
					type:'POST',
					data:formData,
					contentType: false,
		            processData: false,
					success:function(data){
						alert(data+'s')
					},error:function(data){
						alert('false')
					}
				})
			},
			areaRight:function(){
				$("#temp-file").click()
				this.location='areaRight';
            },
			areaLeft:function(){
				$("#temp-file").click()
			},
			hotLeft:function(){
				$("#temp-file").click()
			},
			hotRightUp:function(){
				$("#temp-file").click()
			},
			hotRightDown:function(){
				$("#temp-file").click()
			},
			recommend1:function(){
				$("#temp-file").click()
			},
			recommend2:function(){  //推荐2
				$("#temp-file").click()
			},
			recommend3:function(){ //推荐3
				$("#temp-file").click()
			},
			boxTitle:function(){
				$(".box-title").find("span").toggleClass("icon-xiala icon-webicon308")
				$(".box-title").siblings("ul").slideToggle(300)
			},
			deleteInfo:function(oid,index) {  //这个系统消息删除list
				var flag =confirm("确定要删除吗?");
				if(flag==false){
					return;
				}
				this.list.splice(index,1);
				//根据oid数据库删除
			},
			updateInfo:function(oid,index){  //  系统消息  跟新list
				//alert($(e.target).attr("oid"))
				var value = prompt("请输入修改的内容");
				this.list[index].name=value
				//根据oid进行数据的修改

			},
			addInfo:function(){
				if(this.list.length>=10){
					confirm("已超过十条！！！！！")
					return;
				}
				var value = prompt("请输入添加的内容");

				if(value==null || value==""){
					return;
				}
				//返回他的oid
				this.list.push({name:value,oid:2})
				//插入操作
			},
			clearInfo:function(){
				confirm("确定要清空这些系统消息？")
				this.list=[];
				//执行清空语句
			},
			sowingBtn:function(e){  //轮播图的li按钮
				//样式修改
				$('.sowing-map-btn').find("li").removeClass("sowing-map-active")
				$(e.target).addClass("sowing-map-active")
			},
			sowingleft:function(e){
				//找到当前的点
				var $currentActive= $('.sowing-map-btn').find('.sowing-map-active')
				//删除行为
				$currentActive.removeClass("sowing-map-active")
				//上一个point节点
				$prev = $currentActive.prev(".sowingMap-ganeral-li")
				//如果到了最上面
				if($prev.hasClass("sowingMap-ganeral-li")==false){
					//跳到末尾
					$('.sowingMap-ganeral-li').last().addClass("sowing-map-active")

					//该对象中丢有相应的内容**************
				}else{
					$prev.addClass("sowing-map-active")
					//该对象中丢有相应的内容 *****************
				}

			},
			sowingright:function(){  //轮播图左右移动按钮
				//找到当前的点
				var $currentActive= $('.sowing-map-btn').find('.sowing-map-active')
				//删除行为
				$currentActive.removeClass("sowing-map-active")
				//上一个point节点
				$next = $currentActive.next(".sowingMap-ganeral-li")
				//如果到了最上面
				if($next.hasClass("sowingMap-ganeral-li")==false){
					//跳到末尾
					$('.sowingMap-ganeral-li').first().addClass("sowing-map-active")
					//该对象中丢有相应的内容  ****************8
				}else{
					$next.addClass("sowing-map-active")
					//该对象中丢有相应的内容  88******************8
				}
			}

		}

	})
})
