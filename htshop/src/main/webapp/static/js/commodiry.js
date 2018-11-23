$(function(){
	Vue.component('v-dialog-modify', {  //修改 的 
        template: '#dialog-update',
        props:['flag','temp'],
        data:function(){	
            return {	
            }
        },
        methods:{
        	modify:function(){
        		this.$emit('close-dialogmod');
        	},
        	close:function(){
        		this.$emit('close-dialogmod');
        	}
        },
        created:function(){
        }
    })



    Vue.component('v-dialog-add', {
        template: '#dialog-add',
        props:['flag'],
        data:function(){
            return {
            	
            }
        },
        methods:{
        	close:function(){
        		this.$emit('close-dialogadd');
        	},
        	insert:function(){
        		this.$emit('close-dialogadd');
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
    		}
    	},
    	methods:{
    		close:function(){
    			this.$emit('close-dialogdetail');
    		},
    		append:function(){
    			document.getElementById("detailsFile").click();
    			//加个行为  删除还是插入
    			//添加进去
    		},
    		replace:function(did,index){
    			document.getElementById("detailsFile").click();
    			
    			//加个行为  删除还是插入	
    			//添加进去
    		},
    		deletes:function(did,index){   //OK
    			var flag = confirm("确定删除该图片？")
    			if(flag==false) return;
    			var params = new URLSearchParams()
     			params.append('did', did)
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
    		fileBtn:function(){
    			alert("改了图片")
    		}
    	}
    })


	new Vue({
		el:'#application',
		data:{
		
			temp:'',
			http:'',
			commodiry_List:[],
			modal:{showDialogmod:false,showDialogadd:false,showDialogdetail:false}
		},
		mounted:function(){
        	this.http=$("#http").val()
        	var params = new URLSearchParams()
        	params.append('tid', '1') 
 			params.append('page', '0')
        	axios.post(this.http+'/adminCommodiry/getinit', params)
        		.then((response) => {
        			if(response.data.code==1){
	    				var res = response.data.data.commodirys
	    				for(var i=0 ;i<res.length;i++){
	    					this.commodiry_List.push({cname:res[i].cname,ctype:res[i].ctype
	    						,counts:res[i].counts,price:res[i].price,
	    					cid:res[i].cid,photo:res[i].photo})
	    				}
        			}
    				
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
        	
        	
        },
		methods: {
			closeDialogadd:function(){
				this.modal.showDialogadd=false;
			},
			closeDialogmod:function(){//关闭对话框
                this.modal.showDialogmod=false;
       	    },
			closeDialogdetail:function(){
				this.modal.showDialogdetail=false;
			},
			modCommodiry:function(cid){
				var params = new URLSearchParams()
	        	params.append('cid', cid) 
				axios.post(this.http+'/adminCommodiry/findSingle', params)
        		.then((response) => {
    				if(response.data.code==1){
    					this.temp=response.data.data.commodiry
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
				this.commodiry_List.splice(index,1);
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
    					this.temp=response.data.data.commodiry
    					console.log(this.temp)
    				}
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
				this.modal.showDialogdetail=true;
			},














			prev:function(){
				alert("prev")

			},
			number:function(){
				alert("number")

			},
			next:function(){
				alert("next")
			}

		}
	})


})