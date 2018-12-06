$(function(){
	new Vue({
		el:'#application',
		data:{
			http,ordersum:0,orderlist:[],currentpage:1,btn:0
		},
		methods:{
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