$(function(){
	Vue.component('v-dialog-modify', {  //修改 的 
        template: '#dialog-update',
        props:['flag','temp','classifys'],
        data:function(){	
            return {
            	
            }
        },
        methods:{
        	modify:function(){
        		//document.getElementById('form-modify').reset();
        		this.$emit('modify-dialogmod');
        	},
        	close:function(){
        		//console.log(this.temp.classify.tid)
        		document.getElementById('form-modify').reset();
        		this.$emit('close-dialogmod');
        	},
        	filechangemod:function(e){
        		const file = e.srcElement.files[0]
        		const imgURL = window.URL.createObjectURL(file)
        		this.temp.photo=imgURL
        		
         	}
        },
        mounted:function(){
        	
        }
    })



    Vue.component('v-dialog-add', {   //////添加的
        template: '#dialog-add',
        props:['flag','classifys'],
        data:function(){
            return {
            	urlPath:'这个弄张默认图片'
            }
        },
        methods:{
        	fileChange:function(e){
        		 const file = e.srcElement.files[0]
        		 const imgURL = window.URL.createObjectURL(file)
        		 this.urlPath=imgURL;     		
        	},
        	close:function(){
        		
        		this.urlPath='默认图片  还原';
        		this.$emit('close-dialogadd');
        	},
        	insert:function(){
        		this.urlPath='默认图片  还原';
        		this.$emit('insert-dialogadd')
        	}
        },
        created:function(){
        }
    })


    Vue.component('v-dialog-detail', {
    	template:'#dialog-detail',
    	props:['flag','temp','http'],
    	data:function(){
    		return {
    		args:{active:'',did:0,img:'',index:0}
    		}
    	},
    	methods:{
    		close:function(){
    			this.$emit('close-dialogdetail');
    		},
    		append:function(){
    			document.getElementById("detailsFile").click();
    			this.args.active='insert';
    			//加个行为  删除还是插入
    			//添加进去
    		},
    		replace:function(img,did,index){
    			document.getElementById("detailsFile").click();
    			this.args.active='update';
    			this.args.index=index;
    			this.args.img=img;
    			this.args.did=did;
    			//加个行为  删除还是插入	
    			//添加进去
    		},
    		deletes:function(img,did,index){   //OK
    			var flag = confirm("确定删除该图片？")
    			if(flag==false) return;
    			var params = new URLSearchParams()
     			params.append('did', did)
     			params.append('img', img)
    			axios.post(this.http+'/adminCommodiry/delDetails', params)
        		.then((response) => {
    				if(response.data.code==1){
    	    			this.temp.splice(index,1)
    	    			//提示图  扩展这
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});

    		},
    		fileBtn:function(e){
    			var file = e.target.files[0];
    			if(file==undefined || !/image/.test(file.type)){
        			return alert("相片格式有误")
        		}//判断相片是否格式正确
    			if(this.args.active=='insert'){
    				var params = new FormData()
    				params.append('cid', this.temp.cid)  //根据商品id进行图片插入
    				params.append('file', file)
    				axios.post(this.http+'/adminCommodiry/insertDetail', params)
            		.then((response) => {
        				if(response.data.code==1){
        					var detail = response.data.data.detail
        					
        					this.temp.push({did:detail.did,image:detail.image,
        						width:0,height:0,commodiry:null})	
        				}
        			})
        			.catch(function (error) {
        			    console.log(error);
        			});

    				
    			}else if(this.args.active=='update'){
    				var params = new FormData()
    				params.append('did', this.args.did)
    				params.append('img', this.args.img)
    				params.append('file', file)
    				axios.post(this.http+'/adminCommodiry/updateDetail', params)
            		.then((response) => {
        				if(response.data.code==1){
        					var detail = response.data.data.detail
        					console.log(JSON.stringify(detail))
        					this.temp[this.args.index].image=detail.image
        	    			//提示图  扩展这
        				}
        			})
        			.catch(function (error) {
        			    console.log(error);
        			});

    				
    			}
    		
    		}
    	}
    })

   
	new Vue({
		el:'#application',
		data:{
			sumPage:0,
			currentPage:1,
			countData:0,
			currentIndex:0,
			currentPhoto:'',
			classifys:[],
			temp:{mod:'',det:''},
			http:'',
			commodiry_List:[],
			modal:{showDialogmod:false,showDialogadd:false,showDialogdetail:false}
		},
		watch:{
			countData:function(){
				this.sumPage=Math.ceil(parseInt(this.countData)/8);
			},
			currentPage:function(){
				//给当前按钮添加样式
				$('.btn-group-pages').find('.nbtnflag').removeClass("bypage-btn")
				$('.btn-group-pages').find('.nbtnflag').eq(this.currentPage-1).addClass("bypage-btn")
			}
		},
		mounted:function(){
        	this.http=$("#http").val()
        	var params = new URLSearchParams()
        	params.append('tid', '1') 
 			params.append('page', '0')
        	axios.post(this.http+'/adminCommodiry/getinit', params)
        		.then((response) => {
        			if(response.data.code==1){
    					this.countData=response.data.data.datasum
	    				var res = response.data.data.commodirys
	    				for(var i=0 ;i<res.length;i++){  //商品数据
	    					this.commodiry_List.push({cname:res[i].cname,ctype:res[i].ctype
	    						,counts:res[i].counts,price:res[i].price,
	    					cid:res[i].cid,photo:res[i].photo})
	    				}
	    				var classess = response.data.data.classifys  //所有的类型
	    				for(i=0 ; i<classess.length;i++){
		    				this.classifys.push({tid:classess[i].tid,
		    					tname:classess[i].tname})
	    				}
        			}
    				
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
        	
        	
        },
		methods: {
		
			addDialogadd:function(){
				//
				var $form = $('#add-form');
        		//图片
        		var file = $form.find('input[type=file]')[0].files[0]
        		if(file==undefined || !/image/.test(file.type)){
        			return alert("图片未选中或者是格式有误")
        		}
        		var params = new FormData()
        		var t = $form.serializeArray();
        		for(var i=0;i<t.length;i++){
        			//这里可以判断是否为空值 打回
         			params.append(t[i].name, t[i].value)
        		}        	
        		params.append('file', file)
      			axios.post(this.http+'/adminCommodiry/addCommodiry', params)
        		.then((response) => {
    				if(response.data.code==1){
    					//插入数组中  //按钮商品+1
    					this.countData++;
    					var commodiry = response.data.data.commodiry
    					if(this.commodiry_List.length<8){
    						//加入商品行列回显
    						this.commodiry_List.push({cname:commodiry.cname,
    							ctype:commodiry.ctype,
    							counts:commodiry.counts,
    							price:commodiry.price,
		    					cid:commodiry.cid,
		    					photo:commodiry.photo})
    					}
    					document.getElementById('add-form').reset();
    					this.modal.showDialogadd=false;
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
			},
			closeDialogadd:function(){//关闭添加的模态框
				document.getElementById('add-form').reset();
				this.modal.showDialogadd=false;
			},
			modifyDig:function(){
				//修改
				
				var $form = $('#form-modify');
        		//图片
        		var file = $form.find('input[type=file]')[0].files[0]
        		var params = new FormData()
        		if(file!=undefined ){
        			params.append('file', file)
        		}
        		var t = $form.serializeArray();
        		for(var i=0;i<t.length;i++){
        			//这里可以判断是否为空值 打回
         			params.append(t[i].name, t[i].value)
        		}   
        		params.append('photo', this.currentPhoto)
      			axios.post(this.http+'/adminCommodiry/updateCommodiry', params)
        		.then((response) => {
    				if(response.data.code==1){
    					var commodiry = response.data.data.commodiry
    					this.commodiry_List[this.currentIndex].cname=commodiry.cname,
    					this.commodiry_List[this.currentIndex].ctype=commodiry.ctype,
    					this.commodiry_List[this.currentIndex].counts=commodiry.counts,
    					this.commodiry_List[this.currentIndex].price=commodiry.price,
    					this.commodiry_List[this.currentIndex].cid=commodiry.cid,
    					this.commodiry_List[this.currentIndex].photo=commodiry.photo,

    					
    					this.modal.showDialogmod=false;
    	        		document.getElementById('form-modify').reset();
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
				
				

			},
			closeDialogmod:function(){//关闭对话框  跟新的
				this.currentPhoto='';
                this.modal.showDialogmod=false;
       	    },
			closeDialogdetail:function(){  //关闭详情
				this.modal.showDialogdetail=false;
			},
			modCommodiry:function(cid,index){//跟新模态回显
				
				this.currentIndex=index;
				//this.currentPhoto='sa';
				var params = new URLSearchParams()
	        	params.append('cid', cid) 
				axios.post(this.http+'/adminCommodiry/findSingle', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.temp.mod=response.data.data.commodiry
    					this.temp.mod.tid=this.temp.mod.classify.tid
    					this.currentPhoto=this.temp.mod.photo;
    					//console.log(JSON.stringify(this.temp.mod.classify.tid))
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});

				this.modal.showDialogmod=true;
			},
			delCommodiry:function(cid,index){
				
				var flag = confirm("确认删除？？？")
				if(flag==false)return;
				//cid删除
				var params = new URLSearchParams()
	        	params.append('cid', cid) 
	        	params.append('photo',this.commodiry_List[index].photo)
	        	
				axios.post(this.http+'/adminCommodiry/deleteCommodiry', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.commodiry_List.splice(index,1);
    					this.searchBtn();  //刷新
    					this.currentPage=1;
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});

				
				
			},
			addCommodriy:function(){
				this.modal.showDialogadd=true;
			},
			detCommodiry:function(cid){  //详情
				var params = new URLSearchParams()
	        	params.append('cid', cid) 
				axios.post(this.http+'/adminCommodiry/findDetails', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.temp.det=response.data.data.commodiry
    					this.temp.det.cid=response.data.data.cid;   /****ccccccccccccccc**/
    					console.log(this.temp)
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
				this.modal.showDialogdetail=true;
			},
			searchBtn:function(){
				var forms = $('#search-form').serializeArray();
				var params = new URLSearchParams()
				params.append(forms[0].name,forms[0].value)	
				params.append(forms[1].name,forms[1].value)
				params.append('page',0)
				axios.post(this.http+'/adminCommodiry/keyword', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.countData=response.data.data.datasum
    					var commodirys = response.data.data.commodirys
    					this.commodiry_List=[];
    					for(var i=0;i<commodirys.length;i++){
    						this.commodiry_List.push({
    							cname:commodirys[i].cname,
    							ctype:commodirys[i].ctype,
    							counts:commodirys[i].counts,
    							price:commodirys[i].price,
		    					cid:commodirys[i].cid,
		    					photo:commodirys[i].photo})
    					}
    				
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
				
			},
			Tooption:function(){
				this.searchBtn()
			},
			prev:function(e){
				if(this.currentPage==1){
					return;
				}
				this.number(--this.currentPage,e)

			},
			number:function(btn,e){
				if(this.currentPage==btn&&e.target.name=='number'){
					return;  //当前就不用了
				}
				this.currentPage=btn;
				
				var forms = $('#search-form').serializeArray();
				var params = new URLSearchParams()
				params.append(forms[0].name,forms[0].value)	
				params.append(forms[1].name,forms[1].value)
				params.append('page',btn-1)
				axios.post(this.http+'/adminCommodiry/keyword', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.countData=response.data.data.datasum
    					var commodirys = response.data.data.commodirys
    					this.commodiry_List=[];
    					for(var i=0;i<commodirys.length;i++){
    						this.commodiry_List.push({
    							cname:commodirys[i].cname,
    							ctype:commodirys[i].ctype,
    							counts:commodirys[i].counts,
    							price:commodirys[i].price,
		    					cid:commodirys[i].cid,
		    					photo:commodirys[i].photo})
    					}
    				
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
				

			},
			next:function(e){
				if(this.currentPage>=this.sumPage){
					return;
				}
				this.number(++this.currentPage,e)
			},
			keywordsubmit:function(){
				alert("")
				
			}

		}
	})
	
	//$('.btn-group-pages').find('.nbtnflag').eq(0).addClass("bypage-btn")

})