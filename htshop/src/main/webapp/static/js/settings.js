$(function(){
	
	var vue=new Vue({
		el:'#application',
		data:{
			amount:'222',
			list:[],
			tempFile:null,
			location:'',
			filepath:{areaRight:'',areaLeft:'',hotLeft:'',hotRightUp:'',
				hotRightDown:'',recommend1:'',recommend2:'',recommend3:'',
				sowingMap:[],
			target:''
			}
		},
		mounted:function(){
			//
		},
		beforeMount:function(){
			httpn = document.getElementById("httpsufix").value;	
			
			//轮播图
			axios.post(httpn+'/other/getsowingMap')
    		.then((response) => {
				if(response.data.code==1){
					console.log(response.data.data.sowingMap)
					var t= response.data.data.sowingMap
					for(var i =0 ;i<t.length;i++){
						this.filepath.sowingMap.push({value:t[i].value,oid:t[i].otherid})
					}
					this.amount=response.data.data.preAmount==null?0:response.data.data.preAmount
				}
			})
			.catch(function (error) {
			    console.log(error);
			});
				
			axios.post(httpn+'/other/getuploadMap')
    		.then((response) => {
				if(response.data.code==1){
					var data= response.data.data;
					this.filepath.areaLeft= data.areaLeft;
					this.filepath.areaRight= data.areaRight;
					this.filepath.recommend1= data.recommend1;
					this.filepath.recommend2= data.recommend2;
					this.filepath.recommend3= data.recommend3;
					this.filepath.hotLeft= data.hotLeft;
					this.filepath.hotRightUp= data.hotRightUp;
					this.filepath.hotRightDown= data.hotRightDown;
				}
			})
			.catch(function (error) {
			    console.log(error);
			});
			
			
			axios.post(httpn+'/other/getsystemInfo')
    		.then((response) => {
				if(response.data.code==1){
					var data= response.data.data.data;
					//console.log(data.data)
					for(var i=0;i<data.length;i++){
						this.list.push({value:data[i].value,oid:data[i].otherid})
					}
				}
			})
			.catch(function (error) {
			    console.log(error);
			});
				
			//系统消息加载
	
			
		},
		watch:{
			amount:function(){  
				var reg =/^[0-9]*$/;
				//alert(this.amount)
				if(!reg.test(this.amount)){
					this.amount = this.amount.substr(0,this.amount.length-1)
				}
				
			}
		},
		methods: {
			changeAmount:function(){
				var flag =confirm("是否确定修改 ？？")
				if(flag!=true){
					return;
				}
				if(this.amount>100000000){
					alert("数额较大!!!!")
				}
				//ajax  做修改
				var params = new URLSearchParams()
	        	params.append('preAmount', this.amount) 
				axios.post(httpn+'/adminCommodiry/preAmount', params)
        		.then((response) => {
    				if(response.data.code==1){
    					
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});

				
				
			},
			fileChange:function(e){
				var targetFile = e.target.files[0];
				var formData =new FormData();
				formData.append("file",targetFile);
				formData.append("location",this.location)
				this.location='';
				var temp =null;
				var which='';
				$.ajax({
					url:httpn+'/other/uploadimg',
					type:'POST',
					async:false,
					data:formData,
					dataType:'json',
					cache:false,
					contentType: false,
		            processData: false,
					success:function(data){
						if(data.code==1) {
							temp =data.data.image;
							which = data.data.location;
						}else{
							alert("fail");
						}
					},error:function(data){
						alert('文件上传失败请检查网络连接是否正常...')
					}
				})
				switch(which){
					case 'areaLeft':this.filepath.areaLeft=temp; break;
					case 'areaRight':this.filepath.areaRight=temp; break;
					case 'hotLeft':this.filepath.hotLeft=temp; break;
					case 'hotRightUp':this.filepath.hotRightUp=temp; break;
					case 'hotRightDown':this.filepath.hotRightDown=temp; break;
					case 'recommend1':this.filepath.recommend1=temp; break;
					case 'recommend2':this.filepath.recommend2=temp; break;
					case 'recommend3':this.filepath.recommend3=temp; break;
					default: break;
				}
			},
			areaRight:function(){
				$("#temp-file").click()
				this.location='areaRight';
            },
			areaLeft:function(){
				$("#temp-file").click()
				this.location='areaLeft';
			},
			hotLeft:function(){
				$("#temp-file").click()
				this.location='hotLeft';

			},
			hotRightUp:function(){
				$("#temp-file").click()
				this.location='hotRightUp';
			},
			hotRightDown:function(){
				$("#temp-file").click()
				this.location='hotRightDown';
			},
			recommend1:function(){
				$("#temp-file").click()
				this.location='recommend1';
			},
			recommend2:function(){  //推荐2
				$("#temp-file").click()
				this.location='recommend2';
			},
			recommend3:function(){ //推荐3
				$("#temp-file").click()
				this.location='recommend3';
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
				$.ajax({
					url:document.getElementById("httpsufix").value+'/other/delsystemInfo',
					type:'post',
					data:{oid:oid},
					async:true,
					dataType:'json',
					success:function(data){
						if(data.code==1){
							//success  做提示框
						}
					},error:function(data){
						alert("fail")
					}
				})
			},
			updateInfo:function(oid,index){  //  系统消息  跟新list
				//alert($(e.target).attr("oid"))
				var value = prompt("请输入修改的内容");
				this.list[index].value=value
				//根据oid进行数据的修改
				$.ajax({
					url:document.getElementById("httpsufix").value+'/other/updateinfo',
					type:'post',
					async:true,
					data:{oid:oid,value:value},
					dataType:'json',
					success:function(data){
						if(data.code==1){
							//success
						}
					},error:function(data){
						alert("fail")
					}
				})

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

				var oid =0;
				$.ajax({
					url:document.getElementById("httpsufix").value+'/other/addsystemInfo',
					type:'post',
					data:{content:value},
					async:false,
					dataType:'json',
					success:function(data){
						oid=data;
					},error:function(data){
						alert("fail")
					}
				})
				this.list.push({value:value,oid:oid})
				//插入操作
			
			},
			clearInfo:function(){
				confirm("确定要清空这些系统消息？")
				this.list=[];
				$.ajax({
					url:document.getElementById("httpsufix").value+'/other/clearinfo',
					type:'post',
					async:true,
					dataType:'json',
					success:function(data){
						if(data.code==1){
							//success
						}
					},error:function(data){
						alert("fail")
					}
				})
				//执行清空语句
			},
			sowingBtn:function(e){  //轮播图的li按钮
				//样式修改
				$('.sowing-map-btn').find("li").removeClass("sowing-map-active")
				$(e.target).addClass("sowing-map-active")
				this.filepath.target=$(e.target).attr("img");
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
					this.filepath.target=
						this.filepath.sowingMap[this.filepath.sowingMap.length-1].value;
					//该对象中丢有相应的内容**************
				}else{
					$prev.addClass("sowing-map-active")
					this.filepath.target=$prev.attr("img")
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
					this.filepath.target=this.filepath.sowingMap[0].value;
				}else{
					$next.addClass("sowing-map-active")
					//该对象中丢有相应的内容  88******************8
					this.filepath.target=$next.attr("img");
				}
			},
			sowingdel:function(){
				var $currentActive= $('.sowing-map-btn').find('.sowing-map-active')
				var oid = $currentActive.attr("index");
				var flag =confirm("确定要删除吗?");
				if(flag==false){
					return;
				}
				if(this.filepath.sowingMap.length==0){ 
					alert("已无数据") 
					return;
				}
				
				pos = $currentActive.attr("pos")
				this.filepath.sowingMap.splice(pos,1)
				var li = document.querySelector(".sowingMap-ganeral-li");
				if(li!=null) {
					li.click();
				}
				
				//根据oid删除
				$.ajax({
					url:document.getElementById("httpsufix").value+'/other/delsowingMap',
					type:'post',
					data:{oid:oid},
					async:true,
					dataType:'json',
					success:function(data){
						if(data.code==1){
							//success  做提示框
						}
					},error:function(data){
						alert("fail")
					}
				})
				
				
			},
			sowingadd:function(){
				document.getElementById("sowingMap-file").click();
				
				
				
			},
			sowingChange:function(e){
				var targetFile = e.target.files[0];
				var formData =new FormData();
				formData.append("file",targetFile);
				var value=null;
				var oid=null;
				$.ajax({
					url:httpn+'/other/addsowingMap',
					type:'POST',
					async:false,
					data:formData,
					dataType:'json',
					cache:false,
					contentType: false,
		            processData: false,
					success:function(data){
						if(data.code==1) {
							value=data.data.value;
							oid = data.data.oid;
						}else{
							alert("fail");
						}
					},error:function(data){
						alert('文件上传失败请检查网络连接是否正常...')
					}
				})
				this.filepath.sowingMap.push({value:value,oid:oid})
				
			}

		}

	})
	//点击第一个
	var first_sowingMap =document.querySelector(".sowingMap-ganeral-li");
	if(first_sowingMap!=null) {
		first_sowingMap.click();
	}
})

