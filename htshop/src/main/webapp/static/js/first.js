$(function(){
		Vue.component('button-counter', { 
		  props:[
			"flag","order"
		  ],
		  methods:{  
			  close:function(){
				  this.express='';
				  this.company='SF';
				  this.$emit('vbox-close');
			  },
			  Post:function(){
				  this.$emit('vbox-post',{oid:this.order.oid,express:this.express,company:this.company});
				  this.express='';
				  this.company='SF';
			  }
		  },
		  data: function () {
		    return {
		    	express:'',company:'SF'
		    }
		  },
		  template: '#postExpress'
		})
	
	new Vue({
		el:'#application',
		data:{
			http,ordersum:0,orderlist:[],currentpage:1,btn:0,
			flagbox:false,order:{},Index:-1
		},
		methods:{
			postBox:function(o){
				var params = new FormData()
				params.append("oid",o.oid)
				params.append("express",o.express)
				params.append("company",o.company)
				
				axios.post(http+'/adminfirst/setExpress',params)
	    		.then((response) => {
					if(response.data.code==1){
						console.log(response.data)
						this.flagbox=false;
						this.orderlist.splice(this.Index,1);
					}
				})
				.catch(function (error) {
				    console.log(error);
				});
				
			},
			closeBox:function(){
				this.flagbox=false;
			},
			numberBtn:function(d){
				if(d==this.currentpage){
					return;
				}
				this.http = document.getElementById('http').href;	
				var params = new FormData()
				params.append('page', d-1)
				axios.post(http+'/adminfirst/byPage',params)
	    		.then((response) => {
					if(response.data.code==1){
						console.log(response.data.data.order)
						this.orderlist = response.data.data.order;
						this.currentpage=d
					}
				})
				.catch(function (error) {
				    console.log(error);
				});
				
			},
			preBtn:function(){
				if(this.currentpage<=1){
					return;
				}
				this.numberBtn(this.currentpage-1)
			},
			nextBtn:function(){
				if(this.currentpage==this.btn){
					return;
				}
				this.numberBtn(this.currentpage+1)
			},
			toCommodiry:function(order,index){
				this.flagbox=true;
				this.order=order
				this.Index=index;
				
			}
			
		},
		mounted:function(){
			this.http = document.getElementById('http').href;	
			var params = new FormData()
			params.append('page', 0)
			axios.post(http+'/adminfirst/initPage',params)
    		.then((response) => {
				if(response.data.code==1){
					console.log(response.data.data)
					var res = response.data.data;
					this.ordersum=res.ordersum;
					this.orderlist=res.order
					this.btn=Math.ceil(this.ordersum/12);
					
				}
			})
			.catch(function (error) {
			    console.log(error);
			});
				
		},
		watch:{
			ordersum:function(){
				//退页
				if(this.ordersum%12==0 && this.page>1){
					//上一页的查询
				}
			},
			orderlist:function(){
				this.ordersum--;
			},
			currentpage:function(){
				$(".numbtn").removeClass("btn-active");
				$(".numbtn").eq(this.currentpage-1).addClass("btn-active");
			}
		}
	})
})