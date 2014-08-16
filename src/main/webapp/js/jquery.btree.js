/**
 * 弹出层树框插件
 */

(function($) {    
	$.fn.btree = function(options) {  
		  var defaults = { 
			title:'网站栏目结构',
		    url: '' ,
		    btn:'',
		    dialogs:false,
		    value:0,
		    label:null,
		    onClick:null
		  };    
		  var opts = $.extend(defaults, options);
		  
		  var showhtml = '<div><ul id="btreewrap"></ul></div>';
		  var msgdialog;
		  
		  var openWin = function(){
			  msgdialog = $(showhtml).dialog({title:opts.title,onClose: function() { $(this).dialog("destroy");}});
		  }
		  
		  var treeGetJson = function(){
			  $.getJSON(opts.url,function(json){
				  	openWin();
					$('#btreewrap').tree({data: json, onClick:  function(node, $link) {
				            opts.onClick.call(this,node);
				            msgdialog.dialog("destroy");
				          }
					 });
				});
		  }
		  
		  var create = function(){
			  $("#"+opts.btn).click(function(){
				  treeGetJson();
				});
		  }
		  
		  create();//创建
		};
		
})(jQuery); 