/**
 * 弹出层树框插件
 */

(function($) {    
	$.fn.bfile = function(options) {
		  var defaults = { 
			title:'文件管理器',
		    url: '' ,
		    path:'/',
		    btn: '',
		    dialogs:false,
		    input:'',
		    onClick:null
		  };    
		  var opts = $.extend(defaults, options); 
		  
		  var formatSize = function(size){
			  return Math.round((size/1024)*Math.pow(10, 2))/Math.pow(10, 2)+'KB';
		  }
		  
		  var fileType = function(name){
			  var ss = name.split(".");
			  if(ss.length == 2) return ss[1];
		  }
		  
		  var tableCreate = function(json){
			  var tablethml = '<thead><tr><td>文件名</td><td>类型</td><td>文件大小</td></tr></thead>';
			    tablethml +='<tr><td>目录路径：'+opts.path+'</td><td></td><td></td></tr>'
			    tablethml +='<tr><td><a href="javascript:void(0);" class="bfileupbtn">上级目录</a></td><td></td><td></td></tr>'
				$.each(json, function(i, n){
					if(n.isdir){
						tablethml +='<tr><td><a href="javascript:void(0);" class="bfilebtn">{0}</a></td><td>{1}</td><td>{2}</td></tr>'.format(n.name, '','');
					}else{
						tablethml +='<tr><td><span class="bfilename">{0}</span></td><td>{1}</td><td>{2}</td></tr>'.format(n.name,fileType(n.name),formatSize(n.size));
					}
					  
				});
				$(tablethml).appendTo("#bfiledivlist");
		  }
		  
		  var openWin = function(){
			  msgdialog = $(showhtml).dialog({title:opts.title,onClose: function() { $(this).dialog("destroy");}});
		  }
		  
		  var opendir = function(){
			  clickFileName();
			  
			  $(".bfilebtn").click(function(){
					var dir = $(this).html();
					opts.path += dir+'/';
					connGetJson(opts.url+'?path='+opts.path);
			  });
			  
			  $(".bfileupbtn").click(function(){
				  var path = opts.path;
				  if(path.length > 3){
					  path = path.substring(0,path.length-1);
					  opts.path = path.substring(0,path.lastIndexOf('/')+1);
				  }
				  connGetJson(opts.url+'?path='+opts.path);
			  });
		  }
		  
		  var clickFileName = function(){
			  $(".bfilename").click(function(){
				  $("#"+opts.input).val(opts.path + $(this).html());
				  msgdialog.dialog("destroy");
			  });
		  }
		  
		  var connGetJson = function(url){
			  $.getJSON(url,function(json){
				    $("#bfiledivlist").empty();
					tableCreate(json);
					opendir();
				});
		  }
		  
		  var showhtml = '<div><table class="table" id="bfiledivlist"></table></div>';
		  var msgdialog;
		  $("#"+opts.btn).click(function(){
			  openWin();
			  connGetJson(opts.url+'?path='+opts.path);
		  });
			
		};
		
})(jQuery); 